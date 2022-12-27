package com.qis.springframework.aop;

/**
 * 被代理的目标对象
 *
 * @author: qishuo
 * @date: 2022/12/27 11:12
 */
public class TargetSource {
    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }


    public Class<?>[] getTargetClass() {
        return this.target.getClass().getInterfaces();
    }

    /**
     * 返回一个目标实例。在 AOP 框架调用 AOP 方法调用的“目标”之前立即调用。
     *
     * @return
     */
    public Object getTarget() {
        return target;
    }
}
