package com.yaowk.user.listener;

import com.yaowk.user.event.UserEvent;
import com.yaowk.user.model.User;
import net.dreamlu.event.core.ApplicationListener;
import net.dreamlu.event.core.Listener;

/**
 * 添加用户监听器
 *
 * @authc yaowk
 * 2017/7/18
 */
@Listener(tag = "add")
public class AddUserListener implements ApplicationListener<UserEvent> {
    @Override
    public void onApplicationEvent(UserEvent userEvent) {

    }
}
