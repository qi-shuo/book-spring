package com.qis.springframework.context.support;

import com.qis.springframework.beans.BeansException;
import com.qis.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.qis.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.qis.springframework.beans.factory.config.BeanPostProcessor;
import com.qis.springframework.context.ApplicationEvent;
import com.qis.springframework.context.ApplicationListener;
import com.qis.springframework.context.ConfigurableApplicationContext;
import com.qis.springframework.context.event.ApplicationEventMulticaster;
import com.qis.springframework.context.event.ContextClosedEvent;
import com.qis.springframework.context.event.ContextRefreshedEvent;
import com.qis.springframework.context.event.SimpleApplicationEventMulticaster;
import com.qis.springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * 抽象应用上下文
 *
 * @author: qishuo
 * @date: 2022/12/9 10:58
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    /**
     * 事件广播器bean的名称
     */
    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";
    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() throws BeansException {
        //1. 刷新beanFactory
        refreshBeanFactory();
        //2. 获取beanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        // 3. 添加 ApplicationContextAwareProcessor，让继承自 ApplicationContextAware 的 Bean 对象都能感知所属的 ApplicationContext
        // 优先注册BeanPostProcessor,这样可以在调用BeanPostProcessor链中第一个执行,在Spring源码中prepareBeanFactory进行注册比其他的BeanPostProcessor优先注册
        // spring源码中postProcessBeanFactory也提供注入特殊的BeanPostProcessors,但是比ApplicationContextAwareProcessor优先级低,先执行prepareBeanFactory在执行postProcessBeanFactory
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
        //4. 在 Bean 实例化之前，执行 BeanFactoryPostProcessor (Invoke factory processors registered as beans in the context.)
        invokeBeanFactoryPostProcessors(beanFactory);
        //5. 注册BeanPostProcessors
        registerBeanPostProcessors(beanFactory);
        //6. 初始化事件发布者
        initApplicationEventMulticaster();
        //7. 注册事件监听器
        registerListeners();
        //8. 提前实例化所有单例bean对象(在spring中是非懒加载的所有单例bean)
        beanFactory.preInstantiateSingletons();
        //9. 发布容器刷新完成事件
        finishRefresh();
    }

    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    /**
     * 初始化事件广播器
     */
    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        //spring源码首先获取APPLICATION_EVENT_MULTICASTER_BEAN_NAME如果已经存在事件广播器,直接使用,如果没有创建一个简单的事件广播器,放到bean工厂中
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    private void finishRefresh() {
        //发布容器刷新事件
        publishEvent(new ContextRefreshedEvent(this));
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
        //发布上下文关闭事件
        publishEvent(new ContextClosedEvent(this));
        getBeanFactory().destroySingletons();

    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
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
