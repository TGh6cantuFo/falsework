package com.yaowk.device.listener;

import com.yaowk.device.event.PaperOutEvent;
import net.dreamlu.event.core.ApplicationListener;
import net.dreamlu.event.core.Listener;

/**
 * @authc yaowk
 * 2017/7/17
 */
@Listener(enableAsync = true)
public class PaperOutListener implements ApplicationListener<PaperOutEvent> {
    @Override
    public void onApplicationEvent(PaperOutEvent paperOutEvent) {

    }
}
