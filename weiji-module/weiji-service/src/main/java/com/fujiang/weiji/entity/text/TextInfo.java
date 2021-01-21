package com.fujiang.weiji.entity.text;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

/**
 * 文章信息表
 *
 * @author Yifan
 * @since 2021-01-21
 */
@Data
@TableName("text_info")
public class TextInfo extends Model<TextInfo> {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField(value = "title")
    private String title;

    @TableField(value = "title_md5")
    private String titleMd5;

    @TableField(value = "time")
    private Date time;

    @TableField(value = "context")
    private String context;

    @TableField(value = "created_date", fill = FieldFill.INSERT_UPDATE)
    private Date createdDate;

    @TableField(value = "created_oper")
    private String createdOper;
}
