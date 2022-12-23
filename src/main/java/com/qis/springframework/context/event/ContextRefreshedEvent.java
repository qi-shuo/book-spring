package com.qis.springframework.context.event;

/**
 * 上下文刷新事件
 *
 * @author: qishuo
 * @date: 2022/12/23 15:57
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
