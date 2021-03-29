package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.CoachHourSettingEditParam;
import com.drive.admin.pojo.dto.CoachHourSettingPageQueryParam;
import com.drive.admin.pojo.entity.CoachHourSettingEntity;
import com.drive.admin.pojo.vo.CoachHourSettingVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface CoachHourSettingMapStruct extends BaseMapStruct<CoachHourSettingVo, CoachHourSettingEntity, CoachHourSettingEditParam, CoachHourSettingPageQueryParam >{

}
