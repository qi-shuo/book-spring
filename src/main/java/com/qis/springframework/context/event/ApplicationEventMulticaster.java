package com.qis.springframework.context.event;

import com.qis.springframework.context.ApplicationEvent;
import com.qis.springframework.context.ApplicationListener;

/**
 * 应用事件广播器接口
 *
 * @author: qishuo
 * @date: 2022/12/23 15:30
 */
public interface ApplicationEventMulticaster {
    /**
     * 添加事件监听者
     *
     * @param listener
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * 移除事件监听者
     *
     * @param listener
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * 广播事件
     *
     * @param applicationEvent
     */
    void multicastEvent(ApplicationEvent applicationEvent);
}
