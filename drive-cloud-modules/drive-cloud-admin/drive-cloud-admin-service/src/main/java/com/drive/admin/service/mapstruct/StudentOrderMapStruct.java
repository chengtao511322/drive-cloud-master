package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.StudentOrderEditParam;
import com.drive.admin.pojo.dto.StudentOrderPageQueryParam;
import com.drive.admin.pojo.entity.StudentOrderEntity;
import com.drive.admin.pojo.vo.StudentOrderVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface StudentOrderMapStruct extends BaseMapStruct<StudentOrderVo, StudentOrderEntity, StudentOrderEditParam, StudentOrderPageQueryParam >{

}
