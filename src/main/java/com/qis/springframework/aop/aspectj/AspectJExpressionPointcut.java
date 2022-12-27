package com.qis.springframework.aop.aspectj;

import com.qis.springframework.aop.ClassFilter;
import com.qis.springframework.aop.MethodMatcher;
import com.qis.springframework.aop.Pointcut;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Spring {@link com.qis.springframework.aop.Pointcut} 使用 AspectJ 编织器评估切入点表达式的实现。
 *
 * @author: qishuo
 * @date: 2022/12/27 14:53
 */
public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher {
    /**
     * AspectJ 支持的各种切入点原语的枚举。
     */
    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<>();

    static {
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
    }

    /**
     * 切入点表达式
     */
    private final PointcutExpression pointcutExpression;

    public AspectJExpressionPointcut(String expression) {
        //获取切入点解析器
        PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORTED_PRIMITIVES, this.getClass().getClassLoader());
        this.pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }

    @Override
    public boolean matches(Class<?> clazz) {
        return pointcutExpression.couldMatchJoinPointsInType(clazz);
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return pointcutExpression.matchesMethodExecution(method).alwaysMatches();
    }

    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }
}
