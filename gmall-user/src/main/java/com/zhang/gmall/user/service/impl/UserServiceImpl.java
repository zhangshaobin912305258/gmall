package com.zhang.gmall.user.service.impl;

import com.zhang.gmall.user.mapper.UserMapper;
import com.zhang.gmall.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
}
