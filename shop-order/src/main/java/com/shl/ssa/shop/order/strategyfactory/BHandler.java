package com.shl.ssa.shop.order.strategyfactory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author 施海林
 * @create 2023-02-15 15:33
 * @Description
 */
@Slf4j
@Component
public class BHandler implements Handler {

    @Override
    public void handle() {
        System.out.println("BHandler完成任务");
    }

}
