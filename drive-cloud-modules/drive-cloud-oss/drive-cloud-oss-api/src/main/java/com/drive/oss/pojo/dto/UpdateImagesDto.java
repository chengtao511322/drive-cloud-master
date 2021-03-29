package com.drive.oss.pojo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author 小郭
 * @Description //图片上传对象
 * @Date $ $
 * @Param $
 * @return $
 **/
@Data
public class UpdateImagesDto {

    private String userId;

    private MultipartFile file;
    // 图片名称
    private String imagesName;
}
