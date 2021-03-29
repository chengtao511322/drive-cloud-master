package com.drive.basics.service.mapstruct;

import com.drive.basics.pojo.entity.*;
import com.drive.basics.pojo.vo.*;
import com.drive.basics.pojo.dto.*;
import org.mapstruct.Mapper;
import com.drive.common.core.base.BaseMapStruct;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface DriveFlowMapStruct extends BaseMapStruct<DriveFlowVo, DriveFlowEntity, DriveFlowEditParam, DriveFlowPageQueryParam >{

}
