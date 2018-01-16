package com.laohu.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.laohu.dataobject.OrderDetil;
import com.laohu.dto.OrderDTO;
import com.laohu.enums.ResultEnum;
import com.laohu.exception.SellException;
import com.laohu.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @Author tachai on 2018/1/16.
 *
 * @Email 1206966083@qq.com
 */
@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO convert(OrderForm orderForm){
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetil> orderDetilList=new ArrayList<>();
        //json转换成list;
        Gson gson=new Gson();
        try {
            gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetil>>(){}.getType());
        }catch (Exception e){
            log.error("【对象转换】错误，string={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }


        orderDTO.setOrderDetailList(orderDetilList);
        return orderDTO;
    }
}
