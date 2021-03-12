package com.fujiang.weiji.entity.uas;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author Yifan
 * @since 2021-03-04
 */
@Data
@TableName("user")
public class User {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("user_name")
    private String userName;

    @TableField("pass_word")
    private String passWord;
}
