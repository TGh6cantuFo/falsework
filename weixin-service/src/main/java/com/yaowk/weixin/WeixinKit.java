package com.yaowk.weixin;

import com.jfinal.kit.Kv;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.yaowk.device.model.Device;
import com.yaowk.weixin.model.Config;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @authc yaowk
 * 2017/7/18
 */
public class WeixinKit {

    public static Config getConfigByDeviceCode(String code) {
        Kv condition = Kv.by("code = ", code);
        Device device = Device.dao.findFirst(condition);
        if (ObjectUtil.isNotNull(device)) {
            condition = Kv.by("platform_id = ", device.getBindPlatformId());
            Config config = Config.dao.findFirst(condition);
            return config;
        }
        return null;
    }

    public static ApiConfig getApiConfigByDeviceCode(String code) {
        Config config = getConfigByDeviceCode(code);
        if (ObjectUtil.isNotNull(config)) {
            return ApiConfigKit.getApiConfig(config.getAppId());
        }
        return null;
    }

    /**
     * UTF-8编码
     *
     * @param source
     * @return
     */
    public static String urlEncodeUTF8(String source) {
        try {
            return URLEncoder.encode(source, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }


}
