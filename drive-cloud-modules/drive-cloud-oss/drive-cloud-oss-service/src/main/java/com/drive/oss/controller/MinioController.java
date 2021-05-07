package com.drive.oss.controller;

import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.constant.Constants;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.log.annotation.EventLog;
import com.drive.oss.config.MinioConfigurationProperties;
import com.drive.oss.exception.MinioException;
import com.drive.oss.pojo.dto.UpdateImagesDto;
import com.drive.oss.service.OssMinioService;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Minio 对象存储
 *
 * @author DreamChan
 */
@Api(tags = "Minio对象存储管理")
@Slf4j
@RestController
@RequestMapping("/minio")
public class MinioController {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioConfigurationProperties configurationProperties;

    @Autowired
    private MinioConfigurationProperties minioConfig;

    @Autowired
    private OssMinioService ossMinioService;

    /**
     * 文件上传
     */
    @ApiOperation("文件上传")
    @EventLog(message = "文件上传", businessType = EventLogEnum.CREATE)
    @PostMapping("/upload")
    public ResObject upload(@RequestParam("file") MultipartFile file) throws IOException, MinioException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidArgumentException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException {
        // 文件存储路径  /2020-01-01/43897583617343545473.jpg
        String filename = file.getOriginalFilename();
        int begin = filename.indexOf(".");
        int last = filename.length();
        String ext = filename.substring(begin, last);
        String pathSrc = Constants.UPLOAD_HEAD_IMAGES+"/"+LocalDate.now().toString()+"/"+Instant.now().toEpochMilli() +  ext;
        Path path = Paths.get(pathSrc);
        //String path = Constants.UPLOAD_HEAD_IMAGES+"/"+LocalDate.now().toString()+"/"+Instant.now().toEpochMilli() +  ext;
        //Path path = Paths.get(LocalDate.now().toString(), Instant.now().toEpochMilli() +  ext);
        // Path newDir = Files.createDirectories(path);
        // minio仓库名
        ossMinioService.updateFile(pathSrc,file);
        Map result = new HashMap();
        result.put("fileName", file.getOriginalFilename());
        result.put("filePath", pathSrc);
        return R.success(result);
    }
    /**
     * 文件上传
     */
    @ApiOperation("图片上传")
    @EventLog(message = "文件上传", businessType = EventLogEnum.CREATE)
    @PostMapping("/uploadImages")
    public ResObject uploadImages(UpdateImagesDto updateImagesDto) throws IOException, MinioException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidArgumentException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException {
        // 文件存储路径  /2020-01-01/43897583617343545473.jpg
        if (updateImagesDto.getFile() == null){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        String filename = updateImagesDto.getFile().getOriginalFilename();
        int begin = filename.indexOf(".");
        int last = filename.length();
        String ext = filename.substring(begin, last);
        String pathSrc = Constants.IMAGES+"/"+updateImagesDto.getUserId()+"/"+updateImagesDto.getImagesName() +  ext;
        Path path = Paths.get(pathSrc);
        //String path = Constants.UPLOAD_HEAD_IMAGES+"/"+LocalDate.now().toString()+"/"+Instant.now().toEpochMilli() +  ext;
        //Path path = Paths.get(LocalDate.now().toString(), Instant.now().toEpochMilli() +  ext);
        // Path newDir = Files.createDirectories(path);
        // minio仓库名
        ossMinioService.updateFile(pathSrc,updateImagesDto.getFile());
        Map result = new HashMap();
        result.put("fileName", updateImagesDto.getFile().getOriginalFilename());
        result.put("filePath", pathSrc);
        return R.success(result);
    }

    /*@PostMapping(value = "/uploadHeadImg")
    public ResObject uploadHeadImg(HttpServletRequest request, HttpServletResponse response) throws IOException {

        R result = new R();
        String savePath = "";
//      String bizPath = request.getParameter("path");
        String bizPath = request.getHeader("path");
        log.info("bizPath:{}", bizPath);

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");// 获取上传文件对象
        String filename = file.getOriginalFilename();
        int begin = filename.indexOf(".");
        int last = filename.length();
        String ext = filename.substring(begin, last);
        String pathSrc = Constants.UPLOAD_HEAD_IMAGES+"/"+filename;
        minioService.uploadHeadImg(file,pathSrc);
        return R.success(pathSrc);
    }*/


   /* @ApiOperation("获取文件")
    @GetMapping("/{object}")
    public void get(@PathVariable("object") String object, HttpServletResponse response) throws IOException, MinioException {
        InputStream inputStream = minioService.get(Paths.get(object));

        response.addHeader("Content-disposition", "attachment;filename=" + object);
        response.setContentType(URLConnection.guessContentTypeFromName(object));
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }


    @ApiOperation("文件删除")
    @EventLog(message = "文件删除", businessType = EventLogEnum.DELETE)
    @DeleteMapping("/{object}")
    public ResObject remove(@PathVariable("object") String object) throws MinioException {
        Path path = Paths.get(object);
        minioService.remove(path);

        return R.success("删除成功");
    }*/
}
