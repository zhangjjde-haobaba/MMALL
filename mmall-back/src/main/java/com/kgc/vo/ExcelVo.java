package com.kgc.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * Created by jiang on 3/8/22 10:51 PM
 */
@Data
public class ExcelVo {
    /**
     * 编号
     */
    @ExcelProperty("编号")
    private Integer id;

    /**
     * 名称
     */
    @ExcelProperty("名称")
    private String name;

    /**
     * 描述
     */
    @ExcelProperty("描述")
    private String description;

    /**
     * 价格
     */
    @ExcelProperty("价格")
    private Float price;

    /**
     * 库存
     */
    @ExcelProperty("库存")
    private Integer stock;

    /**
     * 分类1
     */
    @ExcelProperty("一级编号")
    private Integer categoryleveloneId;

    /**
     * 分类2
     */
    @ExcelProperty("二级编号")
    private Integer categoryleveltwoId;

    /**
     * 分类3
     */
    @ExcelProperty("三级编号")
    private Integer categorylevelthreeId;

    /**
     * 文件名称
     */
    @ExcelProperty("文件名称")
    private String fileName;
}
