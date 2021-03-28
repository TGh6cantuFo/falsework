package com.yaowk.system.controller;

import com.jfinal.aop.Clear;
import com.jfinal.log.Log;
import com.jfinal.plugin.ehcache.CacheKit;
import com.yaowk.common.constant.CacheConstant;
import com.yaowk.common.constant.ServiceCode;
import com.yaowk.common.controller.BaseController;
import com.yaowk.common.kit.I18nKit;
import com.yaowk.system.Constant;
import com.yaowk.system.interceptor.PlatformIdInterceptor;
import com.yaowk.system.model.User;
import com.yaowk.system.shiro.TokenKit;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 * 用户认证接口
 *
 * @authc yaowk
 * 2017/7/5
 */
@Clear({ PlatformIdInterceptor.class })
public class AuthController extends BaseController {

    public void login() {
        User user = getBean(User.class, "");
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            CacheKit.remove(CacheConstant.AUTH, TokenKit.getUserId() + "authInfo");
            subject.logout();
        }
        try {
            subject.login(usernamePasswordToken);
        }
        catch (IncorrectCredentialsException e) {
            Log.getLog("").info("", e);
            renderFail(ServiceCode.FAIL, I18nKit.get("username_or_password_mistake", Constant.MODULE_NAME, this));
            return;
        }
        renderSuccess();
    }

    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        CacheKit.remove(CacheConstant.AUTH, TokenKit.getUserId() + "authInfo");
        subject.logout();
        renderSuccess();
    }

    public void unauthorizedUrl() {
        renderFail(ServiceCode.NO_PERMISSION, I18nKit.get("unauthorized", Constant.MODULE_NAME, this));
    }

    public void successUrl() {
        renderSuccess();
    }

    public void loginUrl() {
        renderFail(ServiceCode.NO_LOGIN, I18nKit.get("no_login", Constant.MODULE_NAME, this));
    }
}
