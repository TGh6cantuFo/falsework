package com.yaowk.user.controller;

import com.jfinal.aop.Duang;
import com.jfinal.kit.Kv;
import com.yaowk.common.controller.BaseController;
import com.yaowk.common.plugin.FindKv;
import com.yaowk.user.model.User;
import com.yaowk.user.service.UserService;

/**
 * @authc yaowk
 * 2017/6/28
 */
public class UserController extends BaseController {
    UserService userService = Duang.duang(UserService.class);

    public void index() {
        FindKv kv = FindKv.create().setCondition(Kv.create().set("username = ", 1)).setTable(User.tableName("user"));
//        SqlPara sqlPara = Db.getSqlPara("find", kv);
//        String sql = sqlPara.getSql();
//        Object[] param = sqlPara.getPara();
//        userService.find();
        User.dao.find(kv);
        renderNull();
    }
}
