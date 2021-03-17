package com.yaowk.user.controller;

import com.jfinal.kit.Kv;
import com.yaowk.common.controller.BaseController;
import com.yaowk.user.model.User;

/**
 * @authc yaowk
 * 2017/6/28
 */
public class UserController extends BaseController {

    public void index() {
        Kv condition = Kv.create();
        User.dao.find(condition);
        renderNull();
    }
}
