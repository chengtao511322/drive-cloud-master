package com.drive.basics.service.mapstruct;

import com.drive.basics.pojo.dto.OperatorAreaEditParam;
import com.drive.basics.pojo.dto.OperatorAreaPageQueryParam;
import com.drive.basics.pojo.entity.OperatorAreaEntity;
import com.drive.basics.pojo.vo.OperatorAreaVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface OperatorAreaMapStruct extends BaseMapStruct<OperatorAreaVo, OperatorAreaEntity, OperatorAreaEditParam, OperatorAreaPageQueryParam >{

}
