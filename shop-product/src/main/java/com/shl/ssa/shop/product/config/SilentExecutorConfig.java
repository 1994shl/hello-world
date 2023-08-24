package com.shl.ssa.shop.product.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 施海林
 * @create 2023-08-17 10:32
 * @Description 静默任务线程池配置
 */
@Slf4j
@Configuration
public class SilentExecutorConfig {

    @Bean("silentExecutor")
    public static ExecutorService inspectionExecutor() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat(
                "Silent-Monitor-%d").setDaemon(true).build();
        //可无限制创建工作线程，直至OOM
        return new ThreadPoolExecutor(
                1, 3, 60L,
                TimeUnit.MILLISECONDS, new SynchronousQueue<>(), threadFactory);
    }
}
