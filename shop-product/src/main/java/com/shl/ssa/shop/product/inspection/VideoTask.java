package com.shl.ssa.shop.product.inspection;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 施海林
 * @create 2023-07-25 16:53
 * @Description
 */
@Slf4j
public class VideoTask extends Thread {

    private String taskMrid;

    private volatile int index;

    /**
     * 设置分析结果
     *
     * @param analysisObj
     */
    public void setAnalysisObj(Object analysisObj) {
        this.analysisObj = analysisObj;
    }

    /**
     * 主图结果
     */
    private Object picObj;

    /**
     * 分析结果
     */
    private Object analysisObj;

    public Object getPicObj() {
        return picObj;
    }

    public void setPicObj(Object picObj) {
        this.picObj = picObj;
    }

    public Object getAnalysisObj() {
        return analysisObj;
    }

    /**
     * 2.正在执行；3.暂停；4.终止（暂只有这三种状态）
     */
    private volatile Long taskState;


    public VideoTask(String taskMrid, List<String> list) {
        this.taskMrid = taskMrid;
        this.pointList = list;
        this.taskState = 2L;
        this.index = 0;
        this.picObj = null;
        this.analysisObj = null;
    }

    private List<String> pointList;

    public synchronized void continueExe() {
        log.info("任务继续执行");


        taskState = 2L;

        //上报任务继续状态

        log.info("当前执行到第" + (index + 1) + "个点位，继续执行");
    }

    public synchronized void terminateExe() {
        log.info("任务终止执行");

        taskState = 4L;

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
        /*for (String point : pointList) {
            //按点位抓图
            snapPic(point);
            //送去分析
            analysisPic(point);
            //根据分析结果上送结果
            reportResult(point);
        }*/

        //上报任务开始执行

        while (index < pointList.size()) {
            switch (taskState.intValue()) {
                case 2:
                    //按点位抓图
                    String snapPic = snapPic(pointList.get(index));

                    log.info("成功抓到点位" + pointList.get(index) + "的预置位截图！");

                    //送去分析
                    String analysisResult = analysisPic(snapPic);
                    //根据分析结果上送结果
                    reportResult(analysisResult);
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

    private String snapPic(String point) {
        log.info("按点位抓图:" + point);
        //暂停或正在执行
        if (2 != taskState) {
            log.info("暂停或终止执行，直接退出snapPic");
            return null;
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            log.info("按点位抓图失败:" + point);
        }

        while (null == this.picObj) {
            //暂停或正在执行
            if (2 != taskState) {
                log.info("暂停或终止执行，直接退出snapPic");
                return null;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("点位抓图成功:" + point);

        String picUrl = picObj.toString();
        picObj = null;

        return picUrl;

    }

    private String analysisPic(String snapPic) {
        log.info("送去分析:" + snapPic);
        //暂停或正在执行
        if (2 != taskState || StringUtils.isBlank(snapPic)) {
            log.info("暂停或终止执行，直接退出analysisPic");
            return null;
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            log.info("送去分析失败:" + snapPic);
        }

        while (null == this.analysisObj) {
            if (2 != taskState) {
                log.info("暂停或终止执行，直接退出analysisPic");
                return null;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        log.info("分析截图成功:" + snapPic);

        String analysis = analysisObj.toString();
        analysisObj = null;
        return analysis;
    }

    /**
     * 保证此方法的原子性
     * （若刚好暂停在此处，正常上报（不影响摄像头等资源），但不继续往下执行）
     *
     * @param analysisResult
     */
    private synchronized void reportResult(String analysisResult) {
        log.info("根据分析结果上送结果:" + analysisResult);
        if (StringUtils.isBlank(analysisResult)) {
            log.info("分析结果为空，放弃上送（暂停或终止执行）");
            return;
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            log.info("根据分析结果上送结果失败:" + analysisResult);
        }

        //结果上报成功，准备下一个点位执行
        index++;
        picObj = null;
        analysisObj = null;
        log.info("根据分析结果上送结果成功:" + analysisResult);
    }
}
