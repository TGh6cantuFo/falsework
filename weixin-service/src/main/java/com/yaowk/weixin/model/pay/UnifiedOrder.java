package com.yaowk.weixin.model.pay;


import com.yaowk.common.kit.DateKit;

import java.util.Date;
import java.util.HashMap;

/**
 * @authc yaowk
 * 2017/7/17
 */
public class UnifiedOrder extends HashMap<String, String> {

    public UnifiedOrder setAppId(String appId) {
        put("appid", appId);
        return this;
    }

    public UnifiedOrder setMchId(String mchId) {
        put("mch_id", mchId);
        return this;
    }

    public UnifiedOrder setDeviceInfo(String deviceInfo) {
        put("device_info", deviceInfo);
        return this;
    }

    public UnifiedOrder setNonceStr(String nonceStr) {
        put("nonce_str", nonceStr);
        return this;
    }

    public UnifiedOrder setBody(String body) {
        put("body", body);
        return this;
    }

    public UnifiedOrder setDetail(String detail) {
        put("detail", detail);
        return this;
    }

    public UnifiedOrder setAttach(String attach) {
        put("attach", attach);
        return this;
    }

    public UnifiedOrder setOutTradeNo(String outTradeNo) {
        put("out_trade_no", outTradeNo);
        return this;
    }

    public String getOutTradeNo() {
        return get("out_trade_no");
    }

    public UnifiedOrder setFeeType(String feeType) {
        put("fee_type", feeType);
        return this;
    }

    public UnifiedOrder setTotalFee(Integer totalFee) {
        put("total_fee", totalFee.toString());
        return this;
    }

    public UnifiedOrder setSpBillCreateIp(String ip) {
        put("spbill_create_ip", ip);
        return this;
    }

    public String getSpBillCreateIp() {
        return get("spbill_create_ip");
    }

    public UnifiedOrder setTimeStart(Date date) {
        put("time_start", DateKit.toStr(date, "yyyyMMddHHmmss"));
        return this;
    }

    public UnifiedOrder setTimeExpire(Date date) {
        put("time_expire", DateKit.toStr(date, "yyyyMMddHHmmss"));
        return this;
    }

    public UnifiedOrder setNotifyUrl(String notifyUrl) {
        put("notify_url", notifyUrl);
        return this;
    }

    public UnifiedOrder setTradeType(String tradeType) {
        put("trade_type", tradeType);
        return this;
    }

    public UnifiedOrder setOpenId(String openId) {
        put("openid", openId);
        return this;
    }

    public Integer getTotalFee() {
        return Integer.valueOf(get("total_fee"));
    }
}
