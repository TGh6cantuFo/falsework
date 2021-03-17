package com.yaowk.common.controller;

import com.jfinal.core.Controller;
import com.yaowk.common.constant.ServiceCode;
import com.yaowk.common.model.base.Page;
import com.yaowk.common.model.element.ElementPage;
import com.yaowk.common.model.element.RespBody;

/**
 * @authc yaowk
 * 2017/6/27
 */
public class ElementController extends Controller implements IController {

    @Override
    public Page getPage() {
        return getBean(ElementPage.class, "", true);
    }

    @Override
    public void renderJson(Object object) {
        RespBody respBody = new RespBody();
        super.renderJson(object);
    }

    @Override
    public void renderSuccess() {
        RespBody respBody = new RespBody();
        respBody.setCode(ServiceCode.SUCCESS);
        renderJson(respBody);
    }
}
