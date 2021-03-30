package com.yaowk.device;

import com.jfinal.config.Routes;
import com.yaowk.device.controller.DeviceController;
import com.yaowk.device.controller.GoodsController;

/**
 * @authc yaowk
 * 2017/7/17
 */
public class DeviceRoutes extends Routes {
    @Override
    public void config() {
        add("/admin/device", DeviceController.class);
        add("/admin/goods", GoodsController.class);
    }
}
