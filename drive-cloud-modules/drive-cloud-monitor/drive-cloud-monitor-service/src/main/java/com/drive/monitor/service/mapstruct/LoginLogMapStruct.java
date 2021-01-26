package com.drive.monitor.service.mapstruct;

import com.drive.common.core.base.BaseMapStruct;
import com.drive.monitor.pojo.dto.LoginLogEditParam;
import com.drive.monitor.pojo.dto.LoginLogPageQueryParam;
import com.drive.monitor.pojo.entity.LoginLogEntity;
import com.drive.monitor.pojo.vo.LoginLogVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface LoginLogMapStruct extends BaseMapStruct<LoginLogVo, LoginLogEntity, LoginLogEditParam, LoginLogPageQueryParam >{

}
