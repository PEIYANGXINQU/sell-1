package com.laohu.service;

import com.laohu.exception.SellException;
import com.laohu.util.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by @Author tachai on 2018/1/26.
 *
 * @Email 1206966083@qq.com
 */
@Service
public class SecKillService {
    @Autowired
    private ReadisLock readisLock;
    private static final int TIMEOUT = 10 * 1000;//超时时间 10s
    /**
     * 国庆活动，皮蛋粥特价，限量100000份
     */
    static Map<String,Integer> products;
    static Map<String,Integer> stock;
    static Map<String,String> orders;
    {
        /**
         *模拟多个表，商品信息表，库存表，秒杀成功订单表
         */
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        products.put("123456",100000);
        stock.put("123456",100000);
    }
    private String queryMap(String productId){
        return "国庆活动，皮蛋粥特价，限量份"
                +products.get(productId)
                +"还剩："+stock.get(productId)
                +"该商品成功下单用户数目："
                +orders.size()+"人";
    }

    public String querySecKillProductInfo(String productId){
        return this.queryMap(productId);
    }
    //加同步锁但是会变慢 synchronized
    public  void orderProductMockDiffUser(String productId){
        //加锁
        long time = System.currentTimeMillis()+TIMEOUT;
        if(!readisLock.lock(productId,String.valueOf(time))){
            throw new SellException(101,"人太多了，换个姿势试下");
        }
        //1.查询该商品库存，为0则活动结束。
        int stockNum = stock.get(productId);
        if(stockNum==0){
            throw  new SellException(100,"活动结束");
        }else{
            //2.下单（模拟不同用户openid不同）
            orders.put(KeyUtil.genUniqueKey(),productId);
            stockNum = stockNum-1;
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            stock.put(productId,stockNum);
        }
        //解锁
        readisLock.unlock(productId,String.valueOf(time));
    }
}
