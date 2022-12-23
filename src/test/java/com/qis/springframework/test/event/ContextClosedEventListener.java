package com.qis.springframework.test.event;

import com.qis.springframework.context.ApplicationListener;
import com.qis.springframework.context.event.ContextClosedEvent;

/**
 * @author: qishuo
 * @date: 2022/12/23 16:26
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }
}
