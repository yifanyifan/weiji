package com.fujiang.weiji.mapper.uas;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fujiang.weiji.entity.uas.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Yifan
 * @since 2021-03-04
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User getUserByUserNameAndPassWord(@Param("userName") String userName, @Param("passWord") String passWord);

    List<User> selectUserTree();
}
