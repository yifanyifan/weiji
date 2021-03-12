package com.fujiang.weiji.controller.token;

import com.fujiang.weiji.dto.base.DataResponse;
import com.fujiang.weiji.entity.uas.User;
import com.fujiang.weiji.service.uas.UserService;
import enums.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.JwtUtil;

import java.util.Map;

@RestController
@RequestMapping("/token")
public class TokenController {
    @Autowired
    private UserService userService;

    /**
     * 登录接口
     *
     * @param user（userName +passWord）
     * @return
     */
    @PostMapping("/login")
    public DataResponse getToken(@RequestBody Map<String, String> user) {
        User u = new User();
        u.setPassWord("ddd");
        u.setUserName("ddd");
        userService.save(u);

        if (user == null || user.isEmpty()) {
            return new DataResponse(HttpStatus.ERROR.getCode(), "请输入用户名密码", "请输入用户名密码");
        }
        String userName = user.get("userName");
        String passWord = user.get("passWord");
        if (!doLogin(userName, passWord)) {
            return new DataResponse(HttpStatus.ERROR.getCode(), "用户名密码错误", "用户名密码错误");
        }
        String token = JwtUtil.generateToken(userName);
        // todo 将token放入redis中，设置超时时间为 2 * t
        return new DataResponse(HttpStatus.SUCCESS.getCode(), token, null);
    }

    private Boolean doLogin(String userName, String passWord) {
        User user = userService.getUserByUserNameAndPassWord(userName, passWord);
        if (user == null) {
            return false;
        }
        return true;
    }
}
