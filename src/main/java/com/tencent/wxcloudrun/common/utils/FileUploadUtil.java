package com.tencent.wxcloudrun.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Description TODO
 */
@Component
@Slf4j
public class FileUploadUtil {

    /**
     * 文件上传路径
     */
    @Value("${myFile.path}")
    public String path;
    @Value("${myFile.url}")
    public String url;

    /**
     * 上传文件到指定本地磁盘
     * @param file
     * @return
     * @throws IOException
     */
    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        //获取文件名后缀
        assert fileName != null;
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName = UUID.randomUUID()+"."+fileExtensionName;
        File fileDir = new File(path);
        if(!fileDir.exists()){
            //给权限
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploadFileName);
        //保存
        file.transferTo(targetFile);
        return uploadFileName;
    }
}
