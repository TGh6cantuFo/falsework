package com.yaowk.common.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.yaowk.common.constant.ServiceCode;
import com.yaowk.common.model.element.RespBody;

/**
 * @authc yaowk
 * 2017/7/19
 */
public abstract class BaseValidator extends Validator {

    protected static final String ERROR_KEY = "error_key";

    @Override
    protected void handleError(Controller controller) {
        RespBody respBody = new RespBody();
        respBody.setCode(ServiceCode.ERROR);
        respBody.setMsg(controller.getAttr(ERROR_KEY));
        controller.renderJson(respBody);
    }
}
