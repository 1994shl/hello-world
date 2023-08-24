package com.shl.ssa.shop.product.inspection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 施海林
 * @create 2023-08-23 14:22
 * @Description
 */
@Slf4j
public class VideoParallelSubTask extends Thread {

    private String taskMrid;

    public VideoParallelSubTask(String taskMrid) {
        this.taskMrid = taskMrid;
        this.taskState = 2L;
        Consumer consumer = new Consumer();
        consumer.picList = this.picList;
        consumer.taskState = this.taskState;
        this.consumer = consumer;
    }

    private List<String> pointList = Stream.of(
            "A", "B", "C", "D", "E", "F").collect(Collectors.toList());

    private int index = 0;

    private volatile LinkedList<String> picList = new LinkedList<>();

    private Consumer consumer;

    /**
     * 2.正在执行；3.暂停；4.终止；
     */
    @Getter
    private volatile Long taskState;

    public synchronized void continueExe() {
        log.info("任务继续执行");


        taskState = 2L;

        //上报任务继续状态

        log.info("当前执行到第" + (index + 1) + "个点位，继续执行");
    }

    public synchronized void terminateExe() {
        log.info("任务终止执行");

        taskState = 4L;

        //通知分析子线程终止任务
        this.consumer.terminate();

        log.info("当前执行到第" + index + "个点位，终止执行");
    }

    public synchronized void pauseExe() {
        log.info("任务暂停执行");

        //上报任务状态

        taskState = 3L;

        log.info("当前执行到第" + index + "个点位，暂停执行");
    }

    @Override
    @SneakyThrows
    public void run() {
        //分析任务线程
        this.consumer.start();
        while (index < pointList.size()) {
            switch (taskState.intValue()) {
                case 2:
                    //按点位抓图
                    snapPic(this.index);
                    break;
                case 3:
                    log.info("当前任务处于暂停状态");
                    //上报任务暂停状态
                    TimeUnit.SECONDS.sleep(3);
                    break;
                case 4:
                    log.info("终止任务执行");
                    //上报任务终止状态，然后终止执行。
                    return;
                default:
                    log.error("not support this task state");
                    return;
            }

        }

    }

    private void snapPic(int index) {

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            log.info("catch a exception");
        }
        log.info("照片" + this.pointList.get(index) + "已抓图");
        this.picList.add("照片" + this.pointList.get(index));
        this.index++;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Consumer extends Thread {

        private LinkedList<String> picList;

        private Map<String, String> analysisMap = new HashMap<>();

        private volatile Long taskState;

        //TODO:上报已经截了图的，然后终止子线程
        public void terminate() {
            this.taskState = 4L;
        }

        @Override
        @SneakyThrows
        public void run() {
            while (true) {
                //通知终止，暂停不管
                if (this.taskState.equals(4L)) {
                    break;
                }
                if (CollectionUtils.isEmpty(this.picList)) {
                    TimeUnit.SECONDS.sleep(1);
                } else {
                    String pic = this.picList.removeFirst();
                    TimeUnit.SECONDS.sleep(3);
                    log.info("analysis " + pic);
                    TimeUnit.SECONDS.sleep(3);
                    log.info("report " + pic);
                }
            }
            log.info("out sub consume thread!");
        }
    }
}
