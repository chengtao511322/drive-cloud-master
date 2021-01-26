package com.drive.marketing.service.mapstruct;

import com.drive.common.core.base.BaseMapStruct;
import com.drive.marketing.pojo.dto.ChannelManagerActivityEditParam;
import com.drive.marketing.pojo.dto.ChannelManagerActivityPageQueryParam;
import com.drive.marketing.pojo.entity.ChannelManagerActivityEntity;
import com.drive.marketing.pojo.vo.ChannelManagerActivityVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface ChannelManagerActivityMapStruct extends BaseMapStruct<ChannelManagerActivityVo, ChannelManagerActivityEntity, ChannelManagerActivityEditParam, ChannelManagerActivityPageQueryParam >{

}
