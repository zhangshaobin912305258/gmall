package com.zhang.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhang.gmall.entity.PmsBaseSaleAttr;
import com.zhang.gmall.entity.PmsProductInfo;
import com.zhang.gmall.service.SpuService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class SpuController {

    @Reference
    SpuService spuService;

    @RequestMapping("spuList")
    public List<PmsProductInfo> spuList(Long catalog3Id) {
        List<PmsProductInfo> pmsProductInfos = spuService.spuList(catalog3Id);
        return pmsProductInfos;
    }


}
