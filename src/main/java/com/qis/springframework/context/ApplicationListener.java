package com.qis.springframework.context;

import java.util.EventListener;

/**
 * 应用监听器接口,基于 Observer 设计模式的标准 <code>java.util.EventListener<code> 接口。
 *
 * @author: qishuo
 * @date: 2022/12/23 15:17
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     * 处理应用事件
     *
     * @param event 要响应的事件
     */
    void onApplicationEvent(E event);
}
