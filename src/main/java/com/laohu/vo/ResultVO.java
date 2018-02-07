package com.laohu.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by @Author tachai on 2018/1/11.
 *
 * @Email 1206966083@qq.com
 */

/**
 * http请求返回的最外层对象
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = -5187102223852215264L;
    /**错误码*/
    private Integer code;
    /**提示的信息*/
    private String msg;
    /**返回的数据*/
    private T data;
}
