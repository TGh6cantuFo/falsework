package com.yaowk.user;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.yaowk.common.ext.JsonRenderFactory;
import com.yaowk.common.util.DataPluginKit;
import com.yaowk.common.util.ScanJarStringSource;
import com.yaowk.user.controller.UserController;
import com.yaowk.user.model._MappingKit;

/**
 * @authc yaowk
 * 2017/6/28
 */
public class Config extends JFinalConfig {

    public static void main(String[] args) {
        JFinal.start("user-controller/src/main/webapp", 80, "/");
    }

    @Override
    public void configConstant(Constants constants) {
        constants.setRenderFactory(new JsonRenderFactory());
    }

    @Override
    public void configRoute(Routes routes) {
        routes.add("/", UserController.class);
    }

    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void configPlugin(Plugins plugins) {
        DruidPlugin druidPlugin = DataPluginKit.getDruidPlugin();
        plugins.add(druidPlugin);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        _MappingKit.mapping(arp);
        arp.getEngine().addSharedFunction(new ScanJarStringSource("common.sql"));
        arp.addSqlTemplate(new ScanJarStringSource("user.sql"));
        arp.setShowSql(true);
        plugins.add(arp);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}