package com.zhang.gmall.user.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.zhang.gmall.entity.UmsMember;
import com.zhang.gmall.entity.UmsMemberReceiveAddress;
import com.zhang.gmall.service.IUmsMemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Reference
    private IUmsMemberService umsMemberService;

    @RequestMapping("/index")
    public String index() {
        return "hello world";
    }

    @GetMapping("/getAllUser")
    public List<UmsMember> getAllUser() {
        return umsMemberService.getAllUser();
    }

    @RequestMapping("/getReceiveAddressByMemberId")
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(Long memberId) {
        return umsMemberService.getReceiveAddressByMemberId(memberId);
    }
}
