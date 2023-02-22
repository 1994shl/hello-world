package com.shl.ssa.shop.order.extend.listener;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author 施海林
 * @create 2023-02-21 16:58
 * @Description spring boot扩展方式：监听器，取决于监听的event
 * 此event发布于Bean装备之前，无法ioc容器管理，可由spi注入（META-INF/spring.factories）
 */
public class ShopOrderApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent> {

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        System.out.println("ShopOrderApplicationStartingEventListener is working! and enent type is "
                + event.getClass().getName());
    }
}
