package com.qis.springframework.aop.framework;

/**
 * 已配置 AOP 代理的委托接口，允许创建实际的代理对象。 <p>开箱即用的实现可用于 JDK 动态代理和 CGLIB 代理，由 DefaultAopProxyFactory 应用
 * Aop代理的抽象
 * @author: qishuo
 * @date: 2022/12/27 15:02
 */
public interface AopProxy {
    /**
     * 获取代理
     * @return
     */
    Object getProxy();

}
