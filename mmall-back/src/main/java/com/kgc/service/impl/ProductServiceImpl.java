package com.kgc.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kgc.entity.Product;
import com.kgc.entity.ProductCategory;
import com.kgc.mapper.ProductCategoryMapper;
import com.kgc.mapper.ProductMapper;
import com.kgc.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgc.vo.ExcelVo;
import com.kgc.vo.PageVo;
import com.kgc.vo.ProductVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-02-26
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired(required = false)
    private ProductMapper productMapper;
    @Autowired(required = false)
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public PageVo voList(Integer page, Integer size) {
        Page<Product> productPage = new Page<>(page,size);
        Page<Product> resultPage = this.productMapper.selectPage(productPage,null);
        List<Product> productList= resultPage.getRecords();
//        List<Product> productList=this.productMapper.selectList(null);
        ProductVo productVO = null;
        List<ProductVo> result = new ArrayList<>();
        for(Product product:productList){
            productVO = new ProductVo();
            BeanUtils.copyProperties(product, productVO);

            QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", product.getCategoryleveloneId());
            ProductCategory productCategory = this.productCategoryMapper.selectOne(queryWrapper);
            productVO.setCategoryOneName(productCategory.getName());

            queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", product.getCategoryleveltwoId());
            productCategory = this.productCategoryMapper.selectOne(queryWrapper);
            productVO.setCategoryTwoName(productCategory.getName());

            queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", product.getCategorylevelthreeId());
            productCategory = this.productCategoryMapper.selectOne(queryWrapper);
            productVO.setCategoryThreeName(productCategory.getName());

            result.add(productVO);
        }
        PageVo pageVo = new PageVo();
        pageVo.setData(result);
        pageVo.setTotal(resultPage.getTotal());
        return pageVo;
    }

    @Override
    public ProductVo findVoByid(Integer id) {
        Product product = this.productMapper.selectById(id);
        ProductVo productVo = new ProductVo();
        BeanUtils.copyProperties(product, productVo);
        //一级分类
        QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type",1);
        productVo.setLevelOne(this.productCategoryMapper.selectList(queryWrapper));
        //二级分类
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type",2);
        queryWrapper.eq("parent_id",product.getCategoryleveloneId());
        productVo.setLevelTwo(this.productCategoryMapper.selectList(queryWrapper));
        //三级分类
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type",3);
        queryWrapper.eq("parent_id",product.getCategoryleveltwoId());
        productVo.setLevelThree(this.productCategoryMapper.selectList(queryWrapper));
        return productVo;
    }

    @Override
    public List<ExcelVo> list(InputStream inputStream) {
        List<ExcelVo> list = new ArrayList<>();
        EasyExcel.read(inputStream)
                .head(ExcelVo.class)
                .sheet()
                .registerReadListener(new AnalysisEventListener<ExcelVo>() {

                    @Override
                    public void invoke(ExcelVo excelVo, AnalysisContext analysisContext) {
                        list.add(excelVo);
                    }

                    @Override
                    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                        System.out.println("数据解析完成...");
                    }
                }).doRead();
        return list;
    }
}
