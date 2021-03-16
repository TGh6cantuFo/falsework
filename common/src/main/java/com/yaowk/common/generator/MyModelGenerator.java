package com.yaowk.common.generator;

import com.jfinal.plugin.activerecord.generator.ModelGenerator;
import com.jfinal.plugin.activerecord.generator.TableMeta;

/**
 * @authc yaowk
 * 2017/7/3
 */
public class MyModelGenerator extends ModelGenerator {

    protected String findTemplate = "\tpublic List<%s> find(Map condition) {%n\t\tFindKv kv = FindKv.create().setCondition(condition).setTable(tableName);%n\t\tSqlPara sqlPara = getSqlPara(\"find\", kv);%n\t\treturn find(sqlPara);%n\t}%n%n";
    protected String tableNameTemplate = "\tprotected static final String tableName = \"%s\";%n%n";
    protected String myImportTemplate = "import %s%n";

    public MyModelGenerator(String modelPackageName, String baseModelPackageName, String modelOutputDir) {
        super(modelPackageName, baseModelPackageName, modelOutputDir);
    }

    @Override
    protected void genModelContent(TableMeta tableMeta) {
        StringBuilder ret = new StringBuilder();
        this.genPackage(ret);
        this.getMyImport("com.yaowk.common.plugin.FindKv;", ret);
        this.getMyImport("com.jfinal.plugin.activerecord.SqlPara;", ret);
        this.getMyImport("java.util.List;", ret);
        this.getMyImport("java.util.Map;;", ret);
        this.genImport(tableMeta, ret);
        this.genClassDefine(tableMeta, ret);
        this.genDao(tableMeta, ret);
        this.getTableName(tableMeta, ret);
        this.getFindFunction(tableMeta, ret);
        ret.append(String.format("}%n", new Object[0]));
        tableMeta.modelContent = ret.toString();
    }

    protected void getFindFunction(TableMeta tableMeta, StringBuilder ret) {
        ret.append(String.format(findTemplate, new Object[] { tableMeta.modelName }));
    }

    protected void getTableName(TableMeta tableMeta, StringBuilder ret) {
        ret.append(String.format(tableNameTemplate, new Object[] { tableMeta.name }));
    }

    protected void getMyImport(String packageString, StringBuilder ret) {
        ret.append(String.format(myImportTemplate, new Object[] { packageString }));
    }
}
