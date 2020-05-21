package com.zhang.gmall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhang.gmall.entity.UmsMemberReceiveAddress;
import com.zhang.gmall.user.mapper.UmsMemberReceiveAddressMapper;
import com.zhang.gmall.user.service.IUmsMemberReceiveAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 会员收货地址表 服务实现类
 * </p>
 *
 * @author zhang
 * @since 2020-05-20
 */
@Service
public class UmsMemberReceiveAddressServiceImpl extends ServiceImpl<UmsMemberReceiveAddressMapper, UmsMemberReceiveAddress> implements IUmsMemberReceiveAddressService {

    @Override
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(Long memberId) {
        LambdaQueryWrapper<UmsMemberReceiveAddress> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsMemberReceiveAddress::getMemberId, memberId);
        return list(queryWrapper);
    }
}
