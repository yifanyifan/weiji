package com.fujiang.weiji.service.uas;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fujiang.weiji.entity.uas.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Yifan
 * @since 2021-03-04
 */
public interface UserService extends IService<User> {
    User getUserByUserNameAndPassWord(String userName, String passWord);

    List<User> selectUserTree();

    UserDetails loadUserByUsername(String var1) throws UsernameNotFoundException;
}
