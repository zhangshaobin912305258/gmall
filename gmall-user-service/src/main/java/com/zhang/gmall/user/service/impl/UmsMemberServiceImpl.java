package com.zhang.gmall.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.gmall.entity.UmsMember;
import com.zhang.gmall.entity.UmsMemberReceiveAddress;
import com.zhang.gmall.service.IUmsMemberReceiveAddressService;
import com.zhang.gmall.service.IUmsMemberService;
import com.zhang.gmall.user.mapper.UmsMemberMapper;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
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

    public static void main(String[] args) {
        LocalDate preDate = LocalDate.of(2017, 1, 15);
        LocalDate nowDate = LocalDate.now();

        int years = preDate.until(nowDate).getYears();
        int months = preDate.until(nowDate).getMonths();
        int days = preDate.until(nowDate).getDays();
        System.out.println("间隔:"+years + " years," + months + " months and " + days + " days");

        int lastDayInt = (int) (LocalDate.of(2020,10,10).toEpochDay() - preDate.toEpochDay());
        System.out.println(lastDayInt);
        System.out.println(preDate.plusDays(1314));

    }
}
