package com.yaowk.common.kit;

import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.druid.DruidPlugin;

import javax.sql.DataSource;

/**
 * 生成数据源工具类
 *
 * @authc yaowk
 * 2017/6/27
 */
public class DataPluginKit {

    public static DruidPlugin getDruidPlugin() {
        Prop prop = PropKit.use("jdbc.properties");
        DruidPlugin druidPlugin = new DruidPlugin(prop.get("jdbcUrl"), prop.get("user"), prop.get("password"), prop.get("driverClass"));
        return druidPlugin;
    }

    public static DataSource getDruidDataSource() {
        DruidPlugin druidPlugin = getDruidPlugin();
        druidPlugin.start();
        return druidPlugin.getDataSource();
    }
}