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
@TableName("scan_rule_level")
public class ScanRuleLevel extends Model<ScanRuleLevel> {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * JSON：第一层Key值
     */
    @TableField(value = "l1")
    private String l1;
    /**
     * JSON：第二层Key值
     */
    @TableField(value = "l2")
    private String l2;
    /**
     * 标题Key
     */
    @TableField(value = "title_field")
    private String titleField;
    /**
     * 时间Key
     */
    @TableField(value = "time_field")
    private String timeField;
    /**
     * 内容Key
     */
    @TableField(value = "context_field")
    private String contextField;

    /**
     * 站点
     */
    @TableField(exist = false)
    private String webUrl;
}
