package com.yaowk.system.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.kit.Kv;
import com.yaowk.common.constant.ServiceCode;
import com.yaowk.common.controller.BaseController;
import com.yaowk.common.kit.I18nKit;
import com.yaowk.system.Constant;
import com.yaowk.system.model.User;
import com.yaowk.system.model.UserPlatform;
import com.yaowk.system.shiro.TokenKit;

import java.util.List;

/**
 * 平台权限验证拦截器
 *
 * @authc yaowk
 * 2017/7/16
 */
public class PlatformIdInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation invocation) {
        User user = User.dao.findById(TokenKit.getUserId());
        Integer platformId = invocation.getController().getParaToInt("platformId");
        boolean flag = false;

        Kv condition = Kv.by("user_id = ", user.getId());
        List<UserPlatform> userPlatforms = UserPlatform.dao.find(condition);
        for (UserPlatform userPlatform : userPlatforms) {
            if (userPlatform.getPlatformId() == platformId) {
                flag = true;
                break;
            }
        }

        if (flag) {
            invocation.invoke();
        }
        else {
            ((BaseController) invocation.getController()).renderFail(ServiceCode.NO_PERMISSION,
                    I18nKit.get("unauthorized", Constant.MODULE_NAME, invocation.getController()));
        }
    }
}
