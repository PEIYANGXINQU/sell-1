package com.laohu.service.impl;

import com.laohu.dto.OrderDTO;
import com.laohu.enums.ResultEnum;
import com.laohu.exception.SellException;
import com.laohu.service.PayService;
import com.laohu.util.JsonUtil;
import com.laohu.util.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by @Author tachai on 2018/1/19.
 *
 * @Email 1206966083@qq.com
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {
    private static final String ORDER_NAME="微信点餐订单";

    @Autowired
    private BestPayServiceImpl bestPayService;
    @Autowired
    private OrderServiceImpl orderService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest=new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        log.info("【微信支付】发起支付 request={}",JsonUtil.toJson(payRequest));
        PayResponse payResponse= bestPayService.pay(payRequest);
        log.info("【微信支付】发起支付 request={}", JsonUtil.toJson(payResponse));
        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        //1.验证签名
        //2.支付的状态
        //3.支付金额
        //4.支付人（下单人==支付人）
        PayResponse payResponse=bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】异步通知，payResponse={}",JsonUtil.toJson(payResponse));

        //查询订单是否存在
        OrderDTO orderDTO=orderService.findOne(payResponse.getOrderId());
        if(orderDTO==null){
            log.error("【微信支付】异步通知，订单不存在，orderId={}",payResponse.getOrderId());
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //判断金额是否一致(0.10 0.1)
//        if(orderDTO.getOrderAmount().compareTo(new BigDecimal(payResponse.getOrderAmount()))!=0){
        if(!MathUtil.equals(orderDTO.getOrderAmount().doubleValue(),payResponse.getOrderAmount())){
            log.error("【微信支付】异步通知，金额不匹配，orderId={}",payResponse.getOrderId());
            throw new SellException(ResultEnum.WECHAT_NOTIFY_MONEY_VERIFY_ERROR);
        }
        //修改订单的支付状态
        orderService.paid(orderDTO);
        return payResponse;
    }

    /**
     * 退款
     * @param orderDTO
     */
    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        //微信
        RefundRequest refundRequest=new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
       log.info("【微信退款】request={}",JsonUtil.toJson(refundRequest));
       RefundResponse refundResponse=bestPayService.refund(refundRequest);
        log.info("【微信退款】reponse={}",JsonUtil.toJson(refundRequest));
        return refundResponse;
    }
}
