package com.atguigu.spzx.manager.service.impl;

import cn.hutool.core.date.DateTime;
import com.atguigu.spzx.manager.service.FileUploadService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * 包名：com.atguigu.spzx.manager.service.impl
 *
 *
 * 首先，使用MinioClient类创建一个连接到MinIO服务器的客户端，设置服务器的地址、凭据等信息。
 * 接着，通过multipartFile对象获取上传文件的输入流、原始文件名和文件扩展名。
 * 使用UUID生成一个唯一的文件名，并将文件扩展名添加到文件名中。
 * 创建一个文件夹路径，格式为"YYYY/MM/dd"，其中YYYY表示当前年份，MM表示月份，dd表示日期。
 * 构建PutObjectArgs对象，将输入流、输入流可用字节数（文件大小）以及对象路径等信息传入。
 * 调用minioClient的putObject方法将文件上传到MinIO服务器。
 * 最后，拼接上传图片的URL地址，并将其返回给调用者。URL的格式为"http://192.168.222.20:9001/" + "spzx-0628" + "/" + 文件夹路径 + 文件名。
 * @author lct
 * 日期: 2023-10-17   15:22
 */
@Service
public class FileUploadServiceimpl implements FileUploadService {

    @SneakyThrows
    @Override
    public String fileUpload(MultipartFile multipartFile) {
        MinioClient minioClient= MinioClient.builder()
                .endpoint("http://192.168.222.20:9001")
                .credentials("admin","admin123456")
                .build();
        InputStream inputStream = multipartFile.getInputStream();
        String originalFilename = multipartFile.getOriginalFilename(); //获取文件原始名
        String filenameExtension = StringUtils.getFilenameExtension(originalFilename);//获取后缀
        String filename = UUID.randomUUID().toString()+ "."+filenameExtension;
        String folder = DateTime.now().toString("YYYY/MM/dd");
        PutObjectArgs build = PutObjectArgs.builder().bucket("spzx-0628").stream(inputStream, inputStream.available(), -1).object(folder+filename).build();
        minioClient.putObject(build);

        //将上传图片的url地址拼接并返回给调用者
        String url ="http://192.168.222.20:9001/"+"spzx-0628"+"/"+folder+filename;
        System.out.println("给调用者返回url：" + url);
        return url;
    }
}
