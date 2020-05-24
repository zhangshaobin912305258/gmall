package com.zhang.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhang.gmall.entity.PmsBaseCatalog1;
import com.zhang.gmall.entity.PmsBaseCatalog2;
import com.zhang.gmall.entity.PmsBaseCatalog3;
import com.zhang.gmall.manage.mapper.PmsBaseCatalog1Mapper;
import com.zhang.gmall.manage.mapper.PmsBaseCatalog2Mapper;
import com.zhang.gmall.manage.mapper.PmsBaseCatalog3Mapper;
import com.zhang.gmall.service.CatalogService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final PmsBaseCatalog1Mapper pmsBaseCatalog1Mapper;
    private final PmsBaseCatalog2Mapper pmsBaseCatalog2Mapper;
    private final PmsBaseCatalog3Mapper pmsBaseCatalog3Mapper;

    @Override
    public List<PmsBaseCatalog1> getCatalog1() {
        return pmsBaseCatalog1Mapper.selectList(null);
    }

    @Override
    public List<PmsBaseCatalog2> getCatalog2(Long catalog1Id) {
        List<PmsBaseCatalog2> catalog2s = pmsBaseCatalog2Mapper.selectList(new LambdaQueryWrapper<PmsBaseCatalog2>().eq(PmsBaseCatalog2::getCatalog1Id, catalog1Id));
        return catalog2s;
    }

    @Override
    public List<PmsBaseCatalog3> getCatalog3(Long catalog2Id) {
        List<PmsBaseCatalog3> catalog3s = pmsBaseCatalog3Mapper.selectList(new LambdaQueryWrapper<PmsBaseCatalog3>().eq(PmsBaseCatalog3::getCatalog2Id, catalog2Id));
        return catalog3s;
    }
}
