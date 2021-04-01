package com.yaowk.system.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.xiaoleilu.hutool.util.ArrayUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.yaowk.common.controller.BaseController;
import com.yaowk.system.model.Platform;
import com.yaowk.system.model.User;
import com.yaowk.system.model.UserPlatform;
import com.yaowk.system.shiro.TokenKit;
import com.yaowk.system.validator.PlatformIdValidator;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.util.*;

/**
 * @authc yaowk
 * 2017/7/16
 */
@RequiresAuthentication
@RequiresPermissions("system:platform")
@Before(PlatformIdValidator.class)
public class PlatformController extends BaseController {

    public void index() {
        Integer id = getParaToInt();
        Platform platform = Platform.dao.findById(id);
        renderJson(platform);
    }

    @Clear(PlatformIdValidator.class)
    public void list() {
        Kv condition = Kv.by("user_id = ", TokenKit.getUserId());
        List<UserPlatform> userPlatforms = UserPlatform.dao.find(condition);
        List<Platform> platforms = new ArrayList<>();
        for (UserPlatform userPlatform : userPlatforms) {
            Platform platform = Platform.dao.findByIdLoadColumns(userPlatform.getPlatformId(), "id,name,status");
            if (Platform.STATUS_NORMAL.equals(platform.getStatus())) {
                platforms.add(platform);
            }
        }
        renderJson(platforms);
    }

    public void userList() {
        List<User> userList = User.dao.find(Kv.by("parent_id = ", TokenKit.getUserId()));
        List<UserPlatform> userPlatformList = UserPlatform.dao.find(Kv.by("platform_id = ", getParaToInt()));
        Set<Integer> userIdSet = new HashSet<>();
        for (UserPlatform userPlatform : userPlatformList) {
            userIdSet.add(userPlatform.getUserId());
        }
        for (User user : userList) {
            if (userIdSet.contains(user.getId())) {
                user.set("checked", true);
            }
            else {
                user.set("checked", false);
            }
        }
        renderJson(userList);
    }

    @Clear(PlatformIdValidator.class)
    @RequiresPermissions("system:platform:add")
    public void add() {
        Platform platform = getBean(Platform.class, "", true);
        platform.save();
        renderSuccess();
    }

    @RequiresPermissions("system:platform:edit")
    public void edit() {
        Platform platform = getBean(Platform.class, "", true);
        platform.update();
        renderSuccess();
    }

    @RequiresPermissions("system:platform:delete")
    public void delete() {
        Integer platformId = getParaToInt();
        new Platform().setId(platformId).delete();
        renderSuccess();
    }

    /**
     * 维护平台和用户关系
     */
    @RequiresPermissions("system:platform:userEdit")
    public void userEdit() {
        Integer platformId = getParaToInt();
        Integer[] userIds = getParaValuesToInt("userId");
        if (ArrayUtil.isNotEmpty(userIds)) {
            // 先查询这个管理员的下属
            Kv condition = Kv.by("parent_id = ", TokenKit.getUserId());
            List<User> users = User.dao.find(condition);
            List<Integer> list = Arrays.asList(userIds);
            // 如果下属不在选中，移除下属和下属的下属...
            for (User user : users) {
                if (!list.contains(user.getId())) {
                    condition = Kv.by("user_id = ", user.getId()).set("platform_id = ", platformId);
                    List<UserPlatform> userPlatforms = UserPlatform.dao.find(condition);
                    for (UserPlatform userPlatform : userPlatforms) {
                        deleteByUserParentId(userPlatform);
                    }
                }
            }

            // 查询该平台的所有操作者
            condition = Kv.by("platform_id = ", platformId);
            List<UserPlatform> userPlatforms = UserPlatform.dao.find(condition);
            list = new ArrayList<>();
            for (UserPlatform userPlatform : userPlatforms) {
                list.add(userPlatform.getUserId());
            }
            // 如果选中不在操作者中，加入
            for (Integer userId : userIds) {
                if (!list.contains(userId)) {
                    new UserPlatform().setUserId(userId).setPlatformId(platformId).save();
                }
            }
        }
        else {
            // 全部清除
            Kv condition = Kv.by("platform_id = ", platformId);
            List<UserPlatform> userPlatforms = UserPlatform.dao.find(condition);
            for (UserPlatform userPlatform : userPlatforms) {
                userPlatform.delete();
            }
        }

        // 最后都要存在自己名下
        Kv condition = Kv.by("platform_id = ", platformId).set("user_id = ", TokenKit.getUserId());
        List<UserPlatform> userPlatforms = UserPlatform.dao.find(condition);
        if (CollectionUtil.isEmpty(userPlatforms)) {
            new UserPlatform().setUserId(TokenKit.getUserId()).setPlatformId(platformId).save();
        }
    }

    private void deleteByUserParentId(UserPlatform userPlatform) {
        userPlatform.delete();
        Kv condition = Kv.by("parent_id = ", userPlatform.getUserId());
        List<User> users = User.dao.find(condition);
        if (users.size() == 0) {
            return;
        }
        for (User user : users) {
            condition = Kv.by("user_id = ", user.getId()).set(" platform_id = ", userPlatform.getPlatformId());
            List<UserPlatform> userPlatforms = UserPlatform.dao.find(condition);
            for (UserPlatform userPlatform1 : userPlatforms) {
                deleteByUserParentId(userPlatform1);
            }
        }
    }
}
