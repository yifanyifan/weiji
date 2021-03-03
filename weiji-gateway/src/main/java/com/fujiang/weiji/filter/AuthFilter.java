package com.fujiang.weiji.filter;

import com.alibaba.fastjson.JSONObject;
import com.fujiang.weiji.dto.base.DataResponse;
import utils.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.PathContainer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

@Component
public class AuthFilter implements GlobalFilter {
    //不需要校验的路径
    private String[] skipAuthUrls = {"/token/login", "/token/userId", "refresh"};

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse resp = exchange.getResponse();
        HttpHeaders header = request.getHeaders();
        HttpMethod method = request.getMethod();
        String token = header.getFirst(JwtUtil.HEADER_AUTH);
        String userId = header.getFirst(JwtUtil.HEADER_USERID);
        PathContainer pathContainer = request.getPath().pathWithinApplication();
        String path = pathContainer.value();

        //跳过不需要验证的路径
        if (null != skipAuthUrls && Arrays.asList(skipAuthUrls).contains(path)) {
            return chain.filter(exchange);
        }

        //携带token请求其他业务接口
        Map<String, String> validateResultMap = JwtUtil.validateTokenAndUser(token, userId);
        if (validateResultMap == null || validateResultMap.isEmpty()) {
            return authErro(resp, "please login");
        }
        // TODO 将用户信息存放在请求header中传递给下游业务
        Route gatewayUrl = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        URI uri = gatewayUrl.getUri();
        //表示下游请求对应的服务名如 SPRING-CLOUD-SERVICE  SPRING-CLOUD-GATEWAY
        String serviceName = uri.getHost();

        ServerHttpRequest.Builder mutate = request.mutate();
        mutate.header("x-user-id", validateResultMap.get("userid"));
        mutate.header("x-user-name", validateResultMap.get("user"));
        mutate.header("x-user-serviceName", serviceName);
        ServerHttpRequest buildReuqest = mutate.build();

        //todo 如果响应中需要放数据，也可以放在response的header中
        //ServerHttpResponse response = exchange.getResponse();
        //response.getHeaders().add("new_token","token_value");
        return chain.filter(exchange.mutate().request(buildReuqest).build());
    }

    private Mono<Void> authErro(ServerHttpResponse resp, String mess) {
        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        DataResponse returnData = new DataResponse(org.apache.http.HttpStatus.SC_UNAUTHORIZED, mess, mess);
        String returnStr = JSONObject.toJSONString(returnData);
        DataBuffer buffer = resp.bufferFactory().wrap(returnStr.getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }
}
