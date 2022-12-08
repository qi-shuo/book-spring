package com.qis.springframework.beans.factory;

import com.qis.springframework.beans.BeansException;

import java.util.Map;

/**
 * BeanFactory接口的扩展将由可以枚举所有 bean 实例的 bean 工厂实现，而不是按照客户的请求逐个尝试按名称查找 bean。
 * 预加载所有 bean 定义的 BeanFactory 实现（例如基于 XML 的工厂）可以实现此接口。
 * 如果这是一个HierarchicalBeanFactory ，
 * 返回值将不会考虑任何 BeanFactory 层次结构，而只会与当前工厂中定义的 beans 相关。
 * 也可以使用BeanFactoryUtils帮助器类来考虑祖先工厂中的 bean。
 * 这个接口中的方法将只尊重这个工厂的 bean 定义。
 * 他们将忽略任何已通过其他方式（如org.springframework.beans.factory.config.ConfigurableBeanFactory的registerSingleton方法）
 * 注册的单例 bean，但getBeanNamesOfType和getBeansOfType除外，它们也会检查此类手动注册的单例。
 * 当然，BeanFactory 的getBean也允许透明访问这种特殊的 bean。
 * 然而，在典型的场景中，所有的 bean 无论如何都会由外部 bean 定义来定义，所以大多数应用程序不需要担心这种差异化。
 * 注意：除了getBeanDefinitionCount和containsBeanDefinition之外，此接口中的方法不是为频繁调用而设计的。实施可能很慢
 *
 * @author: qishuo
 * @date: 2022/12/8 18:46
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照类型返回bean实例
     *
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回注册表中所有的bean名称
     *
     * @return
     */
    String[] getBeanDefinitionNames();

}
