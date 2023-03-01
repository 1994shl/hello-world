package com.shl.ssa.shop.order.extend.listener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author 施海林
 * @create 2023-02-22 10:40
 * @Description 发生在Bean装配后，可由自行实例化@Component 或spi
 */
@Component
public class ShopOrderApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println(Thread.currentThread().getName()+" ShopOrderApplicationStartedEventListener is working! and enent type is "
                + event.getClass().getName());
    }
}
