package com.qis.springframework.test;

import com.qis.springframework.aop.AdvisedSupport;
import com.qis.springframework.aop.TargetSource;
import com.qis.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.qis.springframework.aop.framework.CglibAopProxy;
import com.qis.springframework.aop.framework.JdkDynamicAopProxy;
import com.qis.springframework.beans.PropertyValue;
import com.qis.springframework.beans.PropertyValues;
import com.qis.springframework.beans.factory.config.BeanDefinition;
import com.qis.springframework.beans.factory.config.BeanReference;
import com.qis.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.qis.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.qis.springframework.context.ApplicationListener;
import com.qis.springframework.context.support.ClassPathXmlApplicationContext;
import com.qis.springframework.test.bean.*;
import com.qis.springframework.test.common.MyBeanFactoryPostProcessor;
import com.qis.springframework.test.common.MyBeanPostProcessor;
import com.qis.springframework.test.event.ContextClosedEventListener;
import com.qis.springframework.test.event.CustomEvent;
import com.qis.springframework.util.ClassUtils;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public void testApplicationContext() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService userService = classPathXmlApplicationContext.getBean("userService", UserService.class);
        System.out.println(userService);
        System.out.println(userService.queryUserInfo());

        UserService userService1 = classPathXmlApplicationContext.getBean("userService", UserService.class);
        System.out.println(userService1);
        System.out.println(userService1.queryUserInfo());


    }

    /**
     * 测试bean初始化方法和bean的销毁方法
     */
    @Test
    public void testInitAndDisposable() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        //注册钩子方法
        applicationContext.registerShutdownHook();
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);

    }

    /**
     * 测试xxxAware相关感知接口
     */
    @Test
    public void testAware() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();

        System.out.println("测试结果：" + result);

        System.out.println("ApplicationContextAware：" + userService.getApplicationContext());
        System.out.println("BeanFactoryAware：" + userService.getBeanFactory());
    }

    /**
     * 测试多例bean
     */
    @Test
    public void testPrototype() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService userService1 = applicationContext.getBean("userService", UserService.class);
        UserService userService2 = applicationContext.getBean("userService", UserService.class);
        System.out.println(userService1.hashCode());
        System.out.println(userService2.hashCode());
    }

    /**
     * 测试FactoryBean
     */
    @Test
    public void testFactoryBean() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserDao userDao = applicationContext.getBean("userDao", UserDao.class);
        UserDao userDao1 = applicationContext.getBean("userDao", UserDao.class);

        System.out.println(userDao.queryUserName("10001"));
        System.out.println(userDao.equals(userDao1));

        System.out.println(userDao);
        System.out.println(userDao1);
    }

    /**
     * 测试事件广播和监听
     */
    @Test
    public void testLister() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));
        applicationContext.registerShutdownHook();
    }

    /**
     * 测试获取泛型
     */
    public static class QisList extends ArrayList<String> {

    }

    /**
     * 测试泛型
     */
    @Test
    public void testGeneric() throws ClassNotFoundException {
        QisList strings = new QisList();
        Class<? extends List> listenerClass = strings.getClass();
        // 按照 CglibSubclassingInstantiationStrategy、SimpleInstantiationStrategy 不同的实例化类型，需要判断后获取目标 class
        Class<?> targertClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;

        Type genericSuperclass = targertClass.getGenericSuperclass();
        Type actualTypeArgument = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        System.out.println(className);
    }

    /**
     * 测试AOP中的ClassFilter和MethodMatcher
     *
     * @throws NoSuchMethodException
     */
    @Test
    public void test_aop() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.qis.springframework.test.bean.UserService.*(..))");

        Class<UserService> clazz = UserService.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");

        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));
    }

    /**
     * 测试AOP代理
     */
    @Test
    public void test_dynamic() {
        // 目标对象
        IUserService userService = new UserService();
        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.qis.springframework.test.bean.IUserService.*(..))"));

        // 代理对象(JdkDynamicAopProxy)
        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_jdk.queryUserInfo());

        // 代理对象(Cglib2AopProxy)
        IUserService proxy_cglib = (IUserService) new CglibAopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_cglib.register("花花"));
    }

}
