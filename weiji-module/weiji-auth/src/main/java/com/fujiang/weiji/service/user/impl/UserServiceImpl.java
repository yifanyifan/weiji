package com.fujiang.weiji.service.user.impl;

import com.fujiang.weiji.entity.user.User;
import com.fujiang.weiji.mapper.user.UserMapper;
import com.fujiang.weiji.service.user.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yifan
 * @since 2021-03-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
