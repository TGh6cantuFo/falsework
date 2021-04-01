package com.yaowk.config;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.plugin.shiro.ShiroInterceptor;
import com.jfinal.ext.plugin.shiro.ShiroPlugin;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.tx.TxByMethodRegex;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.template.Engine;
import com.yaowk.common.ext.FastJsonWithNullFactory;
import com.yaowk.common.ext.JsonRenderFactory;
import com.yaowk.common.kit.DataPluginKit;
import com.yaowk.common.plugin.CommonSqlPlugin;
import com.yaowk.device.DeviceRoutes;
import com.yaowk.device.common.DeviceSqlPlugin;
import com.yaowk.system.SystemRoutes;
import com.yaowk.system.common.UserSqlPlugin;
import com.yaowk.weixin.common.ApiConfigInit;

/**
 * @authc yaowk
 * 2017/7/13
 */
public class MainConfig extends JFinalConfig {

    private Routes routes;

    public static void main(String[] args) {
        JFinal.start("config/src/main/webapp", 8080, "/falsework");
    }

    @Override
    public void configConstant(Constants constants) {
        constants.setDevMode(true);
        constants.setRenderFactory(new JsonRenderFactory());
        constants.setJsonFactory(new FastJsonWithNullFactory());
    }

    @Override
    public void configRoute(Routes routes) {
        this.routes = routes;
        routes.add(new SystemRoutes());
        routes.add(new DeviceRoutes());
    }

    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void configPlugin(Plugins plugins) {

        ShiroPlugin shiroPlugin = new ShiroPlugin(routes);
        shiroPlugin.setUnauthorizedUrl("/admin/auth/unauthorizedUrl");
        shiroPlugin.setSuccessUrl("/admin/auth/successUrl");
        shiroPlugin.setLoginUrl("/admin/auth/loginUrl");
        plugins.add(shiroPlugin);

        EhCachePlugin ehCachePlugin = new EhCachePlugin();
        plugins.add(ehCachePlugin);

        DruidPlugin druidPlugin = DataPluginKit.getDruidPlugin();
        plugins.add(druidPlugin);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);

        plugins.add(new CommonSqlPlugin(arp));
        plugins.add(new UserSqlPlugin(arp));
        plugins.add(new DeviceSqlPlugin(arp));

        arp.setShowSql(true);
        plugins.add(arp);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {
        interceptors.add(new ShiroInterceptor());
//        interceptors.add(new TxByMethods("add", "edit", "delete"));
        interceptors.add(new TxByMethodRegex("add|edit|delete|.+Edit"));
    }

    @Override
    public void configHandler(Handlers handlers) {

    }

    @Override
    public void afterJFinalStart() {
        super.afterJFinalStart();
        ApiConfigInit.init();
    }
}
