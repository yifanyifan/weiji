package com.fujiang.weiji.controller.token;

import com.alibaba.fastjson.JSONObject;
import com.fujiang.weiji.config.RedisUtil;
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

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/token")
public class TokenController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 登录接口
     *
     * @param param（userName +passWord）
     * @return
     */
    @PostMapping("/login")
    public DataResponse getToken(@RequestBody Map<String, String> param) {
        if (param == null || param.isEmpty()) {
            return new DataResponse(HttpStatus.ERROR.getCode(), "请输入用户名密码", "请输入用户名密码");
        }
        String userName = param.get("userName");
        String passWord = param.get("passWord");

        User user = userService.getUserByUserNameAndPassWord(userName, passWord);
        if (user == null) {
            return new DataResponse(HttpStatus.ERROR.getCode(), "用户名密码错误", "用户名密码错误");
        }

        //生成token
        String token = JwtUtil.generateToken(user.getId());

        // 将token放入redis中，设置超时时间为 31分钟
        Map map = new HashMap<String, Object>();
        map.put(user.getId(), JSONObject.toJSONString(user));
        redisUtil.hmset("USER_TOKEN", map);

        return new DataResponse(HttpStatus.SUCCESS.getCode(), token, null);
    }
}
