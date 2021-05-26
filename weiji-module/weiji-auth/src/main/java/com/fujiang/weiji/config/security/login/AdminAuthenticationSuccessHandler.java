package com.fujiang.weiji.config.security.login;

import com.fujiang.weiji.config.security.ResponseUtils;
import com.fujiang.weiji.config.security.dto.SecurityUser;
import com.fujiang.weiji.dto.base.DataResponse;
import com.fujiang.weiji.entity.uas.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p> 认证成功处理 </p>
 *
 * @author : zhengqing
 * @description :
 * @date : 2019/10/12 15:31
 */
@Component
public class AdminAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
        User user = new User();
        SecurityUser securityUser = ((SecurityUser) auth.getPrincipal());
        user.setToken(securityUser.getCurrentUserInfo().getToken());
        DataResponse result = new DataResponse(200, "登录成功!", user);
        ResponseUtils.out(response, result);
    }

}
