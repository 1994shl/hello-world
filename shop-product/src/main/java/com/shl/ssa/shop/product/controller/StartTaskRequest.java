package com.shl.ssa.shop.product.controller;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 施海林
 * @create 2023-07-26 10:58
 * @Description
 */
@Data
public class StartTaskRequest implements Serializable {

    private static final long serialVersionUID = -4693254273725573607L;

    private String taskMrid;
}
