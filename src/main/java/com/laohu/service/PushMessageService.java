package com.laohu.service;

import com.laohu.dto.OrderDTO;

/**
 * Created by @Author tachai on 2018/1/25.
 *
 * @Email 1206966083@qq.com
 */
public interface PushMessage {
    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
