package com.laohu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by @Author tachai on 2018/1/23.
 *
 * @Email 1206966083@qq.com
 */
@Data
@Component
@ConfigurationProperties(prefix="projectUrl")
public class ProjectUrlConfig {
    /**
     * 微信公众平台授权url
     */
    public String wechatMpAuthorize;
    /**
     * 微信开发平台授权url
     */
    public String wechatOpenAuthorize;
    /**
     * 点餐系统
     */
    public  String sell;

}
