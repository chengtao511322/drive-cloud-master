package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.StudentTestEnrollEditParam;
import com.drive.admin.pojo.dto.StudentTestEnrollPageQueryParam;
import com.drive.admin.pojo.entity.StudentTestEnrollEntity;
import com.drive.admin.pojo.vo.StudentTestEnrollVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface StudentTestEnrollMapStruct extends BaseMapStruct<StudentTestEnrollVo, StudentTestEnrollEntity, StudentTestEnrollEditParam, StudentTestEnrollPageQueryParam >{

}
