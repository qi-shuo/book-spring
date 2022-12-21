package com.qis.springframework.beans.factory;

/**
 * 由想要在销毁时释放资源的 bean 实现的接口。如果 BeanFactory 处理缓存的单例，则它应该调用 destroy 方法。应用程序上下文应该在关闭时处理它的所有单例。
 * @author: qishuo
 * @date: 2022/12/21 15:44
 */
public interface DisposableBean {
    /**
     * 销毁方法
     * @throws 抛出异常
     */
    void destroy() throws Exception;
}
