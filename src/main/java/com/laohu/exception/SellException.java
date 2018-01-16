package com.laohu.exception;

import com.laohu.enums.ResultEnum;
import lombok.Getter;

/**
 * Created by @Author tachai on 2018/1/12.
 *
 * @Email 1206966083@qq.com
 */
@Getter
public class SellException extends RuntimeException {
    private Integer code;
    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }
    public SellException(Integer code,String message){
        super(message);
        this.code=code;
    }
}
