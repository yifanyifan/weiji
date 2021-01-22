package com.fujiang.weiji.entity.module;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * 模块_规则映射表
 *
 * @author Yifan
 * @since 2021-01-22
 */
@Data
@TableName("module_scan_rule")
public class ModuleScanRule extends Model<ModuleScanRule> {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 模块表ID
     */
    @TableField(value = "module_id")
    private String moduleId;
    /**
     * 规则表名【必须要，因为可能有多种规则】
     */
    @TableField(value = "scan_rule_name")
    private String scanRuleName;
    /**
     * 规则表ID
     */
    @TableField(value = "scan_rule_id")
    private String scanRuleId;
    /**
     * 网站站点
     */
    private String webUrl;
}
