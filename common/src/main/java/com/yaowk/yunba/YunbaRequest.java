package com.yaowk.yunba;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 云巴推送body结构
 * 详细接口文档 https://yunba.io/docs/restful_api_api_manual
 *
 * @authc yaowk
 * 2017/5/8
 */
public class YunbaRequest extends HashMap<String, Object> {

    private final static String APPKEY = "59107d94f1ae5ffe36712a0d";
    private final static String SECKEY = "sec-AtStSzSUwPfBDX1c8KoYoNmKKQLl7OQv6iTuPzhjqSqIQRMD";

    private Map<String, Object> opts;
    private Map<String, Object> aps;
    private Map<String, String> thirdPartyPush;

    public YunbaRequest() {
        put("appkey", APPKEY);
        put("seckey", SECKEY);
    }

    public YunbaRequest setAlias(String alias) {
        getOpts().put("alias", alias);
        return this;
    }

    public String getAlias() {
        return (String) getOpts().get("alias");
    }

    public YunbaRequest setAliases(List<String> aliases) {
        getOpts().put("aliases", aliases);
        return this;
    }

    public List<String> getAliases() {
        return (List<String>) getOpts().get("aliases");
    }

    public YunbaRequest setTimeToLive(Integer time) {
        getOpts().put("time_to_live", time);
        return this;
    }

    public Integer getTimeToLive() {
        return (Integer) getOpts().get("time_to_live");
    }

    public YunbaRequest setQos(Integer qos) {
        getOpts().put("qos", qos);
        return this;
    }

    public Integer getQos() {
        return (Integer) getOpts().get("qos");
    }

    public YunbaRequest setApsAlert(String alert) {
        getAps().put("alert", alert);
        return this;
    }

    public String getApsAlert() {
        return (String) getAps().get("alert");
    }

    public YunbaRequest setBadge(Integer badge) {
        getAps().put("badge", badge);
        return this;
    }

    public Integer getBadge() {
        return (Integer) getAps().get("badge");
    }

    public YunbaRequest setThirdTitle(String title) {
        getThirdPartyPush().put("notification_title", title);
        return this;
    }

    public String getThirdTitle() {
        return getThirdPartyPush().get("notification_title");
    }

    public YunbaRequest setThirdContent(String content) {
        getThirdPartyPush().put("notification_content", content);
        return this;
    }

    public String getThirdContent() {
        return getThirdPartyPush().get("notification_content");
    }

    public String getMethod() {
        return (String) get("method");
    }

    public YunbaRequest setMethod(String method) {
        put("method", method);
        return this;
    }

    public String getAppkey() {
        return (String) get("appkey");
    }

    public YunbaRequest setAppkey(String appkey) {
        put("appkey", appkey);
        return this;
    }

    public String getSeckey() {
        return (String) get("seckey");
    }

    public YunbaRequest setSeckey(String seckey) {
        put("seckey", seckey);
        return this;
    }

    public String getTopic() {
        return (String) get("topic");
    }

    public YunbaRequest setTopic(String topic) {
        put("topic", topic);
        return this;
    }

    public String getMsg() {
        return (String) get("msg");
    }

    public YunbaRequest setMsg(String msg) {
        put("msg", msg);
        return this;
    }

    private Map<String, Object> getAps() {
        if (aps == null) {
            Map<String, Map<String, Object>> apnJson = new HashMap<>();
            aps = new HashMap<>();
            apnJson.put("aps", aps);
            getOpts().put("apn_json", apnJson);
        }
        return aps;
    }

    public Map<String, String> getThirdPartyPush() {
        if (thirdPartyPush == null) {
            thirdPartyPush = new HashMap<>();
            getOpts().put("third_party_push", thirdPartyPush);
        }
        return thirdPartyPush;
    }

    public Map<String, Object> getOpts() {
        if (opts == null) {
            opts = new HashMap<>();
            put("opts", opts);
        }
        return opts;
    }
}
