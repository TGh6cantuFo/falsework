package com.yaowk.common.generator;

import com.jfinal.plugin.activerecord.generator.ModelGenerator;
import com.jfinal.plugin.activerecord.generator.TableMeta;

/**
 * Model类生成扩展
 *
 * @authc yaowk
 * 2017/7/3
 */
public class MyModelGenerator extends ModelGenerator {

    protected String findTemplate = "\tpublic List<%s> find(Map condition) {%n\t\tFindKv kv = FindKv.create().setCondition(condition).setTable(tableName);%n\t\tSqlPara sqlPara = getSqlPara(\"find\", kv);%n\t\tString key = \"%s:find:\" + Json.getJson().toJson(kv);%n\t\tDbCacheKit.addKey(key);%n\t\treturn findByCache(CacheConstant.DB, key, sqlPara.getSql(), sqlPara.getPara());%n\t}%n%n";
    protected String paginateTemplate = "\tpublic com.jfinal.plugin.activerecord.Page<%s> paginate(Page page, Map condition) {%n\t\tFindKv kv = FindKv.create().setCondition(condition).setTable(tableName);%n\t\tSqlPara sqlPara = getSqlPara(\"paginate-except\", kv);%n\t\tJson json = Json.getJson();%n\t\tString key = \"%s:paginate:\" + json.toJson(page) + json.toJson(kv);%n\t\tDbCacheKit.addKey(key);%n\t\treturn paginateByCache(CacheConstant.DB, key, page.getPageNumber(), page.getPageSize(), getSql(\"paginate-select\"), sqlPara.getSql(), sqlPara.getPara());%n\t}%n%n";
    protected String saveTemplate = "\t@Override%n\tpublic boolean save() {%n\t\tDbCacheKit.removeCacheStarWith(\"%s:paginate\");%n\t\treturn super.save();%n\t}%n%n";
    protected String updateTemplate = "\t@Override%n\tpublic boolean update() {%n\t\tDbCacheKit.removeCacheStarWith(\"%s\");%n\t\treturn super.update();%n\t}%n%n";
    protected String deleteTemplate = "\t@Override%n\tpublic boolean delete() {%n\t\tDbCacheKit.removeCacheStarWith(\"%s\");%n\t\treturn super.delete();%n\t}%n%n";
    protected String tableNameTemplate = "\tprotected static final String tableName = \"%s\";%n%n";
    protected String myImportTemplate = "import %s%n";

    public MyModelGenerator(String modelPackageName, String baseModelPackageName, String modelOutputDir) {
        super(modelPackageName, baseModelPackageName, modelOutputDir);
    }

    @Override
    protected void genModelContent(TableMeta tableMeta) {
        StringBuilder ret = new StringBuilder();
        this.genPackage(ret);
        this.getMyImport("com.jfinal.plugin.activerecord.SqlPara;", ret);
        this.getMyImport("com.jfinal.json.Json;", ret);
        this.getMyImport("com.yaowk.common.plugin.FindKv;", ret);
        this.getMyImport("com.yaowk.common.constant.CacheConstant;", ret);
        this.getMyImport("com.yaowk.common.kit.DbCacheKit;", ret);
        this.getMyImport("com.yaowk.common.model.base.Page;", ret);
        this.getMyImport("java.util.List;", ret);
        this.getMyImport("java.util.Map;", ret);
        this.genImport(tableMeta, ret);
        this.genClassDefine(tableMeta, ret);
        this.genDao(tableMeta, ret);
        this.getTableName(tableMeta, ret);
        this.getFindFunction(tableMeta, ret);
        this.getPaginate(tableMeta, ret);
        this.getSave(tableMeta, ret);
        this.getUpdate(tableMeta, ret);
        this.getDelete(tableMeta, ret);
        ret.append(String.format("}%n", new Object[0]));
        tableMeta.modelContent = ret.toString();
    }

    protected void getFindFunction(TableMeta tableMeta, StringBuilder ret) {
        ret.append(String.format(findTemplate, new Object[] { tableMeta.modelName, tableMeta.modelName }));
    }

    protected void getTableName(TableMeta tableMeta, StringBuilder ret) {
        ret.append(String.format(tableNameTemplate, new Object[] { tableMeta.name }));
    }

    protected void getMyImport(String packageString, StringBuilder ret) {
        ret.append(String.format(myImportTemplate, new Object[] { packageString }));
    }

    protected void getPaginate(TableMeta tableMeta, StringBuilder ret) {
        ret.append(String.format(paginateTemplate, new Object[] { tableMeta.modelName, tableMeta.modelName }));
    }

    protected void getSave(TableMeta tableMeta, StringBuilder ret) {
        ret.append(String.format(saveTemplate, new Object[] { tableMeta.modelName }));
    }

    protected void getUpdate(TableMeta tableMeta, StringBuilder ret) {
        ret.append(String.format(updateTemplate, new Object[] { tableMeta.modelName }));
    }

    protected void getDelete(TableMeta tableMeta, StringBuilder ret) {
        ret.append(String.format(deleteTemplate, new Object[] { tableMeta.modelName }));
    }
}
