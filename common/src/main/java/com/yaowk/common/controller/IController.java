package com.yaowk.common.controller;

import com.yaowk.common.model.base.Page;

/**
 * @authc yaowk
 * 2017/6/27
 */
public interface IController {

    Page getPage();

    void renderSuccess();
}
