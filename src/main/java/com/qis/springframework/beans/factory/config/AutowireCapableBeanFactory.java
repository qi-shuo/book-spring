package com.qis.springframework.beans.factory.config;

import com.qis.springframework.beans.BeansException;
import com.qis.springframework.beans.factory.BeanFactory;

/**
 * BeanFactory接口的扩展将由能够自动装配的 bean 工厂实现，前提是它们想为现有的 bean 实例公开此功能。
 * BeanFactory 的这个子接口并不意味着在正常的应用程序代码中使用：在典型用例中坚持使用BeanFactory或org.springframework.beans.factory.ListableBeanFactory 。
 * 其他框架的集成代码可以利用此接口来连接和填充 Spring 不控制其生命周期的现有 bean 实例。例如，这对于 WebWork Actions 和 Tapestry Page 对象特别有用。
 * 请注意，此接口不是由org.springframework.context.ApplicationContext facades 实现的，因为它几乎从未被应用程序代码使用过。也就是说，它也可以从应用程序上下文中获得，可以通过 ApplicationContext 的org.springframework.context.ApplicationContext.getAutowireCapableBeanFactory()方法访问。
 * 您还可以实现org.springframework.beans.factory.BeanFactoryAware接口，即使在 ApplicationContext 中运行时它也会公开内部 BeanFactory，以访问 AutowireCapableBeanFactory：只需将传入的 BeanFactory 转换为 AutowireCapableBeanFactory。
 *
 * @author: qishuo
 * @date: 2022/12/8 18:57
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    /**
     * 执行BeanPostProcessors接口实现类的postProcessBeforeInitialization
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 执行BeanPostProcessors接口实现类的postProcessorsAfterInitialization方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
