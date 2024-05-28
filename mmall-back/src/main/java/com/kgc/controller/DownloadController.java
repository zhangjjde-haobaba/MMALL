package com.kgc.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.kgc.service.ProductCategoryService;
import com.kgc.service.ProductService;
import com.kgc.vo.CategoryExcelVo;
import com.kgc.vo.ExcelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * Created by jiang on 3/12/22 9:25 PM
 */
@RestController
@RequestMapping("/download")
public class DownloadController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/exportdata")
    public String exportExcel(HttpServletResponse response) throws Exception{
        String fileName  = "MMALL商品数据";
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
        //这里 需要指定写用哪个class去写
        WriteSheet writeSheet = EasyExcel.writerSheet(0, "product信息").head(ExcelVo.class).build();
        excelWriter.write(this.productService.list(), writeSheet);
        writeSheet = EasyExcel.writerSheet(1, "productCate信息").head(CategoryExcelVo.class).build();
        excelWriter.write(this.productCategoryService.list(), writeSheet);
        //千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
        return "success";
    }

}
