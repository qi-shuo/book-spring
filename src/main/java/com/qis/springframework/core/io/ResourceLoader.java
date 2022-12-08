package com.qis.springframework.core.io;

/**
 * 加载资源
 *
 * @author: qishuo
 * @date: 2022/12/8 15:18
 */
public interface ResourceLoader {
    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 获取资源
     * @param location 资源地址,本地文件,远程url地址,classpath
     * @return
     */
    Resource getResource(String location);
}
