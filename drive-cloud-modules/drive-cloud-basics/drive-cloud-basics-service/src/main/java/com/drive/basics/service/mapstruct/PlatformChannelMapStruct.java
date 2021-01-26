package com.drive.basics.service.mapstruct;

import com.drive.basics.pojo.dto.PlatformChannelEditParam;
import com.drive.basics.pojo.dto.PlatformChannelPageQueryParam;
import com.drive.basics.pojo.entity.PlatformChannelEntity;
import com.drive.basics.pojo.vo.PlatformChannelVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface PlatformChannelMapStruct extends BaseMapStruct<PlatformChannelVo, PlatformChannelEntity, PlatformChannelEditParam, PlatformChannelPageQueryParam >{

}
