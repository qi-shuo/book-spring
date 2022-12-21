package com.qis.springframework.context.support;

import com.qis.springframework.beans.BeansException;
import com.qis.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.qis.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.qis.springframework.beans.factory.config.BeanPostProcessor;
import com.qis.springframework.context.ConfigurableApplicationContext;
import com.qis.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * 抽象应用上下文
 *
 * @author: qishuo
 * @date: 2022/12/9 10:58
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeansException {
        //1. 刷新beanFactory
        refreshBeanFactory();
        //2. 获取beanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        //3. 在 Bean 实例化之前，执行 BeanFactoryPostProcessor (Invoke factory processors registered as beans in the context.)
        invokeBeanFactoryPostProcessors(beanFactory);
        //4. 注册BeanPostProcessors
        registerBeanPostProcessors(beanFactory);
        //5. 提前实例化所有单例bean对象(在spring中是非懒加载的所有单例bean)
        beanFactory.preInstantiateSingletons();
    }

    /**
     * 执行BeanFactoryPostProcessor的后置处理器,在所有BeanDefinition中加载完成,但是没有实例化之前
     *
     * @param beanFactory
     */
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        //  getBeansOfType会优先工厂beanDefinition获取对应bean的class进行创建bean
        // 此时BeanFactory中没有BeanPostProcessors,所以不会执行BeanPostProcessors一些钩子方法
        // 如果在调用该方法之前调用了addBeanPostProcessor注册BeanPostProcessors那么会去执行BeanPostProcessors,一些特定的BeanPostProcessors就会在invokeBeanFactoryPostProcessor之前注册到BeanFactory中
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            //执行BeanFactory的后置处理,可以修改BeanDefinition
            beanFactoryPostProcessor.postProcessorBeanFactory(beanFactory);
        }
    }

    /**
     * 注册Bean的后置处理器,BeanPostProcessors
     *
     * @param beanFactory
     */
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        //  getBeansOfType会优先工厂beanDefinition获取对应bean的class进行创建bean,
        // 此时beanFactory中只有 BeanFactoryPostProcessor,但是BeanFactoryPostProcessor已经被执行了所以不会在被执行
        //容器没有BeanPostProcessors类,只有执行registerBeanPostProcessors才会有BeanPostProcessors类,之后创建的bean才会走钩子方法
        Map<String, BeanPostProcessor> beanFactoryPostProcessorMap = getBeansOfType(BeanPostProcessor.class);
        //Spring在注册之前会基于Order进行排序,Spring注册顺序BeanPostProcessorChecker->PriorityOrdered->Ordered(不包含@Order注解的形式)->普通的->MergedBeanDefinitionPostProcessor->ApplicationListenerDetector
        for (BeanPostProcessor beanPostProcessor : beanFactoryPostProcessorMap.values()) {
            //注册bean的后置处理器
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }

    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        getBeanFactory().destroySingletons();

    }

    /**
     * 刷新beanFactory
     *
     * @throws BeansException
     */
    protected abstract void refreshBeanFactory() throws BeansException;

    /**
     * 获取bean工厂
     *
     * @return
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    @Override
    public Object getBean(String beanName) throws BeansException {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return getBeanFactory().getBean(beanName, args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(beanName, requiredType);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }
}
