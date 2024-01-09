package com.shl.ssa.shop.product.inspection;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 施海林
 * @create 2023-11-27 16:41
 * @Description
 */
@Data
public class TaskResultDto implements Serializable {

    private static final long serialVersionUID = 4832690025979444750L;

    private String videoStr;

    private String picStr;

    private String voiceStr;
}
