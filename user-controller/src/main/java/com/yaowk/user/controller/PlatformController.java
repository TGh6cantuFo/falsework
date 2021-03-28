package com.yaowk.user.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.xiaoleilu.hutool.util.ArrayUtil;
import com.yaowk.common.controller.BaseController;
import com.yaowk.user.model.Platform;
import com.yaowk.user.model.UserPlatform;
import com.yaowk.user.shiro.TokenKit;

import java.util.ArrayList;
import java.util.List;

/**
 * @authc yaowk
 * 2017/7/16
 */
@Before(Tx.class)
public class PlatformController extends BaseController {

    @Clear(Tx.class)
    public void index() {
        Integer id = getParaToInt("platformId");
        Platform platform = Platform.dao.findById(id);
        renderJson(platform);
    }

    @Clear(Tx.class)
    public void list() {
        Kv condition = Kv.by("user_id = ", TokenKit.getUserId());
        List<UserPlatform> userPlatforms = UserPlatform.dao.find(condition);
        List<Platform> platforms = new ArrayList<>();
        for (UserPlatform userPlatform : userPlatforms) {
            platforms.add(Platform.dao.findById(userPlatform.getPlatformId()));
        }
        renderJson(platforms);
    }

    public void add() {
        Platform platform = getBean(Platform.class, "", true);
        platform.save();
        updateUserPlatform(platform.getId());
        renderSuccess();
    }

    public void edit() {
        Platform platform = getBean(Platform.class, "", true);
        platform.update();
        updateUserPlatform(platform.getId());
        renderSuccess();
    }

    public void delete() {
        
    }


    private void updateUserPlatform(Integer platformId) {
        Kv condition = Kv.by("platform_id = ", platformId);
        List<UserPlatform> userPlatforms = UserPlatform.dao.find(condition);
        for (UserPlatform userPlatform : userPlatforms) {
            userPlatform.delete();
        }

        Integer[] userIds = getParaValuesToInt("userId");
        if (ArrayUtil.isNotEmpty(userIds)) {
            new UserPlatform().setUserId(TokenKit.getUserId()).setPlatformId(platformId).save();
        }
    }
}
