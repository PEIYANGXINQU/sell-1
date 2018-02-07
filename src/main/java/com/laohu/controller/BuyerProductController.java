package com.laohu.controller;

import com.laohu.dataobject.ProductCategory;
import com.laohu.dataobject.ProductInfo;
import com.laohu.service.CategoryService;
import com.laohu.service.ProductService;
import com.laohu.util.ResultVOUtil;
import com.laohu.vo.ProductInfoVO;
import com.laohu.vo.ProductVO;
import com.laohu.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by @Author tachai on 2018/1/11.
 *
 * @Email 1206966083@qq.com
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    //如果长度大于三则缓存  如果结果正确（json返回status==0）则缓存
    //@Cacheable(cacheNames = "product",key = "#sellerId",condition="#sellerId.length()>3",unless="#result.getCode() !=0")
    @Cacheable(cacheNames = "product",key = "123")
    public ResultVO list(@RequestParam("sellerId") String sellerId){
        //1.查询所有的上架商品
        List<ProductInfo> productInfoList=productService.findUpAll();
        //2.查询类目(一次查询)
 //       List<Integer> categoryTypeList=new ArrayList<>();
//        //传统方法
//        for(ProductInfo productInfo:productInfoVOList){
//            categoryTypeList.add(productInfo.getCategoryType());
//        }
        //精简方法(java8,lambda)
        List<Integer> categoryTypeList=productInfoList.stream()
                .map(e->e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList=categoryService.findByCategoryTypeIn(categoryTypeList);
        //3.数据拼装
        List<ProductVO> productVOList =new ArrayList<>();
        for(ProductCategory productCategory:productCategoryList){
            ProductVO productVO =new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());
            List<ProductInfoVO> productInfoVOList =new ArrayList<>();
            for(ProductInfo productInfo:productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO =new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

//        ResultVO resultVO =new ResultVO();
//        resultVO.setCode(0);
//        resultVO.setMsg("成功");
//        resultVO.setData(productVOList);
////        ProductVO productVo=new ProductVO();
////        ProductInfoVO productInfoVo=new ProductInfoVO();
////        resultVO.setData(productVo);

        return ResultVOUtil.success(productVOList);
    }
}
