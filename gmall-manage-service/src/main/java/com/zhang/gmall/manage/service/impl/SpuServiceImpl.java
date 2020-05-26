package com.zhang.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhang.gmall.entity.PmsProductInfo;
import com.zhang.gmall.manage.mapper.PmsProductInfoMapper;
import com.zhang.gmall.service.SpuService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpuServiceImpl implements SpuService {
    private final PmsProductInfoMapper pmsProductInfoMapper;

    @Override
    public List<PmsProductInfo> spuList(Long catalog3Id) {
        LambdaQueryWrapper<PmsProductInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PmsProductInfo::getCatalog3Id, catalog3Id);
        return pmsProductInfoMapper.selectList(queryWrapper);
    }


}
