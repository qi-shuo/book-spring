package com.qis.springframework.beans;

/**
 * 用于保存单个 bean 属性的信息和值的对象
 * @author: qishuo
 * @date: 2022/12/8 14:20
 */
public class PropertyValue {
    /**
     * 属性名称
     */
    private final String name;
    /**
     * 属性值
     */
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
