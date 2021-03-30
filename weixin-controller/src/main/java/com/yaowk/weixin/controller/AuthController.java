package com.yaowk.weixin.controller;

import com.jfinal.aop.Before;
import com.jfinal.kit.Kv;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.SnsAccessToken;
import com.jfinal.weixin.sdk.api.SnsAccessTokenApi;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.yaowk.user.model.User;
import com.yaowk.weixin.Constant;
import com.yaowk.weixin.WeixinKit;
import com.yaowk.weixin.model.Fans;

import java.util.List;

/**
 * @authc yaowk
 * 2017/7/18
 */
public class AuthController extends BaseApiController {

    @Before(Tx.class)
    public void index() {
        String code = getPara("code");
        if (StrKit.isBlank(code)) {
            renderText("code is Blank!");
        }
        ApiConfig apiConfig = ApiConfigKit.getApiConfig(getSessionAttr(Constant.APPID));
        String appId = apiConfig.getAppId();
        String secret = apiConfig.getAppSecret();
        SnsAccessToken snsAccessToken = SnsAccessTokenApi.getSnsAccessToken(appId, secret, code);

        String openId = snsAccessToken.getOpenid();
        String token = snsAccessToken.getAccessToken();

        Kv condition = Kv.by("openid = ", openId);
        List<Fans> fansList = Fans.dao.find(condition);
        if (CollectionUtil.isEmpty(fansList)) {
            User user = new User();
            user.setUsername(openId).save();
            Fans fans = new Fans();
            fans.setOpenid(openId).setUserId(user.getId()).save();
            setSessionAttr(Constant.USERID, user.getId());
        }
        else {
            setSessionAttr(Constant.USERID, fansList.get(0).getUserId());
        }

        redirect("");
    }

    public void oauth() {
        String code = getPara();
        ApiConfig apiConfig = WeixinKit.getApiConfigByDeviceCode(code);
        setSessionAttr(Constant.APPID, apiConfig.getAppId());
        String appId = apiConfig.getAppId();
        String redirectUri = WeixinKit.urlEncodeUTF8(PropKit.get("domain") + "/weixin/auth");
        String state = String.valueOf(System.currentTimeMillis());
        String url = SnsAccessTokenApi.getAuthorizeURL(appId, redirectUri, state, false);
        redirect(url);
    }
}
