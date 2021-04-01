package com.yaowk.system.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.kit.Kv;
import com.xiaoleilu.hutool.util.ArrayUtil;
import com.yaowk.common.controller.BaseController;
import com.yaowk.system.model.Menu;
import com.yaowk.system.model.Role;
import com.yaowk.system.model.RoleMenu;
import com.yaowk.system.shiro.TokenKit;
import com.yaowk.system.validator.RoleIdValidator;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @authc yaowk
 * 2017/7/14
 */
@RequiresAuthentication
@RequiresPermissions("system:role")
@Before(RoleIdValidator.class)
public class RoleController extends BaseController {

    public void index() {
        Integer id = getParaToInt();
        Role role = Role.dao.findById(id);
        renderJson(role);
    }

    public void menuList() {
        // 整理菜单
        List<Menu> userMenuList = Menu.dao.findMenuByUserId(TokenKit.getUserId());
        List<Menu> roleMenuList = Menu.dao.findByRoleId(getParaToInt());
        Set<Integer> roleMenuSet = new HashSet<>();
        for (Menu menu : roleMenuList) {
            roleMenuSet.add(menu.getId());
        }
        for (Menu menu : userMenuList) {
            if (roleMenuSet.contains(menu.getId())) {
                menu.setChecked(true);
            }
            else {
                menu.setChecked(false);
            }
        }
        userMenuList = Menu.dao.parseMenu(userMenuList);
        renderJson(userMenuList);
    }

    @Clear(RoleIdValidator.class)
    public void list() {
        Kv condition = Kv.create().set("create_user_id = ", TokenKit.getUserId());
        List<Role> roleList = Role.dao.find(condition);
        renderJson(roleList);
    }

    @Clear(RoleIdValidator.class)
    @RequiresPermissions("system:role:add")
    public void add() {
        Role role = getBean(Role.class, "", true);
        role.setCreateUserId(TokenKit.getUserId());
        role.save();
        renderSuccess();
    }

    @RequiresPermissions("system:role:edit")
    public void edit() {
        Role role = getBean(Role.class, "", true);
        role.update();
        renderSuccess();
    }

    @RequiresPermissions("system:role:delete")
    public void delete() {
        Integer id = getParaToInt();
        new Role().setId(id).delete();
        renderSuccess();
    }

    @RequiresPermissions("system:role:menuEdit")
    public void menuEdit() {
        Integer roleId = getParaToInt();
        Integer[] menuIds = getParaValuesToInt("menuId");
        Kv condition = Kv.create().set("role_id = ", roleId);
        List<RoleMenu> roleMenus = RoleMenu.dao.find(condition);
        for (RoleMenu roleMenu : roleMenus) {
            roleMenu.delete();
        }
        if (ArrayUtil.isNotEmpty(menuIds)) {
            for (Integer menuId : menuIds) {
                new RoleMenu().setMenuId(menuId).setRoleId(roleId).save();
            }
        }
        renderSuccess();
    }
}
