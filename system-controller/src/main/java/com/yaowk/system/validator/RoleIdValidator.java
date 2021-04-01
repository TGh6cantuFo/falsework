package com.yaowk.system.validator;

import com.jfinal.core.Controller;
import com.yaowk.system.model.Role;
import com.yaowk.system.shiro.TokenKit;

/**
 * @authc yaowk
 * 2017/7/20
 */
public class RoleIdValidator extends SystemBaseValidator {
    @Override
    protected void validate(Controller controller) {
        Integer roleId = controller.getParaToInt();
        Role role = Role.dao.findById(roleId);
        if (role.getCreateUserId() != TokenKit.getUserId()) {
            addError(ERROR_KEY, unauthorizedMsg(controller));
        }
    }
}
