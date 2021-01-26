package com.drive.member.service.mapstruct;

import com.drive.common.core.base.BaseMapStruct;
import com.drive.member.pojo.dto.StudentStudyEnrollEditParam;
import com.drive.member.pojo.dto.StudentStudyEnrollPageQueryParam;
import com.drive.member.pojo.entity.StudentStudyEnrollEntity;
import com.drive.member.pojo.vo.StudentStudyEnrollVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface StudentStudyEnrollMapStruct extends BaseMapStruct<StudentStudyEnrollVo, StudentStudyEnrollEntity, StudentStudyEnrollEditParam, StudentStudyEnrollPageQueryParam >{

}
