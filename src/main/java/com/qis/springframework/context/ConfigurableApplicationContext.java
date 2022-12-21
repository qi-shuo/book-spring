package com.qis.springframework.context;

import com.qis.springframework.beans.BeansException;

/**
 * @author: qishuo
 * @date: 2022/12/9 10:56
 */
public interface ConfigurableApplicationContext extends ApplicationContext {
    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;

    /**
     * 注册关闭的钩子方法
     */
    void registerShutdownHook();

    /**
     * 关闭容器
     */
    void close();
}
