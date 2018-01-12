package com.laohu.dto;

import lombok.Data;

/**
 * Created by @Author tachai on 2018/1/12.
 *
 * @Email 1206966083@qq.com
 */
@Data
public class CartDTO {
    private String productId;

    private Integer productQuantity;
    public CartDTO(String productId,Integer productQuantity){
        this.productId=productId;
        this.productQuantity=productQuantity;
    }
}
