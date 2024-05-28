package com.kgc.vo;

import com.kgc.entity.ProductCategory;
import lombok.Data;

import java.util.List;

/**
 * Created by jiang on 2/26/22 9:29 PM
 */
@Data
public class ProductVo {

    private Integer id;
    private String name;
    private String description;
    private Float price;
    private Integer stock;
    private String categoryOneName;
    private String categoryTwoName;
    private String categoryThreeName;
    private Integer categoryleveloneId;
    private Integer categoryleveltwoId;
    private Integer categorylevelthreeId;
    private List<ProductCategory> levelOne;
    private List<ProductCategory> levelTwo;
    private List<ProductCategory> levelThree;
}
