package com.yaowk.config;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.plugin.shiro.ShiroInterceptor;
import com.jfinal.ext.plugin.shiro.ShiroPlugin;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.tx.TxByMethods;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.template.Engine;
import com.yaowk.common.ext.FastJsonWithNullFactory;
import com.yaowk.common.ext.JsonRenderFactory;
import com.yaowk.common.kit.DataPluginKit;
import com.yaowk.common.plugin.CommonSqlPlugin;
import com.yaowk.system.UserRoutes;
import com.yaowk.system.common.UserSqlPlugin;
import com.yaowk.system.interceptor.PlatformIdInterceptor;

/**
 * @authc yaowk
 * 2017/7/13
 */
public class MainConfig extends JFinalConfig {

    private Routes routes;

    public static void main(String[] args) {
        JFinal.start("config/src/main/webapp", 8080, "/");
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
        routes.add(new UserRoutes());
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

        arp.setShowSql(true);
        plugins.add(arp);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {
        interceptors.add(new ShiroInterceptor());
        interceptors.add(new PlatformIdInterceptor());
        interceptors.add(new TxByMethods("add", "edit", "delete"));
    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}
