package com.qis.springframework.context.support;

import com.qis.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.qis.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 抽象基类 XML 上下文
 * @author: qishuo
 * @date: 2022/12/9 16:42
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] configLocations = getConfigLocations();
        for (String configLocation : configLocations) {
            beanDefinitionReader.loadBeanDefinitions(configLocation);
        }

    }

    /**
     * 获取配置文件地址
     *
     * @return
     */
    protected abstract String[] getConfigLocations();
}
