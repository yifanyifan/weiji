package com.fujiang.weiji.entity.module;

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
 * @since 2021-01-22
 */
@Data
@TableName("scan_rule_regular")
public class ScanRuleRegular extends Model<ScanRuleRegular> {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 内容站点前缀
     */
    @TableField(value = "main_url")
    private String mainUrl;
    /**
     * 列表开始
     */
    @TableField(value = "list_start")
    private String listStart;
    /**
     * 列表结束
     */
    @TableField(value = "list_end")
    private String listEnd;
    /**
     * 内容页URL开始
     */
    @TableField(value = "url_start")
    private String urlStart;
    /**
     * 内容页URL结束
     */
    @TableField(value = "url_end")
    private String urlEnd;
    /**
     * 标题开始
     */
    @TableField(value = "title_start")
    private String titleStart;
    /**
     * 标题结束
     */
    @TableField(value = "title_end")
    private String titleEnd;
    /**
     * 时间开始
     */
    @TableField(value = "time_start")
    private String timeStart;
    /**
     * 时间结束
     */
    @TableField(value = "time_end")
    private String timeEnd;
    /**
     * 来源开始
     */
    @TableField(value = "source_start")
    private String sourceStart;
    /**
     * 来源结束
     */
    @TableField(value = "source_end")
    private String sourceEnd;
    /**
     * 内容开始
     */
    @TableField(value = "context_start")
    private String contextStart;
    /**
     * 内容结束
     */
    @TableField(value = "context_end")
    private String contextEnd;

    /**
     * 站点
     */
    @TableField(exist = false)
    private String webUrl;
}
