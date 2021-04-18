package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.StudentCoachAppraiseEditParam;
import com.drive.admin.pojo.dto.StudentCoachAppraisePageQueryParam;
import com.drive.admin.pojo.entity.StudentCoachAppraiseEntity;
import com.drive.admin.pojo.vo.StudentCoachAppraiseVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface StudentCoachAppraiseMapStruct extends BaseMapStruct<StudentCoachAppraiseVo, StudentCoachAppraiseEntity, StudentCoachAppraiseEditParam, StudentCoachAppraisePageQueryParam >{

}
