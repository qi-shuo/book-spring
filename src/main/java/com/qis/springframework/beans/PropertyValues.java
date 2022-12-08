package com.qis.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * 包含一个或多个PropertyValue对象的持有者
 * @author: qishuo
 * @date: 2022/12/8 14:21
 */
public class PropertyValues {
    /**
     * 属性信息集合
     */
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    /**
     * 添加属性信息
     *
     * @param pv
     */
    public void addPropertyValue(PropertyValue pv) {
        propertyValueList.add(pv);
    }

    /**
     * 获取全部的属性信息
     *
     * @return
     */
    public PropertyValue[] getPropertyValues() {
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    /**
     * 通过属性名称获取属性信息
     *
     * @param propertyName
     * @return
     */
    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue propertyValue : this.propertyValueList) {
            if (propertyValue.getName().equals(propertyName)) {
                return propertyValue;
            }
        }
        return null;
    }
}
