package com.shl.ssa.shop.utils.common;

import com.shl.ssa.shop.utils.constant.HttpCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 施海林
 * @create 2022-05-25 11:35
 * @Description
 */
@Slf4j
@RestControllerAdvice
public class RestCtrlExceptionHandler {

    /**
     * 全局异常处理，统一返回状态码
     */
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        log.error("应用抛出了异常：{}", e);
        return new Result(HttpCode.FAILURE, "执行失败", e.getMessage());
    }
}
