package com.laohu.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by @Author tachai on 2018/1/10.
 *
 * @Email 1206966083@qq.com
 */
@Entity
@DynamicUpdate
@Data
public class ProductInfo implements Serializable{
    private static final long serialVersionUID = -2912636485220947364L;
    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer productStatus;
//    private Integer productStatus= ProductStatusEnum.UP.getCode();

    private Integer categoryType;

    private Date  createTime;

    private Date updateTime;

//    @JsonIgnore
//    public ProductStatusEnum getProductStatusEnum() {
//        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
//    }
}
