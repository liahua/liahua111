package com.jt.idea.manage.controller;

import com.jt.idea.common.vo.PicUploadResult;
import com.jt.idea.manage.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping("/fileDemo")
    public String fileDemo(MultipartFile image) {
        /**
         * 定义文件夹路径
         */
        String dir = "E:/jt-upload";
        /**
         * 获取文件名
         */
        String fileName = image.getOriginalFilename();
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            /**
             * 上传图片
             */
            image.transferTo(new File(dir + "/" + fileName));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/file.jsp";
    }

    @RequestMapping("/pic/upload")
    @ResponseBody
    public PicUploadResult fileUpload(MultipartFile uploadFile) {
        return fileService.fileUpload(uploadFile);
    }


}
