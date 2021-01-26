package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.ServiceItemEditParam;
import com.drive.admin.pojo.dto.ServiceItemPageQueryParam;
import com.drive.admin.pojo.entity.ServiceItemEntity;
import com.drive.admin.pojo.vo.ServiceItemVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface ServiceItemMapStruct extends BaseMapStruct<ServiceItemVo, ServiceItemEntity, ServiceItemEditParam, ServiceItemPageQueryParam >{

}
