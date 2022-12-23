package com.qis.springframework.context.event;

/**
 * 上下文关闭时间
 * @author: qishuo
 * @date: 2022/12/23 15:56
 */
public class ContextClosedEvent extends ApplicationContextEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }
}
