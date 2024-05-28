package com.kgc.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.kgc.entity.Product;
import com.kgc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by jiang on 5/7/22 3:06 PM
 */
@RestController
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    private ProductService productService;

    @GetMapping("/download")
    public void downloadpdf(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/pdf");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=product.pdf");
        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        List<Product> list = productService.list();
        for(Product p:list){
            PdfPTable table = new PdfPTable(9);
//            id
            PdfPCell cell = new PdfPCell();
            cell.setPhrase(new Paragraph(p.getId().toString()));
            table.addCell(cell);
            document.add(table);
            //name
            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(p.getName().toString()));
            table.addCell(cell);
            document.add(table);
//            description
            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(p.getDescription().toString()));
            table.addCell(cell);
            document.add(table);
//            price
            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(p.getPrice().toString()));
            table.addCell(cell);
            document.add(table);
//            stock
            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(p.getStock().toString()));
            table.addCell(cell);
            document.add(table);
//            categoryleveloneId
            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(p.getCategoryleveloneId().toString()));
            table.addCell(cell);
            document.add(table);
//            categoryleveltwoId
            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(p.getCategoryleveltwoId().toString()));
            table.addCell(cell);
            document.add(table);
//            categorylevelthreeId
            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(p.getCategorylevelthreeId().toString()));
            table.addCell(cell);
            document.add(table);
//            fileName
            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(p.getFileName().toString()));
            table.addCell(cell);
            document.add(table);

        }
        document.close();
    }
}
