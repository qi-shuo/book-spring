package com.qis.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * 包含 AOP <b>建议<b>（在连接点采取的操作）和确定建议适用性的过滤器（例如切入点）的基本接口。 <i>这个接口不是供 Spring 用户使用的，而是为了支持不同类型通知的通用性。
 *
 * @author: qishuo
 * @date: 2022/12/28 14:30
 */
public interface Advisor {

    /**
     * 返回这方面的建议部分。建议可以是拦截器、前建议、抛出建议等。
     *
     * @return
     */
    Advice getAdvice();
}
