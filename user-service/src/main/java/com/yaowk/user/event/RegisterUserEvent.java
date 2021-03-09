package com.yaowk.user.event;

import net.dreamlu.event.core.ApplicationEvent;

/**
 * @authc yaowk
 * 2017/6/27
 */
public class RegisterUserEvent extends ApplicationEvent {
    public RegisterUserEvent(Object source) {
        super(source);
    }
}
