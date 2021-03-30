package com.yaowk.device.controller;

import com.jfinal.kit.Kv;
import com.yaowk.common.controller.BaseController;
import com.yaowk.device.model.Device;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.util.List;

/**
 * 设备管理接口
 *
 * @authc yaowk
 * 2017/7/17
 */
@RequiresAuthentication
@RequiresPermissions("device:device")
public class DeviceController extends BaseController {

    public void index() {
        Integer id = getParaToInt();
        Device device = Device.dao.findById(id);
        renderJson(device);
    }

    public void list() {
        Integer platformId = getParaToInt("platformId");
        Kv condition = Kv.by("platform_id = ", platformId).set("status != ", Device.STATUS_DELETE);
        List<Device> devices = Device.dao.find(condition);
        renderJson(devices);
    }

    @RequiresPermissions("device:device:add")
    public void add() {
        Device device = getBean(Device.class, "", true);
        device.save();
        renderSuccess();
    }

    @RequiresPermissions("device:device:edit")
    public void edit() {
        Device device = getBean(Device.class, "", true);
        device.update();
        renderSuccess();
    }

    @RequiresPermissions("device:device:delete")
    public void delete() {
        Integer id = getParaToInt();
        new Device().setId(id).delete();
        renderSuccess();
    }

}
