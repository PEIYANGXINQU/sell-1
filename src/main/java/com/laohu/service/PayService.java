package com.laohu.service;

import com.laohu.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/**
 * Created by @Author tachai on 2018/1/19.
 *
 * @Email 1206966083@qq.com
 */
public interface PayService {
    PayResponse create(OrderDTO orderDTO);
    PayResponse notify(String notifyData);
    RefundResponse refund(OrderDTO orderDTO);
}
