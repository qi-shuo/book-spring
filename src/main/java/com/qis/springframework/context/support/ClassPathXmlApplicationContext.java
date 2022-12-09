package com.qis.springframework.context.support;

/**
 * XML 文件应用上下文
 *
 * @author: qishuo
 * @date: 2022/12/9 16:45
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {
    private String[] configLocations;

    public ClassPathXmlApplicationContext() {

    }

    public ClassPathXmlApplicationContext(String configLocations) {
        this(new String[]{configLocations});
    }

    public ClassPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        //初始化直接调用refresh刷新ApplicationContext的Spring容器
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}
