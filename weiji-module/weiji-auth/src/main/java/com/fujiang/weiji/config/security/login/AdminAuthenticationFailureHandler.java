package com.fujiang.weiji.config.security.login;

import com.fujiang.weiji.config.security.ResponseUtils;
import com.fujiang.weiji.dto.base.DataResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p> 认证失败处理 - 前后端分离情况下返回json数据格式 </p>
 *
 * @author : zhengqing
 * @description :
 * @date : 2019/10/12 15:33
 */
@Slf4j
@Component
public class AdminAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        DataResponse result;
        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
            result = new DataResponse(500, e.getMessage());
        } else if (e instanceof LockedException) {
            result = new DataResponse(500, "账户被锁定，请联系管理员!");
        } else if (e instanceof CredentialsExpiredException) {
            result = new DataResponse(500, "证书过期，请联系管理员!");
        } else if (e instanceof AccountExpiredException) {
            result = new DataResponse(500, "账户过期，请联系管理员!");
        } else if (e instanceof DisabledException) {
            result = new DataResponse(500, "账户被禁用，请联系管理员!");
        } else {
            log.error("登录失败：", e);
            result = new DataResponse(500, "登录失败!");
        }
        ResponseUtils.out(response, result);
    }

}
