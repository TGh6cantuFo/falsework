package com.yaowk.common.plugin;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.yaowk.common.kit.ScanJarStringSource;

/**
 * Common模块的Sql插件
 *
 * @authc yaowk
 * 2017/7/3
 */
public class CommonSqlPlugin extends SqlPlugin {
    public CommonSqlPlugin(ActiveRecordPlugin arp) {
        super(arp);
    }

    @Override
    public boolean start() {
        arp.getEngine().addSharedFunction(new ScanJarStringSource("common.sql"));
        arp.addSqlTemplate(new ScanJarStringSource("common.sql"));
        return super.start();
    }

    @Override
    public boolean stop() {
        return super.stop();
    }
}
