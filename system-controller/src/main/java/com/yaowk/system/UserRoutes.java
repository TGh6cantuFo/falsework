package com.yaowk.system;

import com.yaowk.system.controller.*;

/**
 * @authc yaowk
 * 2017/7/13
 */
public class UserRoutes extends com.jfinal.config.Routes {
    @Override
    public void config() {
        add("/admin/user", UserController.class);
        add("/admin/menu", MenuController.class);
        add("/admin/role", RoleController.class);
        add("/admin/platform", PlatformController.class);
        add("/admin/auth", AuthController.class);
    }
}
