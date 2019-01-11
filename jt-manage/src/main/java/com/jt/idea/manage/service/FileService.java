package com.jt.idea.manage.service;

import com.jt.idea.common.vo.PicUploadResult;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public PicUploadResult fileUpload(MultipartFile uploadFile) ;
}
