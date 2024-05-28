package com.kgc.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * Created by jiang on 3/12/22 9:31 PM
 */
@Data
public class CategoryExcelVo {
    @ExcelProperty("编号")
    private Integer id;

    /**
     * 名称
     */
    @ExcelProperty("名称")
    private String name;

    /**
     * 父级目录id
     */
    @ExcelProperty("父级目录")
    private Integer parentId;

    /**
     * 级别(1:一级 2：二级 3：三级)
     */
    @ExcelProperty("类型")
    private Integer type;
}
