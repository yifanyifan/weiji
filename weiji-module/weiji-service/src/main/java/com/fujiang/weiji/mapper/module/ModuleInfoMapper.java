package com.fujiang.weiji.mapper.module;

import com.fujiang.weiji.entity.module.ModuleInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yifan
 * @since 2021-01-22
 */
public interface ModuleInfoMapper extends BaseMapper<ModuleInfo> {
    /**
     * 获取栏目集合
     * @return
     */
    List<ModuleInfo> getColumnList();
}
