package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.ServicePackageDetailEditParam;
import com.drive.admin.pojo.dto.ServicePackageDetailPageQueryParam;
import com.drive.admin.pojo.entity.ServicePackageDetailEntity;
import com.drive.admin.pojo.vo.ServicePackageDetailVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface ServicePackageDetailMapStruct extends BaseMapStruct<ServicePackageDetailVo, ServicePackageDetailEntity, ServicePackageDetailEditParam, ServicePackageDetailPageQueryParam >{

}
