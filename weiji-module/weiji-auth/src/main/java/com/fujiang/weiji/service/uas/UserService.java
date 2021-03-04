package com.fujiang.weiji.service.uas;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fujiang.weiji.entity.uas.User;

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
}
