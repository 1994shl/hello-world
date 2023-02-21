package com.shl.ssa.shop.order.strategyfactory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author 施海林
 * @create 2023-02-15 15:32
 * @Description
 */
@Slf4j
@Component
public class AHandler implements Handler {
    @Override
    public void handle() {

        System.out.println("AHandler完成任务");
    }

}
