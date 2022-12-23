package com.qis.springframework.context.event;

import com.qis.springframework.context.ApplicationContext;
import com.qis.springframework.context.ApplicationEvent;

/**
 * 应用上下文事件
 * @author: qishuo
 * @date: 2022/12/23 15:53
 */
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    /**
     * 获取为其引发事件的 <code>ApplicationContext<code>。
     * @return
     */
    public final ApplicationContext getApplicationContext(){
        return (ApplicationContext) getSource();
    }
}
