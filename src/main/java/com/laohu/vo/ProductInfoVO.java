package com.laohu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by @Author tachai on 2018/1/11.
 *
 * @Email 1206966083@qq.com
 */
public class ProductInfoVO {
    @JsonProperty("id")
    private String productId;
    @JsonProperty("price")
    private String productName;
    @JsonProperty("description")
    private String productDescription;
    @JsonProperty("icon")
    private String productIcon;

}
