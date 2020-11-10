package com.fujiang.weiji.service.impl;

import com.fujiang.weiji.entity.runoob.RunoobTbl;
import com.fujiang.weiji.mapper.runoob.RunoobTblMapper;
import com.fujiang.weiji.service.RunoobTblService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Yifan
 * @Description:
 * @date: 2020/8/15
 * Modified By:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RunoobTblServiceImpl implements RunoobTblService {
    @Autowired
    private RunoobTblMapper runoobTblMapper;

    @Override
    public void insert(RunoobTbl runoobTbl) {
        runoobTblMapper.insert(runoobTbl);
    }
}
