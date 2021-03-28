package com.yaowk.user.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.xiaoleilu.hutool.util.ArrayUtil;
import com.yaowk.common.controller.BaseController;
import com.yaowk.user.model.User;
import com.yaowk.user.model.UserRole;

import java.util.List;

/**
 * @authc yaowk
 * 2017/6/28
 */
@Before(Tx.class)
public class UserController extends BaseController {

    @Clear(Tx.class)
    public void index() {
        Integer id = getParaToInt();
        User user = User.dao.findById(id, "id,username");
        renderJson(user);
    }

    @Clear(Tx.class)
    public void list() {
        Integer platformId = getParaToInt("platformId");
        Kv condition = Kv.by("status != ", "0").set("action_id = ", platformId);
        Page<User> page = User.dao.paginate(getPage(), condition);
        renderJson(page);
    }

    public void add() {
        User user = getBean(User.class, "", true);
        user.save();
        updateUserRole(user.getId());
        renderSuccess();
    }

    public void edit() {
        User user = getBean(User.class, "", true);
        user.update();
        updateUserRole(user.getId());
        renderSuccess();
    }

    public void delete() {
        Integer userId = getParaToInt();
        new User().setId(userId).delete();
        renderSuccess();
    }

    /**
     * 增改用户的时候更新用户角色
     *
     * @param userId
     */
    private void updateUserRole(Integer userId) {
        Integer[] roleIds = getParaValuesToInt("roleId");
        Kv condition = Kv.by("user_id = ", userId);
        List<UserRole> userRoles = UserRole.dao.find(condition);
        for (UserRole userRole : userRoles) {
            userRole.delete();
        }
        if (ArrayUtil.isNotEmpty(roleIds)) {
            for (Integer roleId : roleIds) {
                new UserRole().setUserId(userId).setRoleId(roleId).save();
            }
        }
    }


}
