package com.kgc.controller;


import com.kgc.entity.Product;
import com.kgc.mapper.ProductMapper;
import com.kgc.service.ProductService;
import com.kgc.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired(required = false)
    private ProductMapper productMapper;

    /**
     * page:当前多少页
     * size:每页多少条
     * @return
     */
    @GetMapping("/list/{page}/{size}")
    public PageVo list(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        return this.productService.voList(page,size);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Product product){
        return this.productService.save(product);
    }

    @GetMapping("/findById/{id}")
    public ProductVo findById(@PathVariable("id") Integer id){
        return this.productService.findVoByid(id);
//        return this.productService.getById(id);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody Product product){
        return this.productService.updateById(product);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") Integer id){
        return this.productService.removeById(id);
    }

    @GetMapping("/bar")
    public BarVo bar(){
        List<PieVo> list = this.productMapper.bar();
        BarVo barVo = new BarVo();
        List<String> names = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        for(PieVo pie:list){
            names.add(pie.getName());
            values.add(pie.getValue());
        }
        barVo.setName(names);
        barVo.setValues(values);

        return barVo;
    }

    @GetMapping("/pie")
    public List<PieVo> pie(){
        return this.productMapper.bar();
    }
    @PostMapping("/import")
    public String importData(@RequestParam("file")MultipartFile file){
        try {
            List<ExcelVo> list = this.productService.list(file.getInputStream());
            Product product = new Product();
            for(ExcelVo excelVo:list){
                BeanUtils.copyProperties(excelVo,product);
                this.productService.save(product);
            }

        } catch (IOException e) {
            return "false";
        }
        return "success";
    }

}

