package com.shl.ssa.shop.product.inspection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 施海林
 * @create 2023-11-27 16:37
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskCollectFileDto implements Serializable {

    private static final long serialVersionUID = 120248044835567584L;

    private Integer type;

    private boolean flag;

    private String path;

    private String errorMsg;

    /**
     * 红外截图特有结果
     */
    private String temperature;

    public TaskCollectFileDto(String errorMsg){
        this.flag = false;
        this.errorMsg = errorMsg;
    }
}
