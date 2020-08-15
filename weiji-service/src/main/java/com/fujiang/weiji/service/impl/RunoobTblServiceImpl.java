package com.fujiang.weiji.service.impl;

import com.fujiang.weiji.entity.runoob.RunoobTbl;
import com.fujiang.weiji.mapper.runoob.RunoobTblMapper;
import com.fujiang.weiji.service.RunoobTblService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: Yifan
 * @Description:
 * @date: 2020/8/15
 * Modified By:
 */
public class RunoobTblServiceImpl implements RunoobTblService {
    @Autowired
    private RunoobTblMapper runoobTblMapper;

    @Override
    public void insert(RunoobTbl runoobTbl) {
        runoobTblMapper.insert(runoobTbl);
    }
}
