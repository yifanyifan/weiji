package com.fujiang.weiji.mapper.orde;

import com.fujiang.weiji.entity.orde.OrdeOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdeOrderMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrdeOrder record);

    int insertSelective(OrdeOrder record);

    OrdeOrder selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrdeOrder record);

    int updateByPrimaryKey(OrdeOrder record);
}