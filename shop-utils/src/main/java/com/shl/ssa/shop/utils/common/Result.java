package com.shl.ssa.shop.utils.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.shl.ssa.shop.utils.constant.HttpCode.SUCCESS;

/**
 * @author 施海林
 * @create 2022-05-25 11:30
 * @Description 通用返回类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -7770772169102489441L;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态描述
     */
    private String codeMsg;

    /**
     * 返回的数据
     */
    private T data;

    public Result(T t) {
        this.setCode(SUCCESS);
        this.setCodeMsg("成功");
        this.setData(t);
    }
}
