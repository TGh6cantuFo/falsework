package com.yaowk.common.generator;

import com.jfinal.plugin.activerecord.generator.MetaBuilder;

import javax.sql.DataSource;
import java.util.List;

/**
 * @authc yaowk
 * 2017/6/27
 */
public class BaseMetaBuilder extends MetaBuilder {

    protected List<String> tableNames;

    public BaseMetaBuilder(DataSource dataSource, List<String> tableNames) {
        super(dataSource);
        this.tableNames = tableNames;
    }

    @Override
    protected boolean isSkipTable(String tableName) {
        return !tableNames.contains(tableName);
    }
}
