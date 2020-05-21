package com.zhang.gmall.user.service.impl;

import com.zhang.gmall.entity.UmsMember;
import com.zhang.gmall.entity.UmsMemberReceiveAddress;
import com.zhang.gmall.user.mapper.UmsMemberMapper;
import com.zhang.gmall.user.service.IUmsMemberReceiveAddressService;
import com.zhang.gmall.user.service.IUmsMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author zhang
 * @since 2020-05-20
 */
@Service
@RequiredArgsConstructor
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements IUmsMemberService {

    private final IUmsMemberReceiveAddressService umsMemberReceiveAddressService;

    @Override
    public List<UmsMember> getAllUser() {
        return list();
    }

    @Override
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(Long memberId) {
        return umsMemberReceiveAddressService.getReceiveAddressByMemberId(memberId);
    }
}
