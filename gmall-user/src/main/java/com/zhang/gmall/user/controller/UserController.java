package com.zhang.gmall.user.controller;

import com.zhang.gmall.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @RequestMapping("/index")
    public String index() {
        return "hello world";
    }
}
