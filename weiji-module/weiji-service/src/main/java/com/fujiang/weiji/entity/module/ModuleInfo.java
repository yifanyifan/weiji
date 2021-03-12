package com.fujiang.weiji.entity.module;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * 系统栏目模块表
 *
 * @author Yifan
 * @since 2021-01-22
 */
@Data
@TableName("module_info")
public class ModuleInfo {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 模块名称
     */
    @TableField(value = "module_name")
    private String moduleName;
    /**
     * 父级【0为频道, 其它为栏目】
     */
    @TableField(value = "parent_id")
    private String parentId;

}
