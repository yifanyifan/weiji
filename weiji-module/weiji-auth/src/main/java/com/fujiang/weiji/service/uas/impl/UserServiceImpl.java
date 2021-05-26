package com.fujiang.weiji.service.uas.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fujiang.weiji.config.security.dto.SecurityUser;
import com.fujiang.weiji.entity.uas.User;
import com.fujiang.weiji.mapper.uas.RoleMapper;
import com.fujiang.weiji.mapper.uas.UserMapper;
import com.fujiang.weiji.service.uas.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

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
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User getUserByUserNameAndPassWord(String userName, String passWord) {
        return userMapper.getUserByUserNameAndPassWord(userName, passWord);
    }

    @Override
    public List<User> selectUserTree() {
        List<User> userList = userMapper.selectUserTree();
        return userList;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中取出用户信息
        List<User> userList = userMapper.getUserByUserName(username);
        User user;
        // 判断用户是否存在
        if (!CollectionUtils.isEmpty(userList)) {
            user = userList.get(0);
        } else {
            throw new UsernameNotFoundException("用户名不存在！");
        }
        // 返回UserDetails实现类
        return new SecurityUser(user);
    }
}
