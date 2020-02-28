package com.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.baidu.ueditor.ActionEnter;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@Log
public class UEditorController {
    @RequestMapping(value="/config")
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        String rootPath = ClassUtils.getDefaultClassLoader()
                .getResource("").getPath()+"static/admin/ueditor/jsp";
        try {
            response.setCharacterEncoding("UTF-8");
            String exec = new ActionEnter(request, rootPath).exec();
            log.info(exec);
            PrintWriter writer = response.getWriter();
            writer.write(new ActionEnter( request, rootPath ).exec());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath +"/"+ fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    @ResponseBody //返回json数据
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public JSONObject uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String contentType = file.getContentType();
        System.out.print(contentType);
        String fileName = System.currentTimeMillis()+file.getOriginalFilename();
        String filePath = ClassUtils.getDefaultClassLoader()
                .getResource("").getPath()+"static/admin/upload/";
        JSONObject jo = new JSONObject();//实例化json数据
        if (file.isEmpty()) {
            jo.put("success", 0);
            jo.put("fileName", "");
        }
        try {
            uploadFile(file.getBytes(), filePath, fileName);
            jo.put("success", 1);
            jo.put("fileName", fileName);
        } catch (Exception e) {
        }
        return jo;
    }
}
