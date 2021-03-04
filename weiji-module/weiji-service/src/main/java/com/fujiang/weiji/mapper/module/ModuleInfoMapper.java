package com.fujiang.weiji.mapper.module;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fujiang.weiji.entity.module.ModuleInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Yifan
 * @since 2021-01-22
 */
@Mapper
public interface ModuleInfoMapper extends BaseMapper<ModuleInfo> {
    /**
     * 获取栏目集合
     *
     * @return
     */
    List<ModuleInfo> getColumnList();
}
