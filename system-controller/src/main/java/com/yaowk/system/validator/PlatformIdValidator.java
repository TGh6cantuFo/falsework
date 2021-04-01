package com.yaowk.system.validator;

import com.jfinal.core.Controller;
import com.jfinal.kit.Kv;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.yaowk.system.model.UserPlatform;
import com.yaowk.system.shiro.TokenKit;

import java.util.List;

/**
 * @authc yaowk
 * 2017/7/19
 */
public class PlatformIdValidator extends SystemBaseValidator {
    @Override
    protected void validate(Controller controller) {
        Integer platformId = controller.getParaToInt("platform_id");
        if (ObjectUtil.isNull(platformId)) {
            platformId = controller.getParaToInt();
        }
        Kv condition = Kv.by("user_id = ", TokenKit.getUserId());
        List<UserPlatform> userPlatformList = UserPlatform.dao.find(condition);
        boolean flag = false;
        for (UserPlatform userPlatform : userPlatformList) {
            if (userPlatform.getPlatformId() == platformId) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            addError(ERROR_KEY, unauthorizedMsg(controller));
        }
    }
}
