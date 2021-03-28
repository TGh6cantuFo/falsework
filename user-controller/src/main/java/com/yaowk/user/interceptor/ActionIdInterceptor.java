package com.yaowk.user.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.kit.Kv;
import com.yaowk.user.model.User;
import com.yaowk.user.model.UserPlatform;
import com.yaowk.user.shiro.TokenKit;

import java.util.List;

/**
 * 平台权限验证拦截器
 *
 * @authc yaowk
 * 2017/7/16
 */
public class ActionIdInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation invocation) {
        User user = User.dao.findById(TokenKit.getUserId());
        Integer platformId = invocation.getController().getParaToInt("platformId");
        boolean flag = false;
        if (user.getPlatformId() == platformId) {
            flag = true;
        }
        else {
            Kv condition = Kv.by("user_id = ", user.getId());
            List<UserPlatform> userPlatforms = UserPlatform.dao.find(condition);
            for (UserPlatform userPlatform : userPlatforms) {
                if (userPlatform.getPlatformId() == platformId) {
                    flag = true;
                    break;
                }
            }
        }

        if (flag) {
            invocation.invoke();
        }
        else {
            throw new RuntimeException("user platformId is not the request platformId");
        }
    }
}
