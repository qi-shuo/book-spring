package com.qis.springframework.context;

import java.util.EventObject;

/**
 * 由所有应用程序事件扩展的类。抽象，因为直接发布通用事件没有意义。
 * @author: qishuo
 * @date: 2022/12/23 15:18
 */
public abstract class ApplicationEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
