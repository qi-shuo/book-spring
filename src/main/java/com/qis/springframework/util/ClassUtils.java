package com.qis.springframework.util;

/**
 * @author: qishuo
 * @date: 2022/12/8 15:25
 */
public class ClassUtils {
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // 无法访问线程上下文 ClassLoader - 回退到系统类加载器...
        }
        if (cl == null) {
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;

    }
}
