package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.StudentInfoEditParam;
import com.drive.admin.pojo.dto.StudentInfoPageQueryParam;
import com.drive.admin.pojo.entity.StudentInfoEntity;
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface StudentInfoMapStruct extends BaseMapStruct<StudentInfoVo, StudentInfoEntity, StudentInfoEditParam, StudentInfoPageQueryParam >{

}
