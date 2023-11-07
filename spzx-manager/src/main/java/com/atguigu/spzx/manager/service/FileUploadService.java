package com.atguigu.spzx.manager.service;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

/**
 * 包名：com.atguigu.spzx.manager.service
 *
 * @author lct
 * 日期: 2023-10-17   15:19
 */
@Mapper
public interface FileUploadService {

    String fileUpload(MultipartFile multipartFile);
}
