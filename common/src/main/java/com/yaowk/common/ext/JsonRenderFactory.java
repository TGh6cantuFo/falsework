package com.yaowk.common.ext;

import com.jfinal.render.JsonRender;
import com.jfinal.render.Render;
import com.jfinal.render.RenderFactory;
import com.yaowk.common.element.RespBody;

/**
 * @authc yaowk
 * 2017/6/28
 */
public class JsonRenderFactory extends RenderFactory {
    @Override
    public Render getErrorRender(int errorCode) {
        RespBody respBody = new RespBody();
        Render render;
        switch (errorCode) {
            case 500:
                render = new JsonRender("500");
                break;
            case 404:
                render = new JsonRender(respBody);
                break;
            default:
                render = super.getErrorRender(errorCode);
        }
        return render;
    }
}
