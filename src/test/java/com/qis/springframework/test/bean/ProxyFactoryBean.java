package com.qis.springframework.test.bean;

import com.qis.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: qishuo
 * @date: 2022/12/22 17:14
 */
public class ProxyFactoryBean implements FactoryBean<IUserDao> {
    @Override
    public IUserDao getObject() throws Exception {
        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserDao.class}, (proxy, method, args) -> {
            if ("toString".equals(method.getName())) return this.toString();

            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("10001", "qis");
            hashMap.put("10002", "八杯水");
            hashMap.put("10003", "阿毛");

            return "你被代理了 " + method.getName() + "：" + hashMap.get(args[0].toString());
        });
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
