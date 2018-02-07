package com.laohu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by @Author tachai on 2018/1/18.
 *
 * @Email 1206966083@qq.com
 */
@Data
@Component
@ConfigurationProperties(prefix="wechat")
public class WechatAccountConfig {
    private String mpAppId;
    private String mpAppSecret;

    /**
     * 商户号
     */
    private String mchId;
    /**
     * 商户秘钥
     */
    private String mchKey;
    /**
     * 商户证书路径
     */
    private String keyPath;
    /**
     * 微信支付异步通知地址
     */
    private String notifyUrl;

    /**
     * 微信模板Id
     */
    private Map<String,String > templateId;
}
