package com.laohu.repository;

import com.laohu.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by @Author tachai on 2018/1/10.
 *
 * @Email 1206966083@qq.com
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
        List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
