package com.shl.ssa.shop.product.controller;

import com.shl.ssa.shop.product.inspection.VideoTaskHolder;
import com.shl.ssa.shop.product.silent.SilentTaskHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 施海林
 * @create 2023-07-26 10:08
 * @Description
 */
@Slf4j
@RestController
public class VideoTaskControler {

    @Resource
    private VideoTaskHolder holder;

    @Resource
    private SilentTaskHolder silentTaskHolder;

    @PostMapping(value = "/start")
    public String startVideoTask(@RequestBody StartTaskRequest request) {
        holder.startVideoTask(request.getTaskMrid());
        return request.getTaskMrid();
    }

    @PostMapping(value = "/pause")
    public String pauseVideoTask(@RequestBody StartTaskRequest request) {
        holder.pauseVideoTask(request.getTaskMrid());
        return request.getTaskMrid();
    }

    @PostMapping(value = "/continue")
    public String continueVideoTask(@RequestBody StartTaskRequest request) {
        holder.continueVideoTask(request.getTaskMrid());
        return request.getTaskMrid();
    }

    @PostMapping(value = "/terminate")
    public String terminateVideoTask(@RequestBody StartTaskRequest request) {
        holder.terminateVideoTask(request.getTaskMrid());
        return request.getTaskMrid();
    }

    @PostMapping(value = "/setPic")
    public String setPic(@RequestBody StartTaskRequest request) {
        holder.setPic(request.getTaskMrid());
        return request.getTaskMrid();
    }

    @PostMapping(value = "/setAnalysis")
    public String setAnalysis(@RequestBody StartTaskRequest request) {
        holder.setAnalysis(request.getTaskMrid());
        return request.getTaskMrid();
    }

    @PostMapping(value = "/putSilent")
    public String putSilent(@RequestBody StartTaskRequest request) {
        silentTaskHolder.putTask(request.getTaskMrid());
        return request.getTaskMrid();
    }

    @PostMapping(value = "/stopSilent")
    public String stopSilent(@RequestBody StartTaskRequest request) {
        silentTaskHolder.stopTask(request.getTaskMrid());
        return request.getTaskMrid();
    }

}
