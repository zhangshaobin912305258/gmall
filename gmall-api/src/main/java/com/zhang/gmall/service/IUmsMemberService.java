package com.zhang.gmall.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zhang.gmall.entity.UmsMember;
import com.zhang.gmall.entity.UmsMemberReceiveAddress;

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
