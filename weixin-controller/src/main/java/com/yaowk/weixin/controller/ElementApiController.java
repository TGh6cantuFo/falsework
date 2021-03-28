package com.yaowk.weixin.controller;

import com.jfinal.weixin.sdk.jfinal.ApiController;
import com.yaowk.common.constant.ServiceCode;
import com.yaowk.common.controller.IController;
import com.yaowk.common.model.base.Page;
import com.yaowk.common.model.element.ElementPage;
import com.yaowk.common.model.element.RespBody;

/**
 * @authc yaowk
 * 2017/7/16
 */
public class ElementApiController extends ApiController implements IController {

    @Override
    public Page getPage() {
        return getBean(ElementPage.class, "", true);
    }

    @Override
    public void renderJson(Object object) {
        RespBody respBody = new RespBody();
        respBody.setData(object);
        respBody.setCode(ServiceCode.SUCCESS);
        super.renderJson(respBody);
    }

    @Override
    public void renderSuccess() {
        RespBody respBody = new RespBody();
        respBody.setCode(ServiceCode.SUCCESS);
        renderJson(respBody);
    }
}
