package com.zhang.gmall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.beans.Transient;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 库存单元表
 * </p>
 *
 * @author zhang
 * @since 2020-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PmsSkuInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 库存id(itemID)
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 价格
     */
    private Double price;

    /**
     * sku名称
     */
    private String skuName;

    /**
     * 商品规格描述
     */
    private String skuDesc;

    private Double weight;

    /**
     * 品牌(冗余)
     */
    private Long tmId;

    /**
     * 三级分类id（冗余)
     */
    private Long catalog3Id;

    /**
     * 默认显示图片(冗余)
     */
    private String skuDefaultImg;

    @TableField(exist = false)
    private List<PmsSkuAttrValue> attrValues;


}
