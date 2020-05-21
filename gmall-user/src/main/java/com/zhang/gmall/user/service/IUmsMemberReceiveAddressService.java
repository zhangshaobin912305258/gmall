package com.zhang.gmall.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhang.gmall.entity.UmsMemberReceiveAddress;

import java.util.List;

/**
 * <p>
 * 会员收货地址表 服务类
 * </p>
 *
 * @author zhang
 * @since 2020-05-20
 */
public interface IUmsMemberReceiveAddressService extends IService<UmsMemberReceiveAddress> {

    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(Long memberId);
}
