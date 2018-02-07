package com.laohu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by @Author tachai on 2018/1/11.
 *
 * @Email 1206966083@qq.com
 */
public class ProductInfoVO implements Serializable {

    private static final long serialVersionUID = -7066960945360928054L;
    @JsonProperty("id")
    private String productId;
    @JsonProperty("price")
    private String productName;
    @JsonProperty("description")
    private String productDescription;
    @JsonProperty("icon")
    private String productIcon;

}
