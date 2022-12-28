package com.qis.springframework.test.bean;

import com.qis.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author: qishuo
 * @date: 2022/12/28 15:55
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法:" + method.getName());
    }
}
