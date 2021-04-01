package com.yaowk.device.validator;

import com.jfinal.core.Controller;
import com.yaowk.common.kit.I18nKit;
import com.yaowk.common.validator.BaseValidator;
import com.yaowk.device.Constant;

/**
 * @authc yaowk
 * 2017/7/20
 */
public abstract class DeviceBaseValidator extends BaseValidator {

    protected String unauthorizedMsg(Controller controller) {
        return I18nKit.get("unauthorized", Constant.MODULE_NAME, controller);
    }
}
