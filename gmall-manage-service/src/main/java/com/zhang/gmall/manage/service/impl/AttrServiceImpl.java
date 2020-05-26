package com.zhang.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhang.gmall.entity.PmsBaseAttrInfo;
import com.zhang.gmall.entity.PmsBaseAttrValue;
import com.zhang.gmall.entity.PmsBaseSaleAttr;
import com.zhang.gmall.manage.mapper.PmsBaseAttrInfoMapper;
import com.zhang.gmall.manage.mapper.PmsBaseAttrValueMapper;
import com.zhang.gmall.manage.mapper.PmsBaseSaleAttrMapper;
import com.zhang.gmall.service.AttrService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttrServiceImpl implements AttrService {
    private final PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;
    private final PmsBaseAttrValueMapper pmsBaseAttrValueMapper;
    private final PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;

    @Override
    public List<PmsBaseAttrInfo> attrInfoList(Long catalog3Id) {
        return pmsBaseAttrInfoMapper.selectList(new LambdaQueryWrapper<PmsBaseAttrInfo>()
                .eq(PmsBaseAttrInfo::getCatalog3Id, catalog3Id)
        );
    }

    @Override
    public String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {
        String attrName = pmsBaseAttrInfo.getAttrName();
        List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
        if (attrValueList == null || attrValueList.isEmpty()) {
            return "error";
        }
        PmsBaseAttrInfo pmsBaseAttrInfoDb = pmsBaseAttrInfoMapper.selectOne(new LambdaQueryWrapper<PmsBaseAttrInfo>().eq(PmsBaseAttrInfo::getAttrName, attrName));
        if (pmsBaseAttrInfoDb != null) {
            return "error";
        }
        int attrInfoId = pmsBaseAttrInfoMapper.insert(pmsBaseAttrInfo);
        attrValueList.forEach(attrValue->{
            attrValue.setAttrId(Long.valueOf(attrInfoId));
            pmsBaseAttrValueMapper.insert(attrValue);
        });
        return "success";
    }

    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        LambdaQueryWrapper<PmsBaseSaleAttr> queryWrapper = new LambdaQueryWrapper<>();
        return pmsBaseSaleAttrMapper.selectList(queryWrapper);
    }
}
