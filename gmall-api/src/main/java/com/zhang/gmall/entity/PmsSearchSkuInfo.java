package com.zhang.gmall.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PmsSearchSkuInfo implements Serializable {
    private String id;
    private String skuName;
    private String skuDesc;
    private String catalog3Id;
    private double price;
    private String skuDefaultImg;
    private double hostScore;
    private String productId;
    private List<PmsSkuAttrValue> skuAttrValueList;

}
