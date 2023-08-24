package com.shl.ssa.shop.product.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 施海林
 * @create 2023-08-16 15:31
 * @Description
 */
@Service
public class TaskThreadPoolExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(TaskThreadPoolExecutor.class);

    private static final int SIZE = 4;

    private static final int MAX_SIZE = 8;

    private static final int MAX_COUNT = Integer.MAX_VALUE;

    private static final int TASK_TIMEOUT = 30;

    private ThreadPoolExecutor tpExecutor;

    public TaskThreadPoolExecutor() {
        this(SIZE, MAX_SIZE, MAX_COUNT, TASK_TIMEOUT);
    }

    public TaskThreadPoolExecutor(int size, int max, int count, int timeout) {
        tpExecutor = new ThreadPoolExecutor(size,
                max,
                timeout,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(count),
                new MyExecutorPolicy()) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                if (t == null && r instanceof Future<?>) {
                    try {
                        ((Future<?>) r).get();
                    } catch (CancellationException ce) {
                        t = ce;
                    } catch (ExecutionException ee) {
                        t = ee.getCause();
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt(); // ignore/reset
                    }
                }
                if (t != null) {
                    LOG.error("error occurred during task ", t);
                }
            }
        };
    }

    public void putTask(Runnable task) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("put task to executor.");
        }

        try {
            tpExecutor.execute(task);
        } catch (Exception e) {
            LOG.error("putTask Fail: ", e);
        }
    }

    protected int getTaskCount() {
        return tpExecutor.getQueue().size();
    }

    protected int getActiveThreadCount() {
        return tpExecutor.getActiveCount();
    }


    public static class MyExecutorPolicy implements RejectedExecutionHandler {
        /**
         * Creates a {@code DiscardPolicy}.
         */
        MyExecutorPolicy() {
        }

        /**
         * Does nothing, which has the effect of discarding task r.
         *
         * @param r the runnable task requested to be executed
         * @param e the executor attempting to execute this task
         */
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            LOG.error("rejected execution.", e);
        }
    }
}
