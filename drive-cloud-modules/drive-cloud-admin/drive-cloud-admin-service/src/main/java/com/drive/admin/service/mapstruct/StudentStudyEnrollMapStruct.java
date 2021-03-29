package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.StudentStudyEnrollEditParam;
import com.drive.admin.pojo.dto.StudentStudyEnrollPageQueryParam;
import com.drive.admin.pojo.entity.StudentStudyEnrollEntity;
import com.drive.admin.pojo.vo.StudentStudyEnrollVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface StudentStudyEnrollMapStruct extends BaseMapStruct<StudentStudyEnrollVo, StudentStudyEnrollEntity, StudentStudyEnrollEditParam, StudentStudyEnrollPageQueryParam >{

}
