package com.yaowk.system.controller;

import com.jfinal.aop.Clear;
import com.jfinal.kit.PropKit;
import com.yaowk.common.controller.BaseController;
import com.yaowk.system.interceptor.PlatformIdInterceptor;
import com.yaowk.system.model.Menu;
import com.yaowk.system.model.RoleMenu;
import com.yaowk.system.shiro.TokenKit;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.util.List;

/**
 * @authc yaowk
 * 2017/7/13
 */
@RequiresAuthentication
@RequiresPermissions("system:menu")
@Clear(PlatformIdInterceptor.class)
public class MenuController extends BaseController {

    public void list() {
        List<Menu> menuList = Menu.dao.findMenuByUserId(TokenKit.getUserId());
        menuList = Menu.dao.parseMenu(menuList);
        renderJson(menuList);
    }

    public void index() {
        Integer id = getParaToInt();
        Menu menu = Menu.dao.findById(id);
        renderJson(menu);
    }

    @RequiresPermissions("system:menu:add")
    public void add() {
        Menu menu = getBean(Menu.class, "");
        menu.save();
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setRoleId(PropKit.use("config.properties").getInt("AdminRoleId"));
        roleMenu.setMenuId(menu.getId());
        roleMenu.save();
        renderSuccess();
    }

    @RequiresPermissions("system:menu:edit")
    public void edit() {
        Menu menu = getBean(Menu.class, "");
        menu.update();
        renderSuccess();
    }

    @RequiresPermissions("system:menu:delete")
    public void delete() {
        Integer id = getParaToInt();
        new Menu().setId(id).delete();
        renderSuccess();
    }
}
