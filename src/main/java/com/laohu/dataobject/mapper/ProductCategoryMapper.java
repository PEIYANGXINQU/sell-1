package com.laohu.dataobject.mapper;

import com.laohu.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by @Author tachai on 2018/1/26.
 *
 * @Email 1206966083@qq.com
 */
public interface ProductCategoryMapper {
    @Insert("insert into product_category(category_name,category_type) values(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByMap(Map<String,Object> map);
    @Insert("insert into product_category(category_name,category_type) values (#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER}")
    int insertByProductCategory(ProductCategory productCategory);
    @Select("select * from product_category where category_type=#{categoryType,jdbcType=INTEGER}")
    @Results({
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType"),
    })
    ProductCategory findByCategoryType(Integer categoryType);
    @Select("select * from product_category where category_name=#{categoryName,jdbcType=VARCHAR}")
    @Results({
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType"),
    })
    List<ProductCategory>  findByCategoryName(String categoryName);

    @Update("update product_category set category_name = #{categoryName,jdbcType=VARCHAR} where category_type=#{categoryType,jdbcType=INTEGER}")
    int updateByCategoryType(@Param("categoryName") String categoryName,@Param("categoryType") Integer categoryType);

    @Update("update product_category set category_name = #{categoryName,jdbcType=VARCHAR} where category_type=#{categoryType,jdbcType=INTEGER}")
    int updateByObject(ProductCategory productCategory);
    @Delete("delete product_category where category_type=#{categoryType,jdbcType=INTEGER}")
    int deleteByCategoryType(Integer categoryType);

    //通过xml文件来写
    ProductCategory selelctByCategoryType(Integer categoryType);

}
