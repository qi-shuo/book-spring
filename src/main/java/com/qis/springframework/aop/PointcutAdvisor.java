package com.qis.springframework.aop;

/**
 * 由切入点驱动的所有 Advisor 的超级接口。这几乎涵盖了除引入顾问之外的所有顾问，方法级匹配不适用
 *
 * @author qishuo
 */
public interface PointcutAdvisor extends Advisor {
    /**
     * 获取驱动此顾问的切入点。
     *
     * @return
     */
    Pointcut getPointcut();
}
