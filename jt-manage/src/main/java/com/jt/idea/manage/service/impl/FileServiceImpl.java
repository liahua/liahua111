package com.jt.idea.manage.service.impl;


import com.jt.idea.common.vo.PicUploadResult;
import com.jt.idea.manage.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {


    /**
     * 文件存储的根目录
     */
    @Value("${image.fileDir}")
    private String fileDir;
    @Value("${image.urlPath}")
    private String urlPath;


    @Override
    public PicUploadResult fileUpload(MultipartFile uploadFile) {

        /**
         * 1.检查是否为图片
         * 2.检查是否为木马
         * 3.存储到硬盘上
         * 4.
         */

        PicUploadResult picUploadResult = new PicUploadResult();

        String fileName = uploadFile.getOriginalFilename();

        fileName = fileName.toLowerCase();
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        String regex = "^.+\\.(jpg||png||gif)$";
        if (!fileName.matches(regex)) {
            picUploadResult.setError(1);
            return picUploadResult;

        }
        //判断是否为恶意程序
        try {

            InputStream inputStream = uploadFile.getInputStream();
            BufferedImage read = ImageIO.read(inputStream);
            int width = read.getWidth();
            int height = read.getHeight();
            if (width == 0 || height == 0) {
                picUploadResult.setError(1);
                return picUploadResult;
            }
            picUploadResult.setHeight(height+"");
            picUploadResult.setWidth(width+"");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //分类存储
        /*时间分类存储*/
        String dataDir = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        String localPathDir = fileDir+ dataDir;
        File file = new File(localPathDir);
        if(!file.exists()){
            file.mkdirs();
        }
        //生成文件唯一标识名称
        String uuid = UUID.randomUUID().toString().replace("-", "");
        int randomNum = new Random().nextInt(1000);
        String imageFileName = uuid + randomNum + fileType;
        String realFilePath = localPathDir + "/" + imageFileName;
        File imageFile = new File(realFilePath);
        try {
            uploadFile.transferTo(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //实现图片回显
        String urlRealPath=urlPath+dataDir+"/"+imageFileName;

        picUploadResult.setUrl(urlRealPath);


        return picUploadResult;
    }
}
