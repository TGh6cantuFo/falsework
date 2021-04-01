package com.yaowk.system.model;

import com.jfinal.json.Json;
import com.jfinal.plugin.activerecord.SqlPara;
import com.yaowk.common.constant.CacheConstant;
import com.yaowk.common.kit.DbCacheKit;
import com.yaowk.common.plugin.FindKv;
import com.yaowk.system.model.base.BaseRoleMenu;

import java.util.List;
import java.util.Map;

;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class RoleMenu extends BaseRoleMenu<RoleMenu> {
    public static final RoleMenu dao = new RoleMenu().dao();
    protected static final String tableName = "sys_role_menu";

    public List<RoleMenu> find(Map condition) {
        FindKv kv = FindKv.create().setCondition(condition).setTable(tableName);
        SqlPara sqlPara = getSqlPara("find", kv);
        String key = "RoleMenu:find:" + Json.getJson().toJson(kv);
        DbCacheKit.addKey(key);
        return findByCache(CacheConstant.DB, key, sqlPara.getSql(), sqlPara.getPara());
    }

    @Override
    public boolean save() {
        DbCacheKit.removeCache("Menu:findByRoleId:" + getRoleId());
        DbCacheKit.removeCacheStarWith("RoleMenu");
        return super.save();
    }

    @Override
    public boolean delete() {
        DbCacheKit.removeCache("Menu:findByRoleId:" + getRoleId());
        DbCacheKit.removeCacheStarWith("RoleMenu");
        return super.delete();
    }
}