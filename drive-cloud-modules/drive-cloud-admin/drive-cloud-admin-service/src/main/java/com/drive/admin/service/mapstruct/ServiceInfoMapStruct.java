package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.ServiceInfoEditParam;
import com.drive.admin.pojo.dto.ServiceInfoPageQueryParam;
import com.drive.admin.pojo.entity.ServiceInfoEntity;
import com.drive.admin.pojo.vo.ServiceInfoVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface ServiceInfoMapStruct extends BaseMapStruct<ServiceInfoVo, ServiceInfoEntity, ServiceInfoEditParam, ServiceInfoPageQueryParam >{

}
