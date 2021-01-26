package com.drive.system.service.mapstruct;

import com.drive.common.core.base.BaseMapStruct;
import com.drive.system.pojo.dto.OauthClientDetailsEditParam;
import com.drive.system.pojo.dto.OauthClientDetailsPageQueryParam;
import com.drive.system.pojo.entity.OauthClientDetailsEntity;
import com.drive.system.pojo.vo.OauthClientDetailsVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface OauthClientDetailsMapStruct extends BaseMapStruct<OauthClientDetailsVo, OauthClientDetailsEntity, OauthClientDetailsEditParam, OauthClientDetailsPageQueryParam >{

}
