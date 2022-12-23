package com.qis.springframework.context;

import com.qis.springframework.beans.factory.HierarchicalBeanFactory;
import com.qis.springframework.beans.factory.ListableBeanFactory;

/**
 * @author: qishuo
 * @date: 2022/12/9 10:54
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory,ApplicationEventPublisher {
}
