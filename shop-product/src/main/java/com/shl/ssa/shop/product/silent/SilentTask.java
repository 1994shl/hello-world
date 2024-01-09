package com.shl.ssa.shop.product.silent;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**
 * @author 施海林
 * @create 2023-08-16 15:44
 * @Description 静默任务
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class SilentTask implements Runnable {

    private volatile boolean flag = true;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    private String mrid;

    /**
     * 停止执行
     */
    public void stopSilentMonitorTask() {
        this.flag = false;
        log.info("current name is {}", Thread.currentThread().getName());
    }


    public SilentTask(String mrid) {
        this.mrid = mrid;
    }

    public void test() {
        log.info("current name is {}", Thread.currentThread().getName());
    }

    @Override
    public void run() {

        //Thread.currentThread().interrupt();


        while (flag) {
            log.info("我在执行静默任务:{},current thread is interrupted {}", mrid, Thread.currentThread().isInterrupted());

            test();
            //模拟执行
            try {
                TimeUnit.SECONDS.sleep(20);
                log.info("current thread is interrupted {}", Thread.currentThread().isInterrupted());

            } catch (InterruptedException e) {

                log.info("catch InterruptedException,so quit!");
                break;
            }
        }
        log.info("task:{} out!", mrid);
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println(sdf.parse("15:15:15"));


        String value = "A.123";
        Float f = Float.valueOf(value);

        System.out.println(f);
    }
}
