package com.laohu.enums;

import lombok.Getter;

/**
 * Created by @Author tachai on 2018/1/12.
 *
 * @Email 1206966083@qq.com
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"库存不正确"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_DETAIL_EMPTY(14,""),
    ORDER_UPDATE_FAIL(15,""),
    ;
    private Integer code;
    private String message;
    ResultEnum(Integer code,String message){

    }
}
