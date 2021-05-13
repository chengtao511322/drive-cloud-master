package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.entity.*;
import com.drive.admin.pojo.vo.*;
import com.drive.admin.pojo.dto.*;
import org.mapstruct.Mapper;
import com.drive.common.core.base.BaseMapStruct;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface DeductSettingMapStruct extends BaseMapStruct<DeductSettingVo, DeductSettingEntity, DeductSettingEditParam, DeductSettingPageQueryParam >{

}
