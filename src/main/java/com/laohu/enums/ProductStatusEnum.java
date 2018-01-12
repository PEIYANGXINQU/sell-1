package com.laohu.enums;

import lombok.Getter;

/**
 * Created by @Author tachai on 2018/1/11.
 *
 * @Email 1206966083@qq.com
 */
@Getter
public enum ProductStatusEnum {
    UP(0,"在架"),
    DOWM(1,"下架");

    private Integer code;
    private String message;

    ProductStatusEnum (Integer code,String message){
        this.code=code;
        this.message=message;
    }

}
