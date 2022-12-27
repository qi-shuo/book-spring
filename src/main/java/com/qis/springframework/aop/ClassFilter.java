package com.qis.springframework.aop;

/**
 * 限制切入点或引入到给定目标类集的匹配的过滤器
 * @author: qishuo
 * @date: 2022/12/27 11:16
 */
public interface ClassFilter {
    /**
     * 切入点应该应用于给定的接口还是目标类
     * @param clazz 候选目标类别
     * @return 该建议是否适用于给定的目标类别
     */
    boolean matches(Class<?> clazz);
}
