package com.laohu.dataobject.dao;

import com.laohu.dataobject.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by @Author tachai on 2018/1/26.
 *
 * @Email 1206966083@qq.com
 */
public class ProductCategoryDao {
    @Autowired
    ProductCategoryMapper productCategoryMapper;

    public int insertByMAP(Map<String ,Object> map){
        return  productCategoryMapper.insertByMap(map);
    }
}
