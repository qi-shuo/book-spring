package com.qis.springframework.beans.factory;

/**
 * 由 bean 工厂实现的子接口，可以是层次结构的一部分。
 * 可以在 ConfigurableBeanFactory 接口中找到允许以可配置方式设置父级的 bean 工厂的相应setParentBeanFactory方法。
 *
 * 目前没有使用,固空接口,Spring中提供getParentBeanFactory和containsLocalBean两个方法
 * @author: qishuo
 * @date: 2022/12/8 18:52
 */
public interface HierarchicalBeanFactory extends BeanFactory {

}
