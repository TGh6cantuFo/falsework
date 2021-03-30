package com.yaowk.weixin.common;

import com.jfinal.kit.Kv;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.yaowk.system.model.Platform;

import java.util.List;

/**
 * @authc yaowk
 * 2017/7/17
 */
public class ApiConfigInit {

    public static void init() {
        List<Platform> platformList = Platform.dao.find(Kv.create());
        for (Platform platform : platformList) {
//            ApiConfig apiConfig = new ApiConfig(platform.getToken(), platform.getAppId(), platform.getAppSecret(), platform.getMessageEncrypt() == 1 ? true : false, platform.getEncodingAesKey());
//            ApiConfigKit.putApiConfig(apiConfig);
        }
    }
}
