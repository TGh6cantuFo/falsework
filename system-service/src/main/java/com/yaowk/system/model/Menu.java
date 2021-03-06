package com.yaowk.system.model;

import com.jfinal.json.Json;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.SqlPara;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.yaowk.common.constant.CacheConstant;
import com.yaowk.common.kit.DbCacheKit;
import com.yaowk.common.model.base.Page;
import com.yaowk.common.plugin.FindKv;
import com.yaowk.system.model.base.BaseMenu;

import java.util.*;

;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Menu extends BaseMenu<Menu> {
    public static final Menu dao = new Menu().dao();
    protected static final String tableName = "sys_menu";

    public List<Menu> find(Map condition) {
        FindKv kv = FindKv.create().setCondition(condition).setTable(tableName);
        SqlPara sqlPara = getSqlPara("find", kv);
        String key = "Menu:find:" + Json.getJson().toJson(kv);
        DbCacheKit.addKey(key);
        return findByCache(CacheConstant.DB, key, sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Menu> findByRoleId(Integer roleId) {
        SqlPara sqlPara = getSqlPara("menu.findByRoleId", roleId);
        String key = "Menu:findByRoleId:" + roleId;
        DbCacheKit.addKey(key);
        return findByCache(CacheConstant.DB, key, sqlPara.getSql(), sqlPara.getPara());
    }

    public com.jfinal.plugin.activerecord.Page<Menu> paginate(Page page, Map condition) {
        FindKv kv = FindKv.create().setCondition(condition).setTable(tableName);
        SqlPara sqlPara = getSqlPara("paginate-except", kv);
        Json json = Json.getJson();
        String key = "Menu:paginate:" + json.toJson(page) + json.toJson(kv);
        DbCacheKit.addKey(key);
        return paginateByCache(CacheConstant.DB, key, page.getPageNumber(), page.getPageSize(), getSql("paginate-select"), sqlPara.getSql(), sqlPara.getPara());
    }

    @Override
    public boolean save() {
        DbCacheKit.removeCacheStarWith("Menu:paginate");
        return super.save();
    }

    @Override
    public boolean update() {
        DbCacheKit.removeCacheStarWith("Menu");
        return super.update();
    }

    @Override
    public boolean delete() {
        Kv condition = Kv.by("menu_id = ", getId());
        List<RoleMenu> roleMenus = RoleMenu.dao.find(condition);
        for (RoleMenu roleMenu : roleMenus) {
            roleMenu.delete();
        }
        DbCacheKit.removeCacheStarWith("Menu");
        return super.delete();
    }

    /**
     * ?????????????????????
     *
     * @param userId
     * @return
     */
    public List<Menu> findMenuByUserId(Integer userId) {
        Set<Menu> menuList = new HashSet<>();
        List<Role> roles = Role.dao.findByUserId(userId);
        if (CollectionUtil.isNotEmpty(roles)) {
            for (Role role : roles) {
                List<Menu> menus = Menu.dao.findByRoleId(role.getId());
                if (CollectionUtil.isNotEmpty(menus)) {
                    for (Menu menu : menus) {
                        menuList.add(menu.clone());
                    }
                }
            }
        }
        return CollectionUtil.newArrayList(menuList);
    }

    /**
     * ?????????????????????????????????
     *
     * @param menuList
     * @return
     */
    public List<Menu> parseMenu(List<Menu> menuList) {
        List<Menu> menus = new ArrayList<>();
        for (Menu m : menuList) {
            if (m.getParentId() == 0) {
                menus.add(m);
                findMenuByParentId(menuList, m, m.getId());
            }
        }
        return menus;
    }

    private void findMenuByParentId(List<Menu> menuList, Menu menu, Integer parentId) {
        menu.setChildren(new ArrayList<>());
        for (Menu m : menuList) {
            if (m.getParentId() == parentId) {
                menu.getChildren().add(m);
                findMenuByParentId(menuList, m, m.getId());
            }
        }
    }

    private List<Menu> children;
    private Menu parent;
    private boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    @Override
    public Menu clone() {
        return new Menu()
                .setId(getId())
                .setName(getName())
                .setCreateTime(getCreateTime())
                .setDescription(getDescription())
                .setOpened(getOpened())
                .setParentId(getParentId())
                .setPermission(getPermission())
                .setSort(getSort())
                .setStatus(getStatus())
                .setType(getType())
                .setUrl(getUrl());
    }
}
