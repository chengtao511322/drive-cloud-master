package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.OneFeeSystemCoachStudentEditParam;
import com.drive.admin.pojo.dto.OneFeeSystemCoachStudentPageQueryParam;
import com.drive.admin.pojo.entity.OneFeeSystemCoachStudentEntity;
import com.drive.admin.pojo.vo.OneFeeSystemCoachStudentVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface OneFeeSystemCoachStudentMapStruct extends BaseMapStruct<OneFeeSystemCoachStudentVo, OneFeeSystemCoachStudentEntity, OneFeeSystemCoachStudentEditParam, OneFeeSystemCoachStudentPageQueryParam >{

}
