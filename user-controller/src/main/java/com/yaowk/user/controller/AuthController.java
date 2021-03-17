package com.yaowk.user.controller;

import com.jfinal.plugin.ehcache.CacheKit;
import com.yaowk.common.constant.CacheConstant;
import com.yaowk.common.controller.BaseController;
import com.yaowk.user.model.User;
import com.yaowk.user.shiro.TokenKit;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 * 用户认证接口
 *
 * @authc yaowk
 * 2017/7/5
 */
public class AuthController extends BaseController {

    public void login() {
        User user = getBean(User.class, "");
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            CacheKit.remove(CacheConstant.AUTH, TokenKit.getUserId() + "authInfo");
            subject.logout();
        }
        subject.login(usernamePasswordToken);
        renderSuccess();
    }

    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        CacheKit.remove(CacheConstant.AUTH, TokenKit.getUserId() + "authInfo");
        subject.logout();
        renderSuccess();
    }
}
