package com.drive.admin.controller;

import com.drive.admin.repository.AccountRepository;
import com.drive.common.core.biz.ResObject;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试分布式事务管理")
@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/{account}")
    ResObject increaseAmount(@PathVariable String account){
        return accountRepository.increaseAmount(account);
    }

}
