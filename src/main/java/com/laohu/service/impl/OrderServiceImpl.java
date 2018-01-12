package com.laohu.service.impl;

import com.laohu.converter.OrderMaster2OrderDTOConverter;
import com.laohu.dataobject.OrderDetil;
import com.laohu.dataobject.OrderMaster;
import com.laohu.dataobject.ProductInfo;
import com.laohu.dto.CartDTO;
import com.laohu.dto.OrderDTO;
import com.laohu.enums.OrderStatusEnum;
import com.laohu.enums.PayStatusEnum;
import com.laohu.enums.ResultEnum;
import com.laohu.exception.SellException;
import com.laohu.repository.OrderDetilRepository;
import com.laohu.repository.OrderMasterRepository;
import com.laohu.service.OrderService;
import com.laohu.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by @Author tachai on 2018/1/12.
 *
 * @Email 1206966083@qq.com
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetilRepository orderDetilRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId= com.laohu.util.KeyUtil.genUniqueKey();
        BigDecimal orderAmount=new BigDecimal(BigInteger.ZERO);
//        List<CartDTO> cartDTOList=new ArrayList<>();

        //1.查询商品（数量，价格）
        for(OrderDetil orderDetil:orderDTO.getOrderDetilList()){
            ProductInfo productInfo=productService.findOne(orderDetil.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算订单总价
            orderDetil.getProductPrice()
                    .multiply(new BigDecimal(orderDetil.getProductQuantity()))
                    .add(orderAmount);
            //订单详情入库
            orderDetil.setDetailId(com.laohu.util.KeyUtil.genUniqueKey());
            orderDetil.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetil);
            orderDetilRepository.save(orderDetil);

//            CartDTO cartDTO=new CartDTO(orderDetil.getProductId(),orderDetil.getProductQuantity());
//            cartDTOList.add(cartDTO);
        }

        //3.写入订单数据库（orderMaster和orderDetail）
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        //4.扣库存
        List<CartDTO> cartDTOList=orderDTO.getOrderDetilList().stream().map(e ->new CartDTO(e.getProductId(),e.getProductQuantity())
        ).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);

        return orderDTO ;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster=orderMasterRepository.findOne(orderId);
        if(orderMaster==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetil> orderDetilList=orderDetilRepository.findByOAndOrderId(orderId);
        if(orderDetilList==null){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO=new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetilList(orderDetilList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage=orderMasterRepository.findByBuerBuyerOpenid(buyerOpenid,pageable);
        List<OrderDTO> orderDTOList=OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
        return new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements()) ;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
