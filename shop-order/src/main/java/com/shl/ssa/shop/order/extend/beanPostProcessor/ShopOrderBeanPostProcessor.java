package com.shl.ssa.shop.order.extend.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author 施海林
 * @create 2023-02-22 15:28
 * @Description 针对bean初始化前后的扩展（aop的实现方式，获取织入切面业务的动态代理类service$proxy）
 */
public class ShopOrderBeanPostProcessor implements BeanPostProcessor {

    //bean示例化前
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.contains("Order")){
            System.out.println("bean实例化之前 :" + beanName);
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    //bean示例化后
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.contains("Order")){
            System.out.println("bean实例化之后 :" + beanName);
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
