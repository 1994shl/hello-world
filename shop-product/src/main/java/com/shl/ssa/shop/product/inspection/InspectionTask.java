package com.shl.ssa.shop.product.inspection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 施海林
 * @create 2023-11-27 16:16
 * @Description
 */
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class InspectionTask extends Thread {

    private String taskMrid;

    public InspectionTask(String mrid) {
        this.taskMrid = mrid;
        this.taskState = 2;
    }

    private int index = 0;

    private ExecutorService executorService = Executors.newFixedThreadPool(4);

    /**
     * 2.正在执行；3.暂停；4.终止；
     */
    @Getter
    private volatile Integer taskState;

    private TaskResultDto taskResultDto = new TaskResultDto();

    private List<String> pointList = Stream.of(
            "A", "B", "C", "D", "E", "F").collect(Collectors.toList());

    public synchronized void continueExe() {
        log.info("任务继续执行");

        this.taskState = 2;

        log.info("当前执行到第" + (index + 1) + "个点位，继续执行");
    }

    public synchronized void terminateExe() {
        log.info("任务终止执行");

        this.taskState = 4;

        log.info("当前执行到第" + index + "个点位，终止执行");

        executorService.shutdownNow();
    }

    public synchronized void pauseExe() {
        log.info("任务暂停执行");

        this.taskState = 3;

        log.info("当前执行到第" + index + "个点位，暂停执行");
    }

    @SneakyThrows
    private TaskCollectFileDto getPic() {
        taskResultDto.setPicStr("");
        log.info("start getPic");
        if (3 == this.taskState) {
            return new TaskCollectFileDto("暂停");
        }
        for (int i = 0; i <= 5; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("getPicing" + this.index);
        }
        TaskCollectFileDto dto = new TaskCollectFileDto();
        dto.setPath("/path/pic");
        taskResultDto.setPicStr("/path/pic");
        dto.setFlag(true);
        dto.setType(1);
        return dto;
    }

    @SneakyThrows
    private TaskCollectFileDto getVoice() {
        taskResultDto.setVoiceStr("");
        log.info("start getPic");
        if (3 == this.taskState) {
            return new TaskCollectFileDto("暂停");
        }
        for (int i = 0; i <= 15; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("getVoiceing" + this.index);
        }
        TaskCollectFileDto dto = new TaskCollectFileDto();
        dto.setPath("/path/voice");
        taskResultDto.setPicStr("/path/voice");
        dto.setFlag(true);
        dto.setType(1);
        return dto;
    }

    @SneakyThrows
    private TaskCollectFileDto getVideo() {
        taskResultDto.setVideoStr("");
        log.info("start getPic");
        if (3 == this.taskState) {
            return new TaskCollectFileDto("暂停");
        }
        for (int i = 0; i <= 30; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("getVideoing" + this.index);
        }
        TaskCollectFileDto dto = new TaskCollectFileDto();
        dto.setPath("/path/video");
        taskResultDto.setPicStr("/path/video");
        dto.setFlag(true);
        dto.setType(1);
        return dto;
    }

    /**
     * 采集文件
     *
     * @param index
     */
    private TaskResultDto collectFile(int index) {
        TaskResultDto resultDto = new TaskResultDto();
        String fileType = "1,2,3";
        List<Integer> list = Stream.of(fileType.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        CompletableFuture.allOf(list.stream().map(type -> CompletableFuture.runAsync(() -> {
            switch (type) {
                case 1:
                    resultDto.setPicStr(this.getPic().getPath() + this.index);
                    break;
                case 2:
                    resultDto.setVoiceStr(this.getVoice().getPath() + this.index);
                    break;
                case 3:
                    resultDto.setVideoStr(this.getVideo().getPath() + this.index);
                    break;
                default:
                    log.warn("not support!");
            }
        }, executorService)).toArray(CompletableFuture[]::new)).join();
        log.info("第{}轮数据采集为[{}]", this.index, resultDto);
        return resultDto;
    }

    private synchronized void nextIndex() {
        this.index++;
        taskResultDto = new TaskResultDto();
    }

    @Override
    @SneakyThrows
    public void run() {
        while (index < pointList.size()) {
            switch (taskState.intValue()) {
                case 2:
                    //按点位抓图
                    collectFile(this.index);
                    nextIndex();
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
}
