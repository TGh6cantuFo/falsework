package com.yaowk.user.controller;

import com.jfinal.aop.Duang;
import com.yaowk.common.controller.BaseController;
import com.yaowk.user.service.UserService;

/**
 * @authc yaowk
 * 2017/6/28
 */
public class UserController extends BaseController {
    UserService userService = Duang.duang(UserService.class);

    public void index() {
//        Kv kv = Kv.create().set("condition", Kv.create().set("name = ", 1));
//        SqlPara sqlPara = Db.getSqlPara("find", kv);
//        String sql = sqlPara.getSql();
//        Object[] param = sqlPara.getPara();
        userService.find();
        renderNull();
    }
}
