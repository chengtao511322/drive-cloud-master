package com.drive.oss.service;

import cn.hutool.core.util.StrUtil;
import com.drive.oss.config.MinioConfigurationProperties;
import com.drive.oss.constant.CommonUtils;
import com.drive.oss.exception.MinioException;
import com.drive.oss.exception.MinioFetchException;
import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.PutObjectOptions;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * minio 服务
 * @author xiaoguo
 */
@Service
@Slf4j
public class MinioService {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioConfigurationProperties configurationProperties;

    public List<Item> list() {
        Iterable<Result<Item>> myObjects = minioClient.listObjects(configurationProperties.getBucket(), "", false);
        return getItems(myObjects);
    }

    public List<Item> fullList() throws MinioException {
        try {
            Iterable<Result<Item>> myObjects = minioClient.listObjects(configurationProperties.getBucket());
            return getItems(myObjects);
        } catch (XmlParserException e) {
            throw new MinioException("Error while fetching files in Minio", e);
        }
    }

    public List<Item> list(Path path) {

        Iterable<Result<Item>> myObjects = minioClient.listObjects(configurationProperties.getBucket(), path.toString(), false);
        return getItems(myObjects);
    }

    public List<Item> getFullList(Path path) throws MinioException {
        try {
            Iterable<Result<Item>> myObjects = minioClient.listObjects(configurationProperties.getBucket(), path.toString());
            return getItems(myObjects);
        } catch (XmlParserException e) {
            throw new MinioException("Error while fetching files in Minio", e);
        }
    }

    private List<Item> getItems(Iterable<Result<Item>> myObjects) {
        return StreamSupport
                .stream(myObjects.spliterator(), true)
                .map(itemResult -> {
                    try {
                        return itemResult.get();
                    } catch (InvalidBucketNameException |
                            NoSuchAlgorithmException |
                            InsufficientDataException |
                            IOException |
                            InvalidKeyException |
                            XmlParserException |
                            InvalidResponseException|
                            ErrorResponseException |
                            InternalException e) {
                        throw new MinioFetchException("Error while parsing list of objects", e);
                    }
                })
                .collect(Collectors.toList());
    }

    public InputStream get(Path path) throws MinioException {
        try {
            return minioClient.getObject(configurationProperties.getBucket(), path.toString());
        } catch (XmlParserException | InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException  | ErrorResponseException | InternalException  | InvalidResponseException e) {
            throw new MinioException("Error while fetching files in Minio", e);
        }
    }

    public ObjectStat getMetadata(Path path) throws MinioException {
        try {
            return minioClient.statObject(configurationProperties.getBucket(), path.toString());
        } catch (XmlParserException | InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException |  ErrorResponseException | InternalException  | InvalidResponseException e) {
            throw new MinioException("Error while fetching files in Minio", e);
        }
    }

