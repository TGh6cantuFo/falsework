package com.yaowk.system.model;

import com.jfinal.json.Json;
import com.jfinal.plugin.activerecord.SqlPara;
import com.yaowk.common.constant.CacheConstant;
import com.yaowk.common.kit.DbCacheKit;
import com.yaowk.common.model.base.Page;
import com.yaowk.common.plugin.FindKv;
import com.yaowk.system.model.base.BasePlatform;

import java.util.List;
import java.util.Map;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Platform extends BasePlatform<Platform> {
    public static final Platform dao = new Platform().dao();
    protected static final String tableName = "sys_platform";

    public List<Platform> find(Map condition) {
        FindKv kv = FindKv.create().setCondition(condition).setTable(tableName);
        SqlPara sqlPara = getSqlPara("find", kv);
        String key = "Platform:find:" + Json.getJson().toJson(kv);
        DbCacheKit.addKey(key);
        return findByCache(CacheConstant.DB, key, sqlPara.getSql(), sqlPara.getPara());
    }

    public com.jfinal.plugin.activerecord.Page<Platform> paginate(Page page, Map condition) {
        FindKv kv = FindKv.create().setCondition(condition).setTable(tableName);
        SqlPara sqlPara = getSqlPara("paginate-except", kv);
        Json json = Json.getJson();
        String key = "Platform:paginate:" + json.toJson(page) + json.toJson(kv);
        DbCacheKit.addKey(key);
        return paginateByCache(CacheConstant.DB, key, page.getPageNumber(), page.getPageSize(), getSql("paginate-select"), sqlPara.getSql(), sqlPara.getPara());
    }

    @Override
    public boolean save() {
        DbCacheKit.removeCacheStarWith("Platform:paginate");
        return super.save();
    }

    @Override
    public boolean update() {
        DbCacheKit.removeCacheStarWith("Platform");
        return super.update();
    }

    @Override
    public boolean delete() {
        DbCacheKit.removeCacheStarWith("Platform");
        return setStatus(STATUS_DELETE).update();
    }

    public static final Integer STATUS_NORMAL = 1; // 正常
    public static final Integer STATUS_DELETE = 0; // 删除

}