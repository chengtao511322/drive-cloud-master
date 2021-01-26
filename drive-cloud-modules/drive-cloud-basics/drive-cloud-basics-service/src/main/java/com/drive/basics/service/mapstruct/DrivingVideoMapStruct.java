package com.drive.basics.service.mapstruct;

import com.drive.basics.pojo.dto.DrivingVideoEditParam;
import com.drive.basics.pojo.dto.DrivingVideoPageQueryParam;
import com.drive.basics.pojo.entity.DrivingVideoEntity;
import com.drive.basics.pojo.vo.DrivingVideoVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface DrivingVideoMapStruct extends BaseMapStruct<DrivingVideoVo, DrivingVideoEntity, DrivingVideoEditParam, DrivingVideoPageQueryParam >{

}
