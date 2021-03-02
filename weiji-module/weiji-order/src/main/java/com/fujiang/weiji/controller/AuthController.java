package com.fujiang.weiji.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/oauth")
public class AuthController {
    /*@Value("${rsa.publicKey}")
    public String publicKey;*/

    @GetMapping("/publicKey")
    public String getPublicKey() {
        return "333333333333333333";
    }
}
