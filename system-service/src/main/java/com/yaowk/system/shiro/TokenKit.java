package com.yaowk.system.shiro;

import com.xiaoleilu.hutool.util.ObjectUtil;
import org.apache.shiro.SecurityUtils;

/**
 * @authc yaowk
 * 2017/7/4
 */
public class TokenKit {
    public static final Integer getUserId() {
        UserInfo userInfo = getUserInfo();
        if (ObjectUtil.isNotNull(userInfo)) {
            return getUserInfo().getId();
        }
        return null;
    }

    public static final String getUsername() {
        return getUserInfo().getUsername();
    }

    public static final UserInfo getUserInfo() {
        return (UserInfo) SecurityUtils.getSubject().getPrincipal();
    }
}
