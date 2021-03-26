package com.yaowk.user.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.yaowk.common.controller.BaseController;
import com.yaowk.user.model.Menu;
import com.yaowk.user.model.RoleMenu;
import com.yaowk.user.shiro.TokenKit;

import java.util.List;

/**
 * @authc yaowk
 * 2017/7/13
 */
@Before(Tx.class)
public class MenuController extends BaseController {

    @Clear(Tx.class)
    public void list() {
        List<Menu> menuList = Menu.dao.findMenuByUserId(TokenKit.getUserId());
        menuList = Menu.dao.parseMenu(menuList);
        renderJson(menuList);
    }

    @Clear(Tx.class)
    public void index() {
        Integer id = getParaToInt();
        Menu menu = Menu.dao.findById(id);
        renderJson(menu);
    }

    public void add() {
        Menu menu = getBean(Menu.class, "");
        menu.save();
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setRoleId(PropKit.use("config.properties").getInt("AdminRoleId"));
        roleMenu.setMenuId(menu.getId());
        roleMenu.save();
        renderSuccess();
    }

    public void edit() {
        Menu menu = getBean(Menu.class, "");
        menu.update();
        renderSuccess();
    }

    public void delete() {
        Integer id = getParaToInt();
        new Menu().setId(id).delete();
        renderSuccess();
    }
}
