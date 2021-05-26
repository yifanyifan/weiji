package com.fujiang.weiji.entity.uas;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fujiang.weiji.enumeration.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

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
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("user_name")
    private String userName;

    @TableField("pass_word")
    private String passWord;

    @TableField("nick_name")
    private String nickName;

    @TableField("salt")
    private String salt;

    @TableField("token")
    private String token;

    @TableField("gmt_create")
    private Date gmtCreate;

    @TableField("gmt_modified")
    private Date gmtModified;

    @TableField(exist = false)
    private List<Role> roleList;
}
