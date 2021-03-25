package com.yaowk.user.controller;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Page;
import com.yaowk.common.controller.BaseController;
import com.yaowk.user.model.User;

/**
 * @authc yaowk
 * 2017/6/28
 */
public class UserController extends BaseController {

    public void index() {
        Integer id = getParaToInt();
        User user = User.dao.findById(id, "id,username");
        renderJson(user);
    }

    public void list() {
        Kv condition = Kv.by("status != ", "0");
        Page<User> page = User.dao.paginate(getPage(), condition);
        renderJson(page);
    }

    public void add() {
        User user = getBean(User.class);
        user.save();
    }


}
