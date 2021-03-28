package com.yaowk.system.common;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.yaowk.common.kit.ScanJarStringSource;
import com.yaowk.common.plugin.SqlPlugin;
import com.yaowk.system.model._MappingKit;

/**
 * User模块数据源插件
 *
 * @authc yaowk
 * 2017/7/3
 */
public class UserSqlPlugin extends SqlPlugin {
    public UserSqlPlugin(ActiveRecordPlugin arp) {
        super(arp);
    }

    @Override
    public boolean start() {
        _MappingKit.mapping(arp);
        arp.addSqlTemplate(new ScanJarStringSource("user.sql"));
        return super.start();
    }

    @Override
    public boolean stop() {
        return super.stop();
    }
}
