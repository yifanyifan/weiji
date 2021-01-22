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
    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;
    /**
     * 标题MD5
     */
    @TableField(value = "title_md5")
    private String titleMd5;
    /**
     * 来源
     */
    @TableField(value = "source")
    private String source;
    /**
     * 时间
     */
    @TableField(value = "time")
    private String time;
    /**
     * 内容
     */
    @TableField(value = "context")
    private String context;
    /**
     * 模块表Id
     */
    @TableField(value = "module_id")
    private String module_id;
    /**
     * 扫描URl
     */
    @TableField(value = "url")
    private String url;
    /**
     * 扫描时间
     */
    @TableField(value = "created_date", fill = FieldFill.INSERT_UPDATE)
    private Date createdDate;
    /**
     * 扫描用户
     */
    @TableField(value = "created_oper")
    private String createdOper;
}
