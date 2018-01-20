package com.laohu.controller;

import com.laohu.dto.OrderDTO;
import com.laohu.enums.ResultEnum;
import com.laohu.exception.SellException;
import com.laohu.service.OrderService;
import com.laohu.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by @Author tachai on 2018/1/19.
 *
 * @Email 1206966083@qq.com
 */
@Controller
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId")String orderId, @RequestParam("returnUrl")String returnUrl, Map<String,Object>map){
        //1.查询订单
        OrderDTO orderDTO=orderService.findOne(orderId);
        if(orderDTO==null){
            throw  new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        //2.发起支付

        PayResponse payResponse=payService.create(orderDTO);
        map.put("returnUrl",returnUrl);
        map.put("payResponse",payResponse);
        return new ModelAndView("pay/create",map);
    }

    /**
     * 微信异步通知
     * @param notifyData
     * @return
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){
        payService.notify(notifyData);
        //返回给微信处理结果
        return  new ModelAndView("/pay/success");
    }
}
