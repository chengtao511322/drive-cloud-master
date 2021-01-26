package com.drive.monitor.service.mapstruct;

import com.drive.common.core.base.BaseMapStruct;
import com.drive.monitor.pojo.dto.OperLogEditParam;
import com.drive.monitor.pojo.dto.OperLogPageQueryParam;
import com.drive.monitor.pojo.entity.OperLogEntity;
import com.drive.monitor.pojo.vo.OperLogVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface OperLogMapStruct extends BaseMapStruct<OperLogVo, OperLogEntity, OperLogEditParam, OperLogPageQueryParam >{

}
