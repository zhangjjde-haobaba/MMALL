package com.kgc.mapper;

import com.kgc.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.vo.PieVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-02-26
 */
public interface ProductMapper extends BaseMapper<Product> {
    public List<PieVo> bar();
}
