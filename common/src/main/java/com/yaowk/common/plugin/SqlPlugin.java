package com.yaowk.common.plugin;

import com.jfinal.plugin.IPlugin;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Sql插件抽象类
 *
 * @authc yaowk
 * 2017/7/3
 */
public abstract class SqlPlugin implements IPlugin {

    protected ActiveRecordPlugin arp;

    public SqlPlugin(ActiveRecordPlugin arp) {
        this.arp = arp;
    }

    @Override
    public boolean start() {
        return true;
    }

    @Override
    public boolean stop() {
        return true;
    }
}
