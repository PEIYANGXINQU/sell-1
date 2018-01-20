package com.laohu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by @Author tachai on 2018/1/17.
 *
 * @Email 1206966083@qq.com
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {
    @GetMapping("/auth")
    public void auth(@RequestParam("code")String code){
        log.info("进入auth方法。。。");
        log.info("code={}",code);
        String url="https://api.weixin.qq.com/*****";
        RestTemplate restTemplate=new RestTemplate();
        String response=restTemplate.getForObject(url,String.class);
        log.info("response={}",response);
    }
}
