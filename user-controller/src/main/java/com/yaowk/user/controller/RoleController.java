package com.yaowk.user.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.xiaoleilu.hutool.util.ArrayUtil;
import com.yaowk.common.controller.BaseController;
import com.yaowk.user.model.Menu;
import com.yaowk.user.model.Role;
import com.yaowk.user.model.RoleMenu;
import com.yaowk.user.shiro.TokenKit;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @authc yaowk
 * 2017/7/14
 */
@Before(Tx.class)
public class RoleController extends BaseController {

    @Clear(Tx.class)
    public void index() {
        Integer id = getParaToInt();
        Role role = Role.dao.findById(id);

        // 整理菜单
        List<Menu> userMenuList = Menu.dao.findMenuByUserId(TokenKit.getUserId());
        List<Menu> roleMenuList = Menu.dao.findByRoleId(id);
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

        Kv result = Kv.create().set("role", role).set("menus", userMenuList);
        renderJson(result);
    }

    @Clear(Tx.class)
    public void list() {
        List<Role> roleList = Role.dao.find(Kv.create());
        renderJson(roleList);
    }

    public void add() {
        Role role = getBean(Role.class, "", true);
        role.save();
        updateRoleMenu(role.getId());
        renderSuccess();
    }

    public void edit() {
        Role role = getBean(Role.class, "", true);
        role.update();
        updateRoleMenu(role.getId());
        renderSuccess();
    }

    public void delete() {
        Integer id = getParaToInt();
        new Role().setId(id).delete();
        renderSuccess();
    }


    /**
     * 角色修改维护角色权限关系
     *
     * @param roleId
     */
    private void updateRoleMenu(Integer roleId) {
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
    }
}
