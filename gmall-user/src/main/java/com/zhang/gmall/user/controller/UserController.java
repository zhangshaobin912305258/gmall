package com.zhang.gmall.user.controller;

import com.zhang.gmall.user.entity.UmsMember;
import com.zhang.gmall.user.entity.UmsMemberReceiveAddress;
import com.zhang.gmall.user.service.IUmsMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final IUmsMemberService umsMemberService;

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
