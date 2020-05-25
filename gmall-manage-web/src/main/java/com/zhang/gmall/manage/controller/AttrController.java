package com.zhang.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhang.gmall.entity.PmsBaseAttrInfo;
import com.zhang.gmall.service.AttrService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class AttrController {

    @Reference
    private AttrService attrService;

    @RequestMapping("/attrInfoList")
    public List<PmsBaseAttrInfo> attrInfoList(Long catalog3Id) {
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = attrService.attrInfoList(catalog3Id);
        return pmsBaseAttrInfos;
    }

    @RequestMapping("/saveAttrInfo")
    public String saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo) {
        return attrService.saveAttrInfo(pmsBaseAttrInfo);
    }
}
