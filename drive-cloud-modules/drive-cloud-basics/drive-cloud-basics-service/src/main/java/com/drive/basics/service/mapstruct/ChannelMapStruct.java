package com.drive.basics.service.mapstruct;

import com.drive.basics.pojo.dto.ChannelEditParam;
import com.drive.basics.pojo.dto.ChannelPageQueryParam;
import com.drive.basics.pojo.entity.ChannelEntity;
import com.drive.basics.pojo.vo.ChannelVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface ChannelMapStruct extends BaseMapStruct<ChannelVo, ChannelEntity, ChannelEditParam, ChannelPageQueryParam >{

}
