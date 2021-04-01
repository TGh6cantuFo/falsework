package com.yaowk.device.validator;

import com.jfinal.core.Controller;
import com.jfinal.kit.Kv;
import com.yaowk.device.model.DeviceUser;
import com.yaowk.system.shiro.TokenKit;

import java.util.List;

/**
 * @authc yaowk
 * 2017/7/20
 */
public class DeviceIdValidator extends DeviceBaseValidator {
    @Override
    protected void validate(Controller controller) {
        Integer deviceId = controller.getParaToInt();
        List<DeviceUser> deviceUserList = DeviceUser.dao.find(Kv.by("user_id = ", TokenKit.getUserId()));
        boolean flag = false;
        for (DeviceUser deviceUser : deviceUserList) {
            if (deviceUser.getDeviceId() == deviceId) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            addError(ERROR_KEY, unauthorizedMsg(controller));
        }
    }
}
