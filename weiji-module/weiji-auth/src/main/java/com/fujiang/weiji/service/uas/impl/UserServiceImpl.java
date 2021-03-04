package com.fujiang.weiji.service.uas.impl;

import com.fujiang.weiji.entity.uas.User;
import com.fujiang.weiji.mapper.uas.UserMapper;
import com.fujiang.weiji.service.uas.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yifan
 * @since 2021-03-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
