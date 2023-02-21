package com.shl.ssa.shop.starter.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author 施海林
 * @create 2023-02-21 15:24
 * @Description
 */
public class ShopCondition implements Condition {

    /**
     * 此方法返回true,注解的bean才会自动装配
     *
     * @param context  the condition context
     * @param metadata metadata of the {@link org.springframework.core.type.AnnotationMetadata class}
     *                 or {@link org.springframework.core.type.MethodMetadata method} being checked
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        //此处可做一些逻辑检查，确定是否加载bean
        System.out.println("always return true,so will configure this bean");

        return true;
    }
}
