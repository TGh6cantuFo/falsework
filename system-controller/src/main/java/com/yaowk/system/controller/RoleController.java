package com.yaowk.system.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.xiaoleilu.hutool.util.ArrayUtil;
import com.yaowk.common.controller.BaseController;
import com.yaowk.system.interceptor.PlatformIdInterceptor;
import com.yaowk.system.model.Menu;
import com.yaowk.system.model.Role;
import com.yaowk.system.model.RoleMenu;
import com.yaowk.system.shiro.TokenKit;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @authc yaowk
 * 2017/7/14
 */
@RequiresPermissions("system:role")
@Clear(PlatformIdInterceptor.class)
public class RoleController extends BaseController {

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

    public void list() {
        List<Role> roleList = Role.dao.find(Kv.create().set("create_user_id = ", TokenKit.getUserId()));
        renderJson(roleList);
    }

    @RequiresPermissions("system:role:add")
    public void add() {
        Role role = getBean(Role.class, "", true);
        role.setCreateUserId(TokenKit.getUserId());
        role.save();
        updateRoleMenu(role.getId());
        renderSuccess();
    }

    @RequiresPermissions("system:role:edit")
    public void edit() {
        Role role = getBean(Role.class, "", true);
        role.update();
        updateRoleMenu(role.getId());
        renderSuccess();
    }

    @RequiresPermissions("system:role:delete")
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
