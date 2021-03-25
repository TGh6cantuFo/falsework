package com.yaowk.config;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.yaowk.common.ext.JsonRenderFactory;
import com.yaowk.common.kit.DataPluginKit;
import com.yaowk.common.plugin.CommonSqlPlugin;
import com.yaowk.user.UserRoutes;
import com.yaowk.user.common.UserSqlPlugin;

/**
 * @authc yaowk
 * 2017/7/13
 */
public class MainConfig extends JFinalConfig {

    public static void main(String[] args) {
        JFinal.start("config/src/main/webapp", 80, "/");
    }

    @Override
    public void configConstant(Constants constants) {
        constants.setRenderFactory(new JsonRenderFactory());
    }

    @Override
    public void configRoute(Routes routes) {
        routes.add(new UserRoutes());
    }

    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void configPlugin(Plugins plugins) {
        DruidPlugin druidPlugin = DataPluginKit.getDruidPlugin();
        plugins.add(druidPlugin);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);

        plugins.add(new CommonSqlPlugin(arp));
        plugins.add(new UserSqlPlugin(arp));

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
