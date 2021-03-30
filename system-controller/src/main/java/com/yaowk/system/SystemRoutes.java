package com.yaowk.system;

import com.jfinal.config.Routes;
import com.yaowk.system.controller.*;

/**
 * @authc yaowk
 * 2017/7/13
 */
public class SystemRoutes extends Routes {
    @Override
    public void config() {
        add("/admin/user", UserController.class);
        add("/admin/menu", MenuController.class);
        add("/admin/role", RoleController.class);
        add("/admin/platform", PlatformController.class);
        add("/admin/auth", AuthController.class);
    }
}
