package com.qis.springframework.test.event;

import com.qis.springframework.context.ApplicationListener;
import com.qis.springframework.context.event.ContextRefreshedEvent;

/**
 * 容器刷新监听器
 *
 * @author: qishuo
 * @date: 2022/12/23 16:25
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件：" + this.getClass().getName());
    }
}
