package com.qis.springframework.aop;

/**
 * 切入点由 {@link ClassFilter} 和 {@link MethodMatcher} 组成。这些基本术语和切入点本身都可以组合起来构建组合
 *
 * @author: qishuo
 * @date: 2022/12/27 11:15
 */
public interface Pointcut {
    /**
     * 返回此切入点的 ClassFilter。
     * @return
     */
    ClassFilter getClassFilter();

    /**
     * 返回切入点的方法匹配器
     * @return
     */
    MethodMatcher getMethodMatcher();
}
