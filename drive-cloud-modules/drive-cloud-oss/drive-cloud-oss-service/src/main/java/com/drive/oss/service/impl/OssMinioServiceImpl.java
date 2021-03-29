package com.drive.oss.service.impl;

import com.drive.oss.config.MinioConfigurationProperties;
import com.drive.oss.service.OssMinioService;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class OssMinioServiceImpl implements OssMinioService {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioConfigurationProperties configurationProperties;
    @Override
    public void updateFile(String pathSrc,MultipartFile file) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidArgumentException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException {
        //获取连接
        //获取桶
        String bucketName = configurationProperties.getBucket();
        //开始上传
        minioClient.putObject(configurationProperties.getBucket(), pathSrc, file.getInputStream(), file.getContentType());
        //返回路径
        minioClient.traceOff();
    }
}
