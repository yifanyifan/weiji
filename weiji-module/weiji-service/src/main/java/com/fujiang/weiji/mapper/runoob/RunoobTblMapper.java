package com.fujiang.weiji.mapper.runoob;

import com.fujiang.weiji.entity.runoob.RunoobTbl;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RunoobTblMapper {
    int deleteByPrimaryKey(Integer runoobId);

    int insert(RunoobTbl record);

    int insertSelective(RunoobTbl record);

    RunoobTbl selectByPrimaryKey(Integer runoobId);

    int updateByPrimaryKeySelective(RunoobTbl record);

    int updateByPrimaryKey(RunoobTbl record);
}