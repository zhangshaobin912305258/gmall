package com.zhang.gmall.service;

import com.zhang.gmall.entity.PmsBaseAttrInfo;

import java.util.List;

public interface AttrService {
    List<PmsBaseAttrInfo> attrInfoList(Long catalog3Id);

    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);
}
