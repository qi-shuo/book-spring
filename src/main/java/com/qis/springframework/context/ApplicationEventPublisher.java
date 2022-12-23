package com.qis.springframework.context;

/**
 * 应用事件发布者接口
 *
 * @author: qishuo
 * @date: 2022/12/23 15:22
 */
public interface ApplicationEventPublisher {
    /**
     * 将应用程序事件通知所有注册到此应用程序的侦听器。事件可能是框架事件（例如 RequestHandledEvent）或特定于应用程序的事件。
     * @param event 要发布的事件
     */
    void publishEvent(ApplicationEvent event);
}
