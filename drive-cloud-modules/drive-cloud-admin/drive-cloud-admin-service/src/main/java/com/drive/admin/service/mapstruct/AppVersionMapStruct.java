package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.AppVersionEditParam;
import com.drive.admin.pojo.dto.AppVersionPageQueryParam;
import com.drive.admin.pojo.entity.AppVersionEntity;
import com.drive.admin.pojo.vo.AppVersionVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface AppVersionMapStruct extends BaseMapStruct<AppVersionVo, AppVersionEntity, AppVersionEditParam, AppVersionPageQueryParam >{

}
