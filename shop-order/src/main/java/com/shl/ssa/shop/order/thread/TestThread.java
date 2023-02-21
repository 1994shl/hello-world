package com.shl.ssa.shop.order.thread;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @author 施海林
 * @create 2022-07-27 10:20
 * @Description
 */
@Slf4j
public class TestThread extends Thread {



    private TestThread(){
    }

    private static volatile TestThread testThread;

    public static TestThread getInstance() {
        if (ObjectUtils.isEmpty(testThread)) {
            synchronized (TestThread.class) {
                testThread = new TestThread();
                testThread.setName("TestThread");
            }
        }
        return testThread;
    }

    @Override
    public void run() {
        while (true) {
            log.info("i am work thread");
        }
    }
}
