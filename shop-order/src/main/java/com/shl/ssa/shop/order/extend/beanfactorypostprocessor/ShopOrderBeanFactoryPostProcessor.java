package com.shl.ssa.shop.order.extend.beanfactorypostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author 施海林
 * @create 2023-02-22 14:54
 * @Description 针对BeanFactory的扩展，修改BeanFactory的属性（执行BeanFactory的后置处理器）
 */
public class ShopOrderBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("ShopOrderBeanFactoryPostProcessor is work,可以修改BeanFactory的属性");
    }
}
