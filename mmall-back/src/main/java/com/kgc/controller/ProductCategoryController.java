package com.kgc.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kgc.entity.ProductCategory;
import com.kgc.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-02-26
 */
@RestController
@RequestMapping("/productCategory")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 查询所有的一级分类
     */
    @GetMapping("/init")
    public List<ProductCategory> init(){
        QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type",1);
        return this.productCategoryService.list(queryWrapper);
    }

    /**
     * 根据一级分类的id查找对应的所有二级分类
     * 根据二级分类的id查找对应的所有三级分类
     */
    @GetMapping("children/{level}/{parentId}")
    public List<ProductCategory> children(@PathVariable("level") Integer level, @PathVariable("parentId") Integer parentId){
        QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type",level);
        queryWrapper.eq("parent_id",parentId);
        return this.productCategoryService.list(queryWrapper);
    }



}

