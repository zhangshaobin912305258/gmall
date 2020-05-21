package com.zhang.gmall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 库存工作单表 库存工作单表
 * </p>
 *
 * @author zhang
 * @since 2020-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WmsWareOrderTask implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单编号
     */
    private Long orderId;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 收货人电话
     */
    private String consigneeTel;

    /**
     * 送货地址
     */
    private String deliveryAddress;

    /**
     * 订单备注
     */
    private String orderComment;

    /**
     * 付款方式 1:在线付款 2:货到付款
     */
    private String paymentWay;

    private String taskStatus;

    /**
     * 订单描述
     */
    private String orderBody;

    /**
     * 物流单号
     */
    private String trackingNo;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 仓库编号
     */
    private Long wareId;

    /**
     * 工作单备注
     */
    private String taskComment;


}
