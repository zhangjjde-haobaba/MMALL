package com.kgc.service;

import com.kgc.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.vo.ExcelVo;
import com.kgc.vo.PageVo;
import com.kgc.vo.ProductVo;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-02-26
 */
public interface ProductService extends IService<Product> {

    public PageVo voList(Integer page, Integer size);

    public ProductVo findVoByid(Integer id);

    public List<ExcelVo> list(InputStream inputStream);

}
