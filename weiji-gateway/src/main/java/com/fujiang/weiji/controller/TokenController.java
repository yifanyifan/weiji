package com.fujiang.weiji.controller;

import com.fujiang.weiji.utils.JwtUtil;
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
     * @param user（userID +pwd）
     * @return
     */
    @PostMapping("/userId/pwd")
    public String getToken(@RequestBody Map<String, String> user) {
        //用户名密码需要加密处理
        String result = "";
        if (user == null || user.isEmpty()) {
            return result;
        }
        String userId = user.get("userId");
        String pwd = user.get("pwd");
        if (!doLogin(userId, pwd)) {
            return result;
        }
        String token = JwtUtil.generateToken(userId);
        // todo 将token放入redis中，设置超时时间为 2 * t
        return token;
    }

    private Boolean doLogin(String userId, String pwd) {
        //后续对接user表验证
        if ("admin".equals(userId) && "123".equals(pwd)) {
            return true;
        }
        if ("spring".equals(userId) && "123".equals(pwd)) {
            return true;
        }
        if ("gateway".equals(userId) && "123".equals(pwd)) {
            return true;
        }
        return false;
    }
}
