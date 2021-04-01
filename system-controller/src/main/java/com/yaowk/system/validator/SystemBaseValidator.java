package com.yaowk.system.validator;

import com.jfinal.core.Controller;
import com.yaowk.common.kit.I18nKit;
import com.yaowk.common.validator.BaseValidator;
import com.yaowk.system.Constant;

/**
 * @authc yaowk
 * 2017/7/20
 */
public abstract class SystemBaseValidator extends BaseValidator {

    protected String unauthorizedMsg(Controller controller) {
        return I18nKit.get("unauthorized", Constant.MODULE_NAME, controller);
    }

    protected String idRequiredMsg(Controller controller) {
        return I18nKit.get("id_required", Constant.MODULE_NAME, controller);
    }
}
