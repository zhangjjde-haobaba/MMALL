package com.kgc.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by jiang on 2/27/22 12:46 AM
 */
@Data
public class PageVo {
    private List<ProductVo> data;
    private Long total;
}
