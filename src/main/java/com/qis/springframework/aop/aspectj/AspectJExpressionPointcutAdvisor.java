package com.qis.springframework.aop.aspectj;

import com.qis.springframework.aop.Pointcut;
import com.qis.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * 可用于任何 AspectJ 切入点表达式的 Spring AOP Advisor。
 *
 * @author: qishuo
 * @date: 2022/12/28 15:01
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
    /**
     * 切面
     */
    private AspectJExpressionPointcut pointcut;
    /**
     * 具体拦截方法
     */
    private Advice advice;
    /**
     * 表达式
     */
    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (this.pointcut == null) {
            pointcut = new AspectJExpressionPointcut(this.expression);
        }
        return this.pointcut;
    }
}
