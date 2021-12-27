package com.zsj.es.api.controller;

import com.zsj.es.common.JsonResult;
import com.zsj.es.common.exception.BusinessException;
import com.zsj.es.common.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 上传服务
 */
@Controller
@RequestMapping("/api/file")
public class ApiFileController {

    @Value("${file.filePath}")
    private String filePath;

    @Value("${file.successPath}")
    private String fileSuccessPath;

    @RequestMapping("/uploadIm")
    @ResponseBody
    public JsonResult uploadIm(@RequestParam(value = "file") MultipartFile file) {
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        fileName = UUIDUtil.randomUUID8() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("文件上传失败！");
        }
        String src = fileSuccessPath + "/" + fileName;
        Map<String,Object> map=new HashMap<>();
        map.put("src",src);
        return JsonResult.ok(0,"文件上传成功").put("data",map);
    }
    
}
