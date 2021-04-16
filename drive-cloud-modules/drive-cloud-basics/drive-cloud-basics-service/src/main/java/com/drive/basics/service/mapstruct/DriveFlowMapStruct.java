package com.drive.basics.service.mapstruct;

import com.drive.basics.pojo.dto.DriveFlowEditParam;
import com.drive.basics.pojo.dto.DriveFlowPageQueryParam;
import com.drive.basics.pojo.entity.DriveFlowEntity;
import com.drive.basics.pojo.vo.DriveFlowVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface DriveFlowMapStruct extends BaseMapStruct<DriveFlowVo, DriveFlowEntity, DriveFlowEditParam, DriveFlowPageQueryParam >{

}
