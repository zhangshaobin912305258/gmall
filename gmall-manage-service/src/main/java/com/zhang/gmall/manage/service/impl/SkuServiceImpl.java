package com.zhang.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhang.gmall.entity.PmsSearchSkuInfo;
import com.zhang.gmall.entity.PmsSkuAttrValue;
import com.zhang.gmall.entity.PmsSkuInfo;
import com.zhang.gmall.manage.mapper.PmsSkuAttrValueMapper;
import com.zhang.gmall.manage.mapper.PmsSkuInfoMapper;
import com.zhang.gmall.service.SkuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkuServiceImpl implements SkuService {
    private final PmsSkuInfoMapper pmsSkuInfoMapper;
    private final PmsSkuAttrValueMapper pmsSkuAttrValueMapper;

    @Override
    public List<PmsSkuInfo> getAllSku() {
        List<PmsSkuInfo> pmsSkuInfos = pmsSkuInfoMapper.selectList(null);
        for (PmsSkuInfo pmsSkuInfo : pmsSkuInfos) {
            Long skuId = pmsSkuInfo.getId();
            LambdaQueryWrapper<PmsSkuAttrValue> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(PmsSkuAttrValue::getSkuId, skuId);
            List<PmsSkuAttrValue> pmsSkuAttrValues = pmsSkuAttrValueMapper.selectList(queryWrapper);
            pmsSkuInfo.setAttrValues(pmsSkuAttrValues);
        }

        return pmsSkuInfos;
    }
}
