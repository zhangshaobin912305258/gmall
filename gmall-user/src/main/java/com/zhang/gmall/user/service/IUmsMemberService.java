package com.zhang.gmall.user.service;

import com.zhang.gmall.user.entity.UmsMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhang.gmall.user.entity.UmsMemberReceiveAddress;

import java.util.List;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author zhang
 * @since 2020-05-20
 */
public interface IUmsMemberService extends IService<UmsMember> {

    List<UmsMember> getAllUser();

    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(Long memberId);
}
