package com.shl.ssa.shop.product.silent;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
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

    public SilentTask(String mrid) {
        this.mrid = mrid;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true && flag) {
            //模拟执行
            TimeUnit.SECONDS.sleep(3);
            log.info("我在执行静默任务:{}", mrid);
        }
        log.info("task:{} out!", mrid);
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println(sdf.parse("15:15:15"));
    }
}
