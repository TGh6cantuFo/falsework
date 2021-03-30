package com.yaowk.device.common;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.yaowk.common.kit.ScanJarStringSource;
import com.yaowk.common.plugin.SqlPlugin;
import com.yaowk.device.model._MappingKit;

/**
 * @authc yaowk
 * 2017/7/17
 */
public class DeviceSqlPlugin extends SqlPlugin {
    public DeviceSqlPlugin(ActiveRecordPlugin arp) {
        super(arp);
    }

    @Override
    public boolean start() {
        _MappingKit.mapping(arp);
        arp.addSqlTemplate(new ScanJarStringSource("device.sql"));
        return super.start();
    }
}
