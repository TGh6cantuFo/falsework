package com.yaowk.user.shiro;

import org.apache.shiro.SecurityUtils;

/**
 * @authc yaowk
 * 2017/7/4
 */
public class TokenKit {
    public static final Integer getUserId() {
        return getUserInfo().getId();
    }

    public static final String getUsername() {
        return getUserInfo().getUsername();
    }

    public static final UserInfo getUserInfo() {
        return (UserInfo) SecurityUtils.getSubject().getPrincipal();
    }
}
