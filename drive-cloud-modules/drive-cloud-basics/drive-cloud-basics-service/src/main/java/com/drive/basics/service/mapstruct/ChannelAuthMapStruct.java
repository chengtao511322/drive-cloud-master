package com.drive.basics.service.mapstruct;

import com.drive.basics.pojo.dto.ChannelAuthEditParam;
import com.drive.basics.pojo.dto.ChannelAuthPageQueryParam;
import com.drive.basics.pojo.entity.ChannelAuthEntity;
import com.drive.basics.pojo.vo.ChannelAuthVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface ChannelAuthMapStruct extends BaseMapStruct<ChannelAuthVo, ChannelAuthEntity, ChannelAuthEditParam, ChannelAuthPageQueryParam >{

}
