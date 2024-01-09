package com.shl.ssa.shop.product.inspection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 施海林
 * @create 2023-07-25 16:53
 * @Description
 */
@Slf4j
@Component
public class VideoTaskHolder {

    private static Map<String, VideoTask> taskMap = new HashMap<>(128);

    private static Map<String, InspectionTask> parallelTaskMap = new HashMap<>(128);

    public String startVideoTask(String taskMrid) {
        /*List<String> list = Arrays.asList(taskMrid.split(","));
        VideoTask task = new VideoTask(taskMrid, list);
        taskMap.put(taskMrid, task);
        task.start();
        return taskMrid;*/

        InspectionTask task = new InspectionTask(taskMrid);
        parallelTaskMap.put(taskMrid, task);
        task.start();
        return taskMrid;
    }

    public void pauseVideoTask(String taskMrid) {
        InspectionTask task = parallelTaskMap.get(taskMrid);
        if (null != task) {
            task.pauseExe();
        } else {
            log.info("No video task to pause");
        }
    }

    public void continueVideoTask(String taskMrid) {
        InspectionTask task = parallelTaskMap.get(taskMrid);
        if (null != task) {
            task.continueExe();
        } else {
            log.info("No video task to continue");
        }
    }

    public void terminateVideoTask(String taskMrid) {
        InspectionTask task = parallelTaskMap.remove(taskMrid);
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


        List<String> list = new ArrayList<>();

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");

       /* list.forEach(item -> {
            switch (item){
                case "a":
                    System.out.println(item);
                    break;
                case "b":
                    System.out.println(item);
                    break;
                case "c":
                    System.out.println(item);
                    break;
                case "d":
                    System.out.println(item);
                    break;
                case "e":
                    System.out.println(item);
                    break;
                default:
                    System.out.println("hello");
            }
        });*/

        switch (list.get(1)){
            case "a":
                System.out.println(1);
                break;
            case "b":
                System.out.println(2);
                break;
            case "c":
                System.out.println(3);
                break;
            case "d":
                System.out.println(4);
                break;
            case "e":
                System.out.println(5);
                break;
            default:
                System.out.println("hello");
        }


       // System.out.println(list.stream().map(String::valueOf).collect(Collectors.joining(",")));

    }
}
