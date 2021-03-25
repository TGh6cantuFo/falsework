package com.yaowk.user.controller;

import com.yaowk.common.controller.BaseController;
import com.yaowk.user.model.Menu;
import com.yaowk.user.shiro.TokenKit;

import java.util.List;

/**
 * @authc yaowk
 * 2017/7/13
 */
public class MenuController extends BaseController {

    public void list() {
        List<Menu> menuList = Menu.dao.getMenuByUserId(TokenKit.getUserId());
        menuList = Menu.dao.parseMenu(menuList);
        renderJson(menuList);
    }

    public void index() {
        Integer id = getParaToInt();
        Menu menu = Menu.dao.findById(id);
        renderJson(menu);
    }

    public void add() {
        Menu menu = getBean(Menu.class, "");
        menu.save();
        renderSuccess();
    }

    public void edit() {
        Menu menu = getBean(Menu.class, "");
        menu.update();
        renderSuccess();
    }
}
