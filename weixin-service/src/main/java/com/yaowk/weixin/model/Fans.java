package com.yaowk.weixin.model;

import com.jfinal.plugin.activerecord.SqlPara;
import com.jfinal.json.Json;
import com.yaowk.common.plugin.FindKv;
import com.yaowk.common.constant.CacheConstant;
import com.yaowk.common.kit.DbCacheKit;
import com.yaowk.common.model.base.Page;
import java.util.List;
import java.util.Map;
import com.yaowk.weixin.model.base.BaseFans;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Fans extends BaseFans<Fans> {
	public static final Fans dao = new Fans().dao();
	protected static final String tableName = "sys_fans";

	public List<Fans> find(Map condition) {
		FindKv kv = FindKv.create().setCondition(condition).setTable(tableName);
		SqlPara sqlPara = getSqlPara("find", kv);
		String key = "Fans:find:" + Json.getJson().toJson(kv);
		DbCacheKit.addKey(key);
		return findByCache(CacheConstant.DB, key, sqlPara.getSql(), sqlPara.getPara());
	}

	public com.jfinal.plugin.activerecord.Page<Fans> paginate(Page page, Map condition) {
		FindKv kv = FindKv.create().setCondition(condition).setTable(tableName);
		SqlPara sqlPara = getSqlPara("paginate-except", kv);
		Json json = Json.getJson();
		String key = "Fans:paginate:" + json.toJson(page) + json.toJson(kv);
		DbCacheKit.addKey(key);
		return paginateByCache(CacheConstant.DB, key, page.getPageNumber(), page.getPageSize(), getSql("paginate-select"), sqlPara.getSql(), sqlPara.getPara());
	}

	@Override
	public boolean save() {
		DbCacheKit.removeCacheStarWith("Fans:paginate");
		return super.save();
	}

	@Override
	public boolean update() {
		DbCacheKit.removeCacheStarWith("Fans");
		return super.update();
	}

	@Override
	public boolean delete() {
		DbCacheKit.removeCacheStarWith("Fans");
		return super.delete();
	}

}
