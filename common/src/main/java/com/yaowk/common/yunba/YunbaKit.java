package com.yaowk.common.yunba;

import com.jfinal.json.FastJson;
import com.jfinal.json.Json;
import com.jfinal.kit.HttpKit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @authc yaowk
 * 2017/5/8
 */
public class YunbaKit {

    private static final String URL = "http://rest.yunba.io:8080";

    private static final Map<String, String> HEADERS = new HashMap<>();

    static {
        HEADERS.put("Content-type", "application/json");
    }

    public static YunbaResponse publish(String msg, String topic) {
        return publish(msg, "", topic);
    }

    public static YunbaResponse publish(String msg, String content, String topic) {
        YunbaRequest yunbaRequest = new YunbaRequest()
                .setTopic(topic)
                .setMsg(msg)
                .setThirdTitle(msg)
                .setApsAlert(msg)
                .setThirdContent(content)
                .setMethod("publish");
        return send(yunbaRequest);
    }

    public static YunbaResponse push(String msg, String content) {
        return publish(msg, content, "push");
    }

    public static YunbaResponse push(String msg) {
        return push(msg, "");
    }

    public static YunbaResponse publishAlias(String msg, String alias) {
        return publishAlias(msg, "", alias);
    }

    public static YunbaResponse publishAlias(String msg, String content, String alias) {
        YunbaRequest yunbaRequest = new YunbaRequest()
                .setAlias(alias)
                .setMsg(msg)
                .setThirdTitle(msg)
                .setApsAlert(msg)
                .setThirdContent(content)
                .setMethod("publish_to_alias");
        return send(yunbaRequest);
    }

    public static YunbaResponse publishAliases(String msg, String content, List<String> aliases) {
        YunbaRequest yunbaRequest = new YunbaRequest()
                .setAliases(aliases)
                .setMsg(msg)
                .setThirdTitle(msg)
                .setApsAlert(msg)
                .setThirdContent(content)
                .setMethod("publish_to_alias_batch");
        return send(yunbaRequest);
    }

    public static YunbaResponse publishAliases(String msg, List<String> aliases) {
        return publishAliases(msg, "", aliases);
    }


    private static YunbaResponse send(YunbaRequest yunbaRequest) {
        Json json = FastJson.getJson();
        String resp = HttpKit.post(URL, json.toJson(yunbaRequest), HEADERS);
        return json.parse(resp, YunbaResponse.class);
    }
}
