package com.fujiang.weiji.service.uas.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fujiang.weiji.entity.uas.User;
import com.fujiang.weiji.mapper.uas.UserMapper;
import com.fujiang.weiji.service.uas.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Yifan
 * @since 2021-03-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUserNameAndPassWord(String userName, String passWord) {
        return userMapper.getUserByUserNameAndPassWord(userName, passWord);
    }
}
