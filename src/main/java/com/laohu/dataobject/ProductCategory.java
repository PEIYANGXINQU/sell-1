package com.laohu.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by @Author tachai on 2018/1/10.
 *
 * @Email 1206966083@qq.com
 */
//动态跟新时间

@Entity
@DynamicUpdate
@Data
public class ProductCategory {
    /** 类目id*/
    @Id
    @GeneratedValue
    private Integer categoryId;
    /**
     * 类目名字
     */
    private String categoryName;
    /**
     * 类目编号
     */
    private Integer categoryType;

    private Date updateTime;

    private Date createTime;

    public ProductCategory(){

    }
    public ProductCategory(String categoryName,Integer categoryType){
        this.categoryName=categoryName;
        this.categoryType=categoryType;
    }
}
