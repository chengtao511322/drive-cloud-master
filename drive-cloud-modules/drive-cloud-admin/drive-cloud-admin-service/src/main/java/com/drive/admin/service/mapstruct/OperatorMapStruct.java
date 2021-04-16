package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.OperatorEditParam;
import com.drive.admin.pojo.dto.OperatorPageQueryParam;
import com.drive.admin.pojo.entity.OperatorEntity;
import com.drive.admin.pojo.vo.OperatorVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface OperatorMapStruct extends BaseMapStruct<OperatorVo, OperatorEntity, OperatorEditParam, OperatorPageQueryParam >{

}
