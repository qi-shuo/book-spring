package com.qis.springframework.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * AOP 代理配置管理器的基类。这些本身不是 AOP 代理，但此类的子类通常是直接从中获取 AOP 代理实例的工厂。
 *
 * @author: qishuo
 * @date: 2022/12/27 11:32
 */
public class AdvisedSupport {
    /**
     * proxyConfig
     * true使用cglib创建代理对象
     * false使用jdk创建代理对象
     * Spring源码默认使用jdk,但是在SpringBoot2.0版本默认使用cglib
     * 参考文章:<a href="https://www.51cto.com/article/717741.html">...</a>
     */
    private boolean proxyTargetClass = false;
    /**
     * 被代理的目标对象
     */
    private TargetSource targetSource;
    /**
     * 方法拦截器
     */
    private MethodInterceptor methodInterceptor;
    /**
     * 方法匹配器(检查目标方法是否符合通知条件)
     */
    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }

    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }

    public void setProxyTargetClass(boolean proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }
}
