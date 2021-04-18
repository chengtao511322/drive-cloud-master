package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.ServiceReturnVisitHistoryEditParam;
import com.drive.admin.pojo.dto.ServiceReturnVisitHistoryPageQueryParam;
import com.drive.admin.pojo.entity.ServiceReturnVisitHistoryEntity;
import com.drive.admin.pojo.vo.ServiceReturnVisitHistoryVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface ServiceReturnVisitHistoryMapStruct extends BaseMapStruct<ServiceReturnVisitHistoryVo, ServiceReturnVisitHistoryEntity, ServiceReturnVisitHistoryEditParam, ServiceReturnVisitHistoryPageQueryParam >{

}
