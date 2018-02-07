package com.laohu.dataobject;

import com.laohu.enums.OrderStatusEnum;
import com.laohu.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by @Author tachai on 2018/1/12.
 *
 * @Email 1206966083@qq.com
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    /**订单id*/
    @Id
    private String orderId;
    /**买家名字*/
    private String buyerName;
    /**买家手机号*/
    private String buyerPhone;
    /**买家地址*/
    private String buyerAddress;
    /**买家微信Openid*/
    private String buyerOpenid;
    /**订单总金额*/
    private BigDecimal orderAmount;
    /**订单状态，默认为新下单*/
    private Integer orderStatus= OrderStatusEnum.NEW.getCode();
    /**支付状态，默认是0未支付*/
    private Integer payStatus= PayStatusEnum.WAIT.getCode();
    /**创建时间*/
    private Date createTime;
    /**跟新时间*/
    private Date updateTime;
    //数据库里面没有service但是需要
    @Transient
    private List<OrderDetail> orderDetailList;

}
