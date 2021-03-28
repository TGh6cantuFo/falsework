package com.yaowk.weixin.common;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.yaowk.common.kit.ScanJarStringSource;
import com.yaowk.common.plugin.SqlPlugin;
import com.yaowk.weixin.model._MappingKit;

/**
 * @authc yaowk
 * 2017/7/14
 */
public class WeixinSqlPlugin extends SqlPlugin {
    public WeixinSqlPlugin(ActiveRecordPlugin arp) {
        super(arp);
    }

    @Override
    public boolean start() {
        _MappingKit.mapping(arp);
        arp.addSqlTemplate(new ScanJarStringSource("weixin.sql"));
        return super.start();
    }

    @Override
    public boolean stop() {
        return super.stop();
    }
}
