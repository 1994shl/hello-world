package com.shl.ssa.shop.product.config;

import com.shl.ssa.shop.product.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 施海林
 * @create 2023-08-16 15:22
 * @Description
 */
@Service
public class IntervalTaskManager {

    private static final Logger LOG = LoggerFactory.getLogger(IntervalTaskManager.class);

    private final List<IntervalTask> taskList = Collections.synchronizedList(new ArrayList<>());

    private final TaskThreadPoolExecutor taskThreadPoolExecutor;

    @Autowired
    public IntervalTaskManager(TaskThreadPoolExecutor taskThreadPoolExecutor) {
        this.taskThreadPoolExecutor = taskThreadPoolExecutor;
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("interval-task-%d").daemon(true).build(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        executorService.scheduleAtFixedRate(this::onCheck, 0, 1, TimeUnit.SECONDS);
    }

    /**
     * 执行轮训任务
     *
     * @param runnable 轮训任务
     * @param interval 执行间隔 单位:秒
     * @return 任务标识
     */
    public String post(Runnable runnable, int interval) {
        return post(runnable, interval, 0);
    }

    /**
     * 执行延时任务
     *
     * @param runnable 延时任务，任务只执行一次
     * @param delay    首次执行延迟时间 单位:秒
     * @return 任务标识
     */
    public String postDelay(Runnable runnable, int delay) {
        return post(runnable, 0, delay, 1);
    }

    /**
     * 执行轮训任务
     *
     * @param runnable 轮训任务
     * @param interval 执行间隔 单位:秒
     * @param delay    首次执行延迟时间 单位:秒
     * @return 任务标识
     */
    public String post(Runnable runnable, int interval, int delay) {
        return post(runnable, interval, delay, 0);
    }

    /**
     * 执行轮训任务
     *
     * @param runnable 轮训任务
     * @param interval 执行间隔 单位:秒
     * @param delay    首次执行延迟时间 单位:秒
     * @param times    总执行次数, 0表示无限循环
     * @return 任务标识
     */
    public String post(Runnable runnable, int interval, int delay, int times) {
        if (null == runnable) {
            LOG.error("runnable is null.");
            return null;
        }

        IntervalTask task = new IntervalTask(runnable, interval, delay, times);
        taskList.add(task);
        LOG.info("post task success, task={}", task);
        return task.taskId;
    }

    public synchronized void remove(final Runnable runnable) {
        if (null != runnable) {
            taskList.removeIf(task -> task.runnable == runnable);
        }
    }

    public void remove(final String taskId) {
        if (!StringUtils.isEmpty(taskId)) {
            taskList.removeIf(task -> taskId.equals(task.taskId));
        }
    }

    private void onCheck() {
        //为高性能获取时间
        //TickClock.tick();
        TickClock.tick();

        if (taskList.isEmpty()) {
            return;
        }
        LOG.info("there are [{}] interval tasks to execute", taskList.size());

        List<IntervalTask> task2Remove = new ArrayList<>();
        List<IntervalTask> exeTasks = new ArrayList<>(taskList);

        //不使用lambda，以提升性能
        for (IntervalTask task : exeTasks) {
            if (task.canExecute()) {
                task.executeTask();
                if (task.shouldDestroy()) {
                    task2Remove.add(task);
                }
            }
        }
        taskList.removeAll(task2Remove);
    }

    private class IntervalTask {
        final String taskId;

        final int interval;

        final long exeDelay;

        final int loopTimes;

        final Runnable runnable;

        int runTimes = 0;

        long nextRunTime;

        IntervalTask(Runnable task, int interval, int exeDelay, int loopTimes) {
            int rInt = RandomUtil.random(100000);
            this.taskId = String.valueOf(System.currentTimeMillis()).concat(String.valueOf(rInt));
            this.interval = interval * 1000;
            this.exeDelay = exeDelay * 1000L;
            this.loopTimes = loopTimes;
            this.runnable = task;
            this.nextRunTime = System.currentTimeMillis() + this.exeDelay;
        }

        boolean canExecute() {
            return nextRunTime <= System.currentTimeMillis();
        }

        boolean shouldDestroy() {
            return loopTimes > 0 && runTimes >= loopTimes;
        }

        void executeTask() {
            nextRunTime += interval;
            try {
                //为了不阻塞任务线程，使用异步方式将任务放入线程池中
                taskThreadPoolExecutor.putTask(runnable);
            } catch (Exception e) {
                LOG.error("executeTask fail on method[run]:", e);
            }
            runTimes++;
        }

        @Override
        public String toString() {
            return "[taskId:" + taskId + ",interval:" + interval + ",exeDelay:" + exeDelay + ",loopTimes:" + loopTimes +
                    "]";
        }
    }
}
