package com.yaowk.common.generator;

import com.jfinal.plugin.activerecord.generator.ModelGenerator;
import com.jfinal.plugin.activerecord.generator.TableMeta;

/**
 * @authc yaowk
 * 2017/7/3
 */
public class MyModelGenerator extends ModelGenerator {

    protected String findTemplate = "\tpublic List<%s> find(FindKv kv) {%n\t\tSqlPara sqlPara = getSqlPara(\"find\", kv);%n\t\treturn find(sqlPara);%n}%n%n";
    protected String tableNamePrefixTemplate = "\tprotected static final String tableNamePrefix = \"%s\";%n%n";
    protected String tableNameFunctionTemplate = "\tpublic static String tableName(String table) {%n\t\treturn tableNamePrefix + table;%n\t\t}%n%n";
    protected String tableNamePrefix = "";
    protected String myImportTemplate = "import %s%n";

    public MyModelGenerator(String modelPackageName, String baseModelPackageName, String modelOutputDir, String tableNamePrefix) {
        super(modelPackageName, baseModelPackageName, modelOutputDir);
        this.tableNamePrefix = tableNamePrefix;
    }

    @Override
    protected void genModelContent(TableMeta tableMeta) {
        StringBuilder ret = new StringBuilder();
        this.genPackage(ret);
        this.getMyImport("com.yaowk.common.plugin.FindKv;", ret);
        this.getMyImport("com.jfinal.plugin.activerecord.SqlPara;", ret);
        this.getMyImport("java.util.List;", ret);
        this.genImport(tableMeta, ret);
        this.genClassDefine(tableMeta, ret);
        this.genDao(tableMeta, ret);
        this.getTableNamePrefix(ret);
        this.getTableNameFunction(ret);
        this.getFindFunction(tableMeta, ret);
        ret.append(String.format("}%n", new Object[0]));
        tableMeta.modelContent = ret.toString();
    }

    protected void getFindFunction(TableMeta tableMeta, StringBuilder ret) {
        ret.append(String.format(findTemplate, new Object[] { tableMeta.modelName }));
    }

    protected void getTableNamePrefix(StringBuilder ret) {
        ret.append(String.format(tableNamePrefixTemplate, new Object[] { tableNamePrefix }));
    }

    protected void getTableNameFunction(StringBuilder ret) {
        ret.append(String.format(tableNameFunctionTemplate));
    }

    protected void getMyImport(String packageString, StringBuilder ret) {
        ret.append(String.format(myImportTemplate, new Object[] { packageString }));
    }
}
