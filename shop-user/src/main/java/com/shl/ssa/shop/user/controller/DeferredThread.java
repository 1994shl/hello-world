package com.shl.ssa.shop.user.controller;

import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author 施海林
 * @create 2022-11-23 16:15
 * @Description
 */
public class DeferredThread extends Thread {

    private static DeferredResult<String> deferredResult;

    public DeferredThread(DeferredResult<String> deferredResult) {
        this.deferredResult = deferredResult;
    }

    @Override
    public void run() {
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        deferredResult.setResult("hello world");
    }
}
