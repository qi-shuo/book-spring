package com.qis.springframework.test;

import com.qis.springframework.beans.PropertyValue;
import com.qis.springframework.beans.PropertyValues;
import com.qis.springframework.beans.factory.config.BeanDefinition;
import com.qis.springframework.beans.factory.config.BeanReference;
import com.qis.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.qis.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.qis.springframework.context.support.ClassPathXmlApplicationContext;
import com.qis.springframework.test.bean.UserDao;
import com.qis.springframework.test.bean.UserService;
import com.qis.springframework.test.common.MyBeanFactoryPostProcessor;
import com.qis.springframework.test.common.MyBeanPostProcessor;
import org.junit.Test;

/**
 * @author: qishuo
 * @date: 2022/12/5 19:09
 */
public class ApiTest {
    @Test
    public void testBeanFactory() {
        //创建bean工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //定义BeanDefinition并且注册
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        //第一次获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
        System.out.println(userService);
        //第二次获取bean,证明单例
        UserService userService1 = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
        System.out.println(userService1);

    }

    /**
     * 测试带有构造函数的bean创建
     */
    @Test
    public void testBeanFactoryArgs() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        UserService userService = (UserService) beanFactory.getBean("userService", "qis");
        System.out.println(userService);
        System.out.println(userService.queryUserInfo());
    }

    /**
     * 测试bean工厂设置属性
     */
    @Test
    public void testBeanFactoryPropertyValue() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        //设置属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uid", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));
        //设置userService的beanDefinition
        BeanDefinition userSeriveBeanDefinition = new BeanDefinition(UserService.class, propertyValues);
        //注册userService的beanDefinition
        defaultListableBeanFactory.registerBeanDefinition("userService", userSeriveBeanDefinition);

        BeanDefinition userDaoBeanDefinition = new BeanDefinition(UserDao.class);
        defaultListableBeanFactory.registerBeanDefinition("userDao", userDaoBeanDefinition);

        UserService userService = (UserService) defaultListableBeanFactory.getBean("userService");

        System.out.println(userService.queryUserInfo());
    }

    /**
     * 加载beanFactory通过xml加载BeanDefinition
     */
    @Test
    public void testBeanFactoryXml() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        UserService userService = (UserService) beanFactory.getBean("userService");
        //测试结果
        System.out.println("测试结果:" + userService.queryUserInfo());
    }

    /**
     * 测试bean的后置处理器,包括BeanFactoryPostProcessor和BeanPostProcessor
     */
    @Test
    public void testBeanFactoryPostProcessor() {
        //创建BeanFactory并加载xml
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        //创建BeanFactoryPostProcessor并执行
        MyBeanFactoryPostProcessor myBeanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        myBeanFactoryPostProcessor.postProcessorBeanFactory(beanFactory);

        //创建BeanPostProcessor并添加到工厂中
        MyBeanPostProcessor myBeanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(myBeanPostProcessor);

        UserService userService = beanFactory.getBean("userService", UserService.class);

        System.out.println("测试结果：" + userService.queryUserInfo());
        System.out.println("测试结果：" + userService);
    }

    @Test
    public void testApplicationContext(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService userService = classPathXmlApplicationContext.getBean("userService", UserService.class);
        System.out.println(userService);
        System.out.println(userService.queryUserInfo());

        UserService userService1 = classPathXmlApplicationContext.getBean("userService", UserService.class);
        System.out.println(userService1);
        System.out.println(userService1.queryUserInfo());


    }
}
