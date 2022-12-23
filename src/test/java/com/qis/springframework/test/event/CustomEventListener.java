package com.qis.springframework.test.event;

import com.qis.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * @author: qishuo
 * @date: 2022/12/23 16:39
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到：" + event.getSource() + "消息;时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }
}
