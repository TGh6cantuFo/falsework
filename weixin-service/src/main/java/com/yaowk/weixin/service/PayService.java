package com.yaowk.weixin.service;

import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.PaymentApi;
import com.jfinal.weixin.sdk.kit.PaymentKit;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.yaowk.weixin.WeixinKit;
import com.yaowk.weixin.model.Config;
import com.yaowk.weixin.model.Order;
import com.yaowk.weixin.model.pay.UnifiedOrder;

import java.util.Map;

/**
 * @authc yaowk
 * 2017/7/17
 */
public class PayService {

    public Map<String, String> unifiedOrder(String deviceCode, Integer userId, UnifiedOrder unifiedOrder) {
        Config config = WeixinKit.getConfigByDeviceCode(deviceCode);
        if (ObjectUtil.isNotNull(config)) {
            ApiConfig apiConfig = ApiConfigKit.getApiConfig(config.getAppId());
            unifiedOrder.setAppId(apiConfig.getAppId())
                    .setMchId(config.getMchId())
                    .setNonceStr(String.valueOf(System.currentTimeMillis()))
                    .setOutTradeNo(StrKit.getRandomUUID().toUpperCase());

            PaymentKit.createSign(unifiedOrder, config.getKey());

            String result = PaymentApi.pushOrder(unifiedOrder);

            Map<String, String> resultMap = PaymentKit.xmlToMap(result);

            if (!"SUCCESS".equals(resultMap.get("return_code"))) {
                return resultMap;
            }

            if (!"SUCCESS".equals(resultMap.get("result_code"))) {
                return resultMap;
            }

            if (PaymentKit.verifyNotify(resultMap, config.getKey())) {
                Order order = new Order();
                order.setOutTradeNo(unifiedOrder.getOutTradeNo())
                        .setPlatformId(config.getId())
                        .setCodeUrl(resultMap.get("code_url"))
                        .setPrepayId(resultMap.get("prepay_id"))
                        .setTradeType(resultMap.get("trade_type"))
                        .setSpbillCreateIp(unifiedOrder.getSpBillCreateIp())
                        .setTotalFee(unifiedOrder.getTotalFee())
                        .setUserId(userId)
                        .save();
                resultMap.put("key", config.getKey());
                return resultMap;
            }
        }
        return null;
    }
}
