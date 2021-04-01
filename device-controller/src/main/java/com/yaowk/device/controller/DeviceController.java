package com.yaowk.device.controller;

import com.jfinal.kit.Kv;
import com.yaowk.common.controller.BaseController;
import com.yaowk.device.model.Device;
import com.yaowk.device.model.DeviceUser;
import com.yaowk.system.shiro.TokenKit;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.util.ArrayList;
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
        Integer userId = TokenKit.getUserId();
        Kv condition = Kv.by("user_id = ", userId);
        List<DeviceUser> deviceUserList = DeviceUser.dao.find(condition);
        List<Device> devices = new ArrayList<>();
        for (DeviceUser deviceUser : deviceUserList) {
            condition = Kv.by("id = ", deviceUser.getDeviceId()).set("status = ", Device.STATUS_NORMAL);
            devices.add(Device.dao.findFirst(condition));
        }
        renderJson(devices);
    }

    @RequiresPermissions("device:device:add")
    public void add() {
        Device device = getBean(Device.class, "", true);
        device.save();
        new DeviceUser().setDeviceId(device.getId()).setUserId(TokenKit.getUserId()).save();
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

    public void bandUser() {
        Device device = getBean(Device.class, "", true);
        device.update();
    }

}
