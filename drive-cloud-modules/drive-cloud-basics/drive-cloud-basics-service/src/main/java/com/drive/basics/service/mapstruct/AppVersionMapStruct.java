package com.drive.basics.service.mapstruct;

import com.drive.basics.pojo.dto.AppVersionEditParam;
import com.drive.basics.pojo.dto.AppVersionPageQueryParam;
import com.drive.basics.pojo.entity.AppVersionEntity;
import com.drive.basics.pojo.vo.AppVersionVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface AppVersionMapStruct extends BaseMapStruct<AppVersionVo, AppVersionEntity, AppVersionEditParam, AppVersionPageQueryParam >{

}
