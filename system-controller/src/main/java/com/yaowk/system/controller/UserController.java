package com.yaowk.system.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.kit.Kv;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.xiaoleilu.hutool.util.ArrayUtil;
import com.yaowk.common.controller.BaseController;
import com.yaowk.system.model.User;
import com.yaowk.system.model.UserRole;
import com.yaowk.system.shiro.PasswordKit;
import com.yaowk.system.shiro.TokenKit;
import com.yaowk.system.validator.UserIdValidator;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.util.List;

/**
 * @authc yaowk
 * 2017/6/28
 */
@RequiresAuthentication
@RequiresPermissions("system:user")
@Before(UserIdValidator.class)
public class UserController extends BaseController {

    public void index() {
        Integer id = getParaToInt();
        User user = User.dao.findByIdLoadColumns(id, "id,username");
        user.setPassword(null);
        renderJson(user);
    }

    @Clear(UserIdValidator.class)
    public void list() {
        Kv condition = Kv.by("status != ", "0").set(" parent_id = ", TokenKit.getUserId());
        Page<User> page = User.dao.paginate(getPage(), condition);
        renderJson(page);
    }

    @Clear(UserIdValidator.class)
    @RequiresPermissions("system:user:add")
    public void add() {
        User user = getBean(User.class, "", true);
        user.setPassword(PasswordKit.encrypt(user.getPassword()));
        user.save();
        renderSuccess();
    }

    @RequiresPermissions("system:user:edit")
    public void edit() {
        User user = getBean(User.class, "", true);
        if (StrKit.notBlank(user.getPassword())) {
            user.setPassword(PasswordKit.encrypt(user.getPassword()));
        }
        user.update();
        renderSuccess();
    }

    @RequiresPermissions("system:user:delete")
    public void delete() {
        Integer userId = getParaToInt();
        new User().setId(userId).delete();
        renderSuccess();
    }

    /**
     * ???????????????????????????????????????
     */
    @RequiresPermissions("system:user:roleEdit")
    public void roleEdit() {
        Integer userId = getParaToInt();
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
