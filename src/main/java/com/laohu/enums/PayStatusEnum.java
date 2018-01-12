package com.laohu.enums;

import lombok.Getter;

/**
 * Created by @Author tachai on 2018/1/12.
 *
 * @Email 1206966083@qq.com
 */
@Getter
public enum  PayStatusEnum {
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功");



    private Integer code;
    private String message;
    PayStatusEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }
}
