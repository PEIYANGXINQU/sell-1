package com.laohu.aspect;

import com.laohu.constant.CookieConstant;
import com.laohu.constant.RedisConstant;
import com.laohu.enums.ResultEnum;
import com.laohu.exception.SellException;
import com.laohu.exception.SellerAuthorizeException;
import com.laohu.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by @Author tachai on 2018/1/24.
 *
 * @Email 1206966083@qq.com
 */
@Aspect
@Component
@Slf4j
public class SellAuthorizeAspect {
    @Autowired
    private RedisTemplate redisTemplate;


    @Pointcut("execution(public * com.laohu.controller..Seller*.*(..))"+"&&!execution(public * com.laohu.controller.SellerUserController.*(..))")
    public void verify(){

    }

    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        //查寻cookie
        Cookie cookie= CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie==null){
            log.warn("【登录校验】cookie中查不到token");
            throw new SellerAuthorizeException();
        }
        //去redis里查询
        String tokenValue= (String) redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
        if(StringUtils.isEmpty(tokenValue)){
            log.warn("【登录校验】Redis中查不到token");
            throw new SellerAuthorizeException();
        }
    }

}
