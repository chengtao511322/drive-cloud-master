package com.drive.gateway.controller;

import com.drive.common.core.biz.ResObject;
import com.drive.gateway.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


/**
 * 获取验证码
 * @author xiaoguo
 */
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @Autowired
    private CaptchaService captchaService;

    /**
     * 生成验证码
     */
    @GetMapping("/getCaptchaCode")
    public Mono<ResObject> getCaptchaCode() {

        ResObject capchaRes = captchaService.createCaptcha();

        return Mono.just(capchaRes);
    }
}
