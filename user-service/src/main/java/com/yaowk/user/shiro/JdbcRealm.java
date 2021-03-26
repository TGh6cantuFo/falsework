package com.yaowk.user.shiro;

import com.jfinal.plugin.ehcache.CacheKit;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.yaowk.common.constant.CacheConstant;
import com.yaowk.user.model.Menu;
import com.yaowk.user.model.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.List;

/**
 * @authc yaowk
 * 2017/7/3
 */
public class JdbcRealm extends AuthorizingRealm {

    @Override
    public String getName() {
        return "JdbcRealm";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserInfo userInfo = (UserInfo) principalCollection.getPrimaryPrincipal();
        return CacheKit.get(CacheConstant.AUTH, userInfo.getId() + "authInfo", () -> {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            List<Menu> menus = Menu.dao.findMenuByUserId(userInfo.getId());
            for (Menu menu : menus) {
                info.addStringPermission(menu.getPermission());
            }
            return info;
        });
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = authenticationToken.getPrincipal().toString();
        User user = User.dao.findByUsername(username);
        if (ObjectUtil.isNotNull(user)) {
            UserInfo userInfo = new UserInfo(user.getId(), user.getUsername());
            return new SimpleAuthenticationInfo(userInfo, authenticationToken.getPrincipal(), getName());
        }
        return null;
    }
}
