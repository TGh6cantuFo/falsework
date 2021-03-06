package com.yaowk.system.model;

import com.jfinal.plugin.activerecord.SqlPara;
import com.jfinal.json.Json;
import com.yaowk.common.plugin.FindKv;
import com.yaowk.common.constant.CacheConstant;
import com.yaowk.common.kit.DbCacheKit;
import com.yaowk.common.model.base.Page;
import java.util.List;
import java.util.Map;
import com.yaowk.system.model.base.BaseUserPlatform;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class UserPlatform extends BaseUserPlatform<UserPlatform> {
	public static final UserPlatform dao = new UserPlatform().dao();
	protected static final String tableName = "sys_user_platform";

	public List<UserPlatform> find(Map condition) {
		FindKv kv = FindKv.create().setCondition(condition).setTable(tableName);
		SqlPara sqlPara = getSqlPara("find", kv);
		String key = "UserPlatform:find:" + Json.getJson().toJson(kv);
		DbCacheKit.addKey(key);
		return findByCache(CacheConstant.DB, key, sqlPara.getSql(), sqlPara.getPara());
	}

	public com.jfinal.plugin.activerecord.Page<UserPlatform> paginate(Page page, Map condition) {
		FindKv kv = FindKv.create().setCondition(condition).setTable(tableName);
		SqlPara sqlPara = getSqlPara("paginate-except", kv);
		Json json = Json.getJson();
		String key = "UserPlatform:paginate:" + json.toJson(page) + json.toJson(kv);
		DbCacheKit.addKey(key);
		return paginateByCache(CacheConstant.DB, key, page.getPageNumber(), page.getPageSize(), getSql("paginate-select"), sqlPara.getSql(), sqlPara.getPara());
	}

	@Override
	public boolean save() {
		DbCacheKit.removeCacheStarWith("UserPlatform:paginate");
		return super.save();
	}

	@Override
	public boolean update() {
		DbCacheKit.removeCacheStarWith("UserPlatform");
		return super.update();
	}

	@Override
	public boolean delete() {
		DbCacheKit.removeCacheStarWith("UserPlatform");
		return super.delete();
	}

}
