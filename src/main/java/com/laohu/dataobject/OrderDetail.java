package com.laohu.dataobject;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Created by @Author tachai on 2018/1/12.
 *
 * @Email 1206966083@qq.com
 */
@Entity
@Data
public class OrderDetil {
    @Id
    private String detailId;
    /**订单id*/
    private String orderId;
    /**商品id*/
    private String productId;
    /**商品名称*/
    private String productName;
    /**商品单价*/
    private BigDecimal productPrice;
    /**商品数量*/
    private Integer productQuantity;
    /**商品小图.*/
    private String productIcon;
}
