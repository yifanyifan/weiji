package com.fujiang.weiji.mapper.text;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fujiang.weiji.entity.text.TextInfo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Yifan
 * @since 2021-01-21
 */
public interface TextInfoMapper extends BaseMapper<TextInfo> {
    Integer getCountByTitleMd5(@Param("titleMd5") String titleMd5);
}
