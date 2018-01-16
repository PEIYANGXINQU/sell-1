package com.laohu.service;

import com.laohu.dto.OrderDTO;

/**
 * Created by @Author tachai on 2018/1/16.
 *
 * @Email 1206966083@qq.com
 */
public interface BuyerService {
    //查询一个订单
    OrderDTO findOrderOne(String openid,String orderId);
    //取消订单
    OrderDTO cancelOrder(String openid,String orderId);

}
