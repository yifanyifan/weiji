package com.fujiang.weiji.controller.uas;


import com.fujiang.weiji.dto.base.DataResponse;
import com.fujiang.weiji.entity.uas.User;
import com.fujiang.weiji.service.uas.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Yifan
 * @since 2021-03-04
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/selectUserTree", method = RequestMethod.POST)
    public DataResponse selectUserTree() {
        DataResponse dataResponse = new DataResponse();
        List<User> userList = userService.selectUserTree();
        dataResponse.setCode(200);
        dataResponse.setObject(userList);
        return dataResponse;
    }
}

