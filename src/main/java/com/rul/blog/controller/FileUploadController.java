package com.rul.blog.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 图片上传服务接口层
 *
 * @author RuL
 * @time 2020-06-27
 */
@CrossOrigin
@RestController
public class FileUploadController {

    @Value("${upload.path}")
    private String baseFolderPath;

    /**
     * 上传图片
     *
     * @param image   图片文件
     * @param request 请求
     * @return 上传成功的url,或成功与否提示信息
     */
    @PostMapping("/upload")
    public Object upload(MultipartFile image, HttpServletRequest request) {
        JSONObject response = new JSONObject();

        //时间日期格式化作为子文件夹路径，即每日上传的文件保存在一个小文件夹里面
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dirPath = sdf.format(new Date());
        //文件上传的文件夹路径为baseFolderPath+小文件夹路径
        File baseFolder = new File(baseFolderPath + dirPath);
        if (!baseFolder.exists()) {
            baseFolder.mkdirs();
        }

        //图片名为当前时间戳+原始图片名去掉空格
        String imgName = System.currentTimeMillis() + image.getOriginalFilename().replaceAll(" ", "");
        //目标文件
        File dest = new File(baseFolder, imgName);

        try {
            //使用FileCopyUtils将图片比特流复制到目的文件夹中
            FileCopyUtils.copy(image.getBytes(), dest);

            //图片回显路径
            StringBuffer url = new StringBuffer();
            url.append(request.getScheme())//协议名
                    .append("://")
                    .append(request.getServerName())//ip或者域名
                    .append(":")
                    .append(request.getServerPort())//端口号
                    .append(request.getContextPath())//根路径
                    .append(baseFolderPath)
                    .append(dirPath)
                    .append("/")
                    .append(imgName);

            response.put("url", url);
            response.put("message", "success");
        } catch (IOException e) {
            e.printStackTrace();
            response.put("message", "fail");
        }
        return response;
    }

}
