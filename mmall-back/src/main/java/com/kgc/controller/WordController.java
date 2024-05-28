package com.kgc.controller;

import com.kgc.util.ExportWordUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiang on 4/26/22 11:05 AM
 */
@RestController
@RequestMapping("/word")
public class WordController {
    @GetMapping("/export")
    public void export(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> params = new HashMap<>();
        params.put("username","这是标题");
        params.put("substatus","哈哈");
        params.put("subtime","了咯哦");
        params.put("zhouqi","讷讷");
        params.put("neirong","iiik");
        InputStream is = this.getClass().getResourceAsStream("/word/export.docx");
        ExportWordUtils.exportWord(is,"/Users/jiangyiwei/myVueProject/MMALL/test","aaa.docx",params,request,response);
    }

}
