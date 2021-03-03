package com.fujiang.weiji.controller;

import utils.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/token")
public class TokenController {
    /**
     * 登录接口
     *
     * @param user（userName +passWord）
     * @return
     */
    @PostMapping("/login")
    public String getToken(@RequestBody Map<String, String> user) {
        //用户名密码需要加密处理
        String result = "";
        if (user == null || user.isEmpty()) {
            return result;
        }
        String userName = user.get("userName");
        String passWord = user.get("passWord");
        if (!doLogin(userName, passWord)) {
            return result;
        }
        String token = JwtUtil.generateToken(userName);
        // todo 将token放入redis中，设置超时时间为 2 * t
        return token;
    }

    private Boolean doLogin(String userName, String passWord) {
        //后续对接user表验证
        if ("admin".equals(userName) && "123".equals(passWord)) {
            return true;
        }
        if ("spring".equals(userName) && "123".equals(passWord)) {
            return true;
        }
        if ("gateway".equals(userName) && "123".equals(passWord)) {
            return true;
        }
        return false;
    }
}
