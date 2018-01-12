package com.laohu.repository;

import com.laohu.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by @Author tachai on 2018/1/10.
 *
 * @Email 1206966083@qq.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findoneTest() {
        ProductCategory productCategory = repository.findOne(1);
        System.out.println(productCategory.toString());
    }

    @Test
    //不会把测试数据加进数据库
    @Transactional
    public void saveTest() {
        ProductCategory productCategory = repository.findOne(1);
        productCategory.setCategoryId(2);
        productCategory.setCategoryName("男生最爱");
        productCategory.setCategoryType(3);
        repository.save(productCategory);
    }

    @Test
    public void updateTest() {
        ProductCategory productCategory = repository.findOne(2);

        List<Integer> list= Arrays.asList(2,3,4);
        productCategory.setCategoryType(10);
        java.util.Date now=new java.util.Date();
        Date day = Date.valueOf(now.toString());
        productCategory.setUpdateTime(day);
        repository.save(productCategory);
    }
}