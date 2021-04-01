package com.yaowk.system.validator;

import com.jfinal.core.Controller;
import com.yaowk.system.model.User;
import com.yaowk.system.shiro.TokenKit;

/**
 * @authc yaowk
 * 2017/7/19
 */
public class UserIdValidator extends SystemBaseValidator {
    @Override
    protected void validate(Controller controller) {
        Integer userId = controller.getParaToInt();
        User user = User.dao.findById(userId);
        if (user.getParentId() != TokenKit.getUserId()) {
            addError(ERROR_KEY, unauthorizedMsg(controller));
        }
    }
}
