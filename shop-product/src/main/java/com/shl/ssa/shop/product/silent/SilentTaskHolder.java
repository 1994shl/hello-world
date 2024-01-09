package com.shl.ssa.shop.product.silent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * @author 施海林
 * @create 2023-08-16 15:43
 * @Description
 */
@Slf4j
@Service
public class SilentTaskHolder {

    @Resource
    @Qualifier("silentExecutor")
    private ExecutorService silentExecutor;

    private static Map<String, SilentTask> taskMap = new HashMap<>(128);


    public static Thread thread;

    public void putTask(String key) {

        SilentTask task = new SilentTask(key);




        taskMap.put(key, task);

        task.test();

        //silentExecutor.execute(()->{ task});
        //task.start();
    }

    public void stopTask(String key) {
        SilentTask remove = taskMap.remove(key);
        if (null == remove) {
            log.warn("task key is {} and remove null!", key);
            return;
        }
        log.info("remove {} from IntervalTaskManager", key);

       /* silentExecutor.


        remove.stopSilentMonitorTask();
*/


    }

}
