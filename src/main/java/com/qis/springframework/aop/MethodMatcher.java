package com.qis.springframework.aop;

import java.lang.reflect.Method;

/**
 * {@link Pointcut} 的一部分：检查目标方法是否符合建议条件。
 * @author: qishuo
 * @date: 2022/12/27 11:16
 */
public interface MethodMatcher {
    /**
     * 执行静态检查给定的方法是否匹配
     * @param method
     * @param targetClass
     * @return 此方法是否匹配
     */
    boolean matches(Method method, Class<?> targetClass);
}
