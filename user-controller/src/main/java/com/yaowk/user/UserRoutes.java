package com.yaowk.user;

import com.yaowk.user.controller.MenuController;
import com.yaowk.user.controller.RoleController;
import com.yaowk.user.controller.UserController;

/**
 * @authc yaowk
 * 2017/7/13
 */
public class UserRoutes extends com.jfinal.config.Routes {
    @Override
    public void config() {
        add("/admin/user", UserController.class);
        add("/admin/menu", MenuController.class);
        add("admin/role", RoleController.class);
    }
}
