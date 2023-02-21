package com.shl.ssa.shop.bean.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.shl.ssa.shop.utils.snowflake.SnowFlakeFactory;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 施海林
 * @create 2022-05-25 14:57
 * @Description 用户实体类
 */
@Data
@TableName("t_user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -934974119389482494L;

    /**
     * 数据id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @TableField(value = "id", fill = FieldFill.INSERT)
    private Long id;

    /**
     * 用户名
     */
    @TableField("t_username")
    private String username;

    /**
     * 密码
     */
    @TableField("t_password")
    private String password;

    /**
     * 手机号
     */
    @TableField("t_phone")
    private String phone;

    /**
     * 地址
     */
    @TableField("t_address")
    private String address;

    public UserEntity() {
        this.id = SnowFlakeFactory.getSnowFlakeFromCache().nextId();
        //默认密码
        this.password = "123456";
    }
}
