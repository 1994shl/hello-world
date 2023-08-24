package com.shl.ssa.shop.product.inspection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 施海林
 * @create 2023-07-25 16:53
 * @Description
 */
@Slf4j
@Component
public class VideoTaskHolder {

    private static Map<String, VideoTask> taskMap = new HashMap<>(128);

    private static Map<String, VideoParallelSubTask> parallelTaskMap = new HashMap<>(128);

    public String startVideoTask(String taskMrid) {
        /*List<String> list = Arrays.asList(taskMrid.split(","));
        VideoTask task = new VideoTask(taskMrid, list);
        taskMap.put(taskMrid, task);
        task.start();
        return taskMrid;*/

        VideoParallelSubTask task = new VideoParallelSubTask(taskMrid);
        parallelTaskMap.put(taskMrid, task);
        task.start();
        return taskMrid;
    }

    public void pauseVideoTask(String taskMrid) {
        VideoParallelSubTask task = parallelTaskMap.get(taskMrid);
        if (null != task) {
            task.pauseExe();
        } else {
            log.info("No video task to pause");
        }
    }

    public void continueVideoTask(String taskMrid) {
        VideoParallelSubTask task = parallelTaskMap.get(taskMrid);
        if (null != task) {
            task.continueExe();
        } else {
            log.info("No video task to continue");
        }
    }

    public void terminateVideoTask(String taskMrid) {
        VideoParallelSubTask task = parallelTaskMap.get(taskMrid);
        if (null != task) {
            task.terminateExe();
        } else {
            log.info("No video task to terminate");
        }
    }

    public void setPic(String taskMrid) {
        VideoTask task = taskMap.get(taskMrid);
        if (null != task) {
            log.info("模拟设置巡视点截图结果");
            task.setPicObj("我是一张预置位截图");
        } else {
            log.info("No video task to setPic");
        }
    }

    public void setAnalysis(String taskMrid) {
        VideoTask task = taskMap.get(taskMrid);
        if (null != task) {
            log.info("模拟设置巡视点分析结果");
            task.setAnalysisObj("我是巡视点分析结果");
        } else {
            log.info("No video task to setAnalysis");
        }
    }

    public static void main(String[] args) {


        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        System.out.println(list.stream().map(String::valueOf).collect(Collectors.joining(",")));

    }
}
