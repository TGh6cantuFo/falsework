package com.yaowk.weixin.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Duang;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.weixin.sdk.api.PaymentApi;
import com.jfinal.weixin.sdk.kit.IpKit;
import com.jfinal.weixin.sdk.kit.PaymentKit;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.yaowk.device.event.PaperOutEvent;
import com.yaowk.weixin.Constant;
import com.yaowk.weixin.model.Config;
import com.yaowk.weixin.model.Order;
import com.yaowk.weixin.model.pay.UnifiedOrder;
import com.yaowk.weixin.service.PayService;
import net.dreamlu.event.EventKit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信支付接口
 *
 * @authc yaowk
 * 2017/7/17
 */
@Before(Tx.class)
public class PayController extends ElementApiController {

    private PayService payService = Duang.duang(PayService.class);

    public void simpleDeviceOfPaper() {
        String notifyUrl = "http://xxxx/falseword/weixin/pay/simpleDeviceOfPaperNotifyUrl";
        Integer totalFee = 1;

        String deviceCode = getPara();
        Integer userId = getSessionAttr(Constant.USERID);
        UnifiedOrder unifiedOrder = new UnifiedOrder();
        unifiedOrder.setBody(deviceCode + " : 纸巾机")
                .setTotalFee(totalFee)
                .setTradeType(PaymentApi.TradeType.JSAPI.name())
                .setOpenId(getSessionAttr("openid"))
                .setNotifyUrl(notifyUrl)
                .setSpBillCreateIp(IpKit.getRealIpV2(getRequest()));

        Map<String, String> resultMap = payService.unifiedOrder(deviceCode, userId, unifiedOrder);
        String prepay_id = resultMap.get("prepay_id");

        Map<String, String> packageParams = new HashMap<>();
        packageParams.put("appId", resultMap.get("appid"));
        packageParams.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
        packageParams.put("nonceStr", String.valueOf(System.currentTimeMillis()));
        packageParams.put("package", "prepay_id=" + prepay_id);
        packageParams.put("signType", "MD5");
        String packageSign = PaymentKit.createSign(packageParams, resultMap.get("key"));
        packageParams.put("paySign", packageSign);
        renderJson(packageParams);
    }

    public void simpleDeviceOfPaperNotifyUrl() {
        String xmlMsg = HttpKit.readData(getRequest());
        Map<String, String> params = PaymentKit.xmlToMap(xmlMsg);
        String orderId = params.get("out_trade_no");
        Kv condition = Kv.by(" out_trade_no = ", orderId);
        List<Order> orderList = Order.dao.find(condition);
        if (CollectionUtil.isNotEmpty(orderList)) {
            Order order = orderList.get(0);
            if (Order.STATUS_NO_PAY.equals(order.getStatus())) {
                condition = Kv.by("platform_id = ", order.getPlatformId());
                Config config = Config.dao.findFirst(condition);
                if (PaymentKit.verifyNotify(params, config.getKey())) {
                    if ("SUCCESS".equals(params.get("return_code"))) {
                        // 出纸巾
                        EventKit.post(new PaperOutEvent(order));
                        // 更新订单信息
                        order.setStatus(Order.STATUS_HAS_OUT).update();

                        Map<String, String> xml = new HashMap<String, String>();
                        xml.put("return_code", "SUCCESS");
                        xml.put("return_msg", "OK");
                        renderText(PaymentKit.toXml(xml));
                        return;
                    }
                }
            }
        }
        renderText("");
    }
}
