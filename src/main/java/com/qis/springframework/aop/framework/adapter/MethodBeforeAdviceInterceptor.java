package com.qis.springframework.aop.framework.adapter;

import com.qis.springframework.aop.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 拦截器包装 am {@link com.qis.springframework.aop.MethodBeforeAdvice}。由AOP框架内部使用；应用程序开发人员不需要直接使用此类
 *
 * @author: qishuo
 * @date: 2022/12/28 15:25
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {
    private MethodBeforeAdvice advice;

    public MethodBeforeAdviceInterceptor() {
    }

    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        //调用before
        this.advice.before(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        return invocation.proceed();
    }
}
