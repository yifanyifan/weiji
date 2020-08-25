package com.fujiang.weiji.service.impl;

import com.fujiang.weiji.entity.orde.OrdeOrder;
import com.fujiang.weiji.mapper.orde.OrdeOrderMapper;
import com.fujiang.weiji.service.OrdeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Yifan
 * @Description:
 * @date: 2020/8/15
 * Modified By:
 */
@Service
public class OrdeOrderServiceImpl implements OrdeOrderService {
    @Autowired
    private OrdeOrderMapper ordeOrderMapper;

    @Override
    public void insert(OrdeOrder ordeOrder) {
        ordeOrderMapper.insert(ordeOrder);
    }
}
