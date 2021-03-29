package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.DriveSchoolEditParam;
import com.drive.admin.pojo.dto.DriveSchoolPageQueryParam;
import com.drive.admin.pojo.entity.DriveSchoolEntity;
import com.drive.admin.pojo.vo.DriveSchoolVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface DriveSchoolMapStruct extends BaseMapStruct<DriveSchoolVo, DriveSchoolEntity, DriveSchoolEditParam, DriveSchoolPageQueryParam >{

}
