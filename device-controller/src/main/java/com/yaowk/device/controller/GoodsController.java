package com.yaowk.device.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.kit.Kv;
import com.yaowk.common.controller.BaseController;
import com.yaowk.device.model.Goods;
import com.yaowk.device.validator.DeviceIdValidator;
import com.yaowk.device.validator.GoodsIdValidator;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.util.List;

/**
 * 商品管理接口
 *
 * @authc yaowk
 * 2017/7/17
 */
@RequiresAuthentication
@RequiresPermissions("device:goods")
@Before(GoodsIdValidator.class)
public class GoodsController extends BaseController {

    public void index() {
        Integer id = getParaToInt();
        Goods goods = Goods.dao.findById(id);
        renderJson(goods);
    }

    @Clear(GoodsIdValidator.class)
    @Before(DeviceIdValidator.class)
    public void list() {
        Integer deviceId = getParaToInt();
        Kv condition = Kv.by("device_id = ", deviceId).set("status != ", Goods.STATUS_DELETE);
        List<Goods> goodsList = Goods.dao.find(condition);
        renderJson(goodsList);
    }

    @Clear(GoodsIdValidator.class)
    @RequiresPermissions("device:goods:add")
    public void add() {
        Goods goods = getBean(Goods.class, "", true);
        goods.save();
        renderSuccess();
    }

    @RequiresPermissions("device:goods:edit")
    public void edit() {
        Goods goods = getBean(Goods.class, "", true);
        goods.update();
        renderSuccess();
    }

    @RequiresPermissions("device:goods:delete")
    public void delete() {
        Integer id = getParaToInt();
        new Goods().setId(id).delete();
        renderSuccess();
    }
}
