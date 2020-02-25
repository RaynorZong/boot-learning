package com.demo6.controller;

import com.demo6.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@Slf4j
public class TestController {

    @PostMapping(value ="/json", consumes={MediaType.APPLICATION_JSON_VALUE }, produces="application/json;charset=UTF-8")
    @ResponseBody
    public User json(@RequestBody Map<String,Object> jsonData){
        Map<String,Object> resultData = new HashMap<>(jsonData);
        resultData.put("resultCode", UUID.randomUUID().toString());
        User user = new User();
        user.setDate(LocalDate.now());
        return user;
    }

    @PostMapping(value = "xml",consumes = {MediaType.APPLICATION_XML_VALUE},produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public User xml(@RequestBody User user){
      log.info(user.getName());
      return user;
    }

    @RequestMapping(value = "/form")
    @ResponseBody
    public String form1(@RequestParam String name){
        return name;
    }
    @RequestMapping(value = "/form2")
    @ResponseBody
    public String form2( User user){
        return user.getName();
    }

    @PostMapping(value = "/file",consumes ={ MediaType.MULTIPART_FORM_DATA_VALUE })
    @ResponseBody
    public String file(@RequestParam("file") MultipartFile file){
        String fileName  = file.getOriginalFilename();
        File root = new File("D:/");
        if(!root.isDirectory()){
            root.mkdir();
        }
        try {
            file.transferTo(new File(root,fileName));
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "failed";
    }


	@GetMapping(path ="/download")
    public ResponseEntity<Resource> download(@RequestParam("name")String name)throws IOException{
        File file =new File("D:/Raynor", name);
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource =new ByteArrayResource(Files.readAllBytes(path));
        return ResponseEntity.ok().header("Content-Disposition","attachment;fileName="+ name)
                .contentLength(file.length()).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
    }

    @PostMapping(value = "/data1", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String data1(@RequestBody byte[] body) throws Exception {
        return body.toString();
    }
    @PostMapping(value = "/data2", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String data2(InputStream inputStream, HttpServletRequest request) throws Exception {
        log.info(request.getInputStream().toString());
        return inputStream.toString();
    }


}
