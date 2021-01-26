package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.ServiceItemPriceEditParam;
import com.drive.admin.pojo.dto.ServiceItemPricePageQueryParam;
import com.drive.admin.pojo.entity.ServiceItemPriceEntity;
import com.drive.admin.pojo.vo.ServiceItemPriceVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface ServiceItemPriceMapStruct extends BaseMapStruct<ServiceItemPriceVo, ServiceItemPriceEntity, ServiceItemPriceEditParam, ServiceItemPricePageQueryParam >{

}