    public Map<Path, ObjectStat> getMetadata(Iterable<Path> paths) {
        return StreamSupport.stream(paths.spliterator(), false)
                .map(path -> {
                    try {
                        return new HashMap.SimpleEntry<>(path, minioClient.statObject(configurationProperties.getBucket(), path.toString()));
                    } catch (InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException |  XmlParserException | ErrorResponseException | InternalException | InvalidResponseException  e) {
                        throw new MinioFetchException("Error while parsing list of objects", e);
                    }
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void getAndSave(Path source, String fileName) throws MinioException {
        try {
            minioClient.getObject(configurationProperties.getBucket(), source.toString(), fileName);
        } catch (XmlParserException | InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException |  ErrorResponseException | InternalException  | InvalidResponseException e) {
            throw new MinioException("Error while fetching files in Minio", e);
        }
    }

    public void upload(Path source, InputStream file, Map<String, String> headers) throws
            MinioException {
        try {
            PutObjectOptions options = new PutObjectOptions(file.available(), -1);
            options.setHeaders(headers);
            minioClient.putObject(configurationProperties.getBucket(), source.toString(), file, options);
        } catch (XmlParserException | InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException |  ErrorResponseException | InternalException  | InvalidResponseException e) {
            throw new MinioException("Error while fetching files in Minio", e);
        }
    }

    public void upload(Path source, InputStream file) throws
            MinioException {
        try {
            minioClient.putObject(configurationProperties.getBucket(), source.toString(), file, new PutObjectOptions(file.available(),-1));
        } catch (XmlParserException | InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException |  ErrorResponseException | InternalException  | InvalidResponseException e) {
            throw new MinioException("Error while fetching files in Minio", e);
        }
    }

    public void upload(Path source, InputStream file, String contentType, Map<String, String> headers) throws
            MinioException {
        try {
            PutObjectOptions options = new PutObjectOptions(file.available(), -1);
            options.setContentType(contentType);
            options.setHeaders(headers);
            minioClient.putObject(configurationProperties.getBucket(), source.toString(), file, options);
        } catch (XmlParserException | InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException |  ErrorResponseException | InternalException  | InvalidResponseException e) {
            throw new MinioException("Error while fetching files in Minio", e);
        }
    }

    public void upload(Path source, InputStream file, String contentType) throws
            MinioException {
        try {
            // currentDir.toString().replace("\\", "/")
            PutObjectOptions options = new PutObjectOptions(file.available(), -1);
            options.setContentType(contentType);
            minioClient.putObject(configurationProperties.getBucket(), source.toString(), file, options);
        } catch (XmlParserException | InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException |  ErrorResponseException | InternalException  | InvalidResponseException e) {
            log.error("出错{}",e.getMessage());
            throw new MinioException("Error while fetching files in Minio", e);
        }
    }

    public void upload(Path source, File file,int partSize) throws
            MinioException {
        try {
            minioClient.putObject(configurationProperties.getBucket(), source.toString(), file.getAbsolutePath(),new PutObjectOptions(Files.size(file.toPath()),partSize));
        } catch (XmlParserException | InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException |  ErrorResponseException | InternalException  | InvalidResponseException e) {
            throw new MinioException("Error while fetching files in Minio", e);
        }
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    public String uploadHeadImg(MultipartFile file, String bizPath) {
        //log.info("minio相关配置：minio_url:{}, minio_name:{}, minio_pass:{}, bucketName:{}", minioUrl, minioName, minioPass, bucketName);
        String file_url = "";
        String newBucket = configurationProperties.getBucket();

        try {
            // 检查存储桶是否已经存在
            if(!(minioClient.bucketExists(newBucket))) {
                log.error("Bucket already exists.");
                return "桶错误";
            }
            InputStream stream = file.getInputStream();
            // 获取文件名
            String orgName = file.getOriginalFilename();
            orgName = CommonUtils.getFileName(orgName);
            ///String objectName = bizPath+"/"+orgName.substring(0, orgName.lastIndexOf(".")) + "_" + System.currentTimeMillis() + orgName.substring(orgName.indexOf("."));
            PutObjectOptions options = new PutObjectOptions(stream.available(), -1);
            options.setContentType(file.getContentType());
            // 使用putObject上传一个本地文件到存储桶中。
            minioClient.putObject(newBucket,bizPath, bizPath, options);
            stream.close();
            file_url = configurationProperties.getUrl()+newBucket+"/"+bizPath;
        }catch (IOException e){
            log.error(e.getMessage(), e);
        } catch (InvalidKeyException e) {
            log.error(e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);

        } catch (InvalidBucketNameException e) {
            log.error(e.getMessage(), e);
        } catch (ErrorResponseException e) {
            log.error(e.getMessage(), e);
        } catch (InternalException e) {
            log.error(e.getMessage(), e);
        } catch (InsufficientDataException e) {
            log.error(e.getMessage(), e);
        } catch (InvalidResponseException e) {
            e.printStackTrace();
        } catch (XmlParserException e) {
            e.printStackTrace();
        }
        return file_url;
    }




    public void remove(Path source) throws MinioException {
        try {
            minioClient.removeObject(configurationProperties.getBucket(), source.toString());
        } catch (XmlParserException | InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException |  ErrorResponseException | InternalException  | InvalidResponseException e) {
            throw new MinioException("Error while fetching files in Minio", e);
        }
    }

}