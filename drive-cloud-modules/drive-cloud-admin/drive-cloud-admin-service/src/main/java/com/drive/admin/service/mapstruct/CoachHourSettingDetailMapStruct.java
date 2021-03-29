package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.CoachHourSettingDetailEditParam;
import com.drive.admin.pojo.dto.CoachHourSettingDetailPageQueryParam;
import com.drive.admin.pojo.entity.CoachHourSettingDetailEntity;
import com.drive.admin.pojo.vo.CoachHourSettingDetailVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface CoachHourSettingDetailMapStruct extends BaseMapStruct<CoachHourSettingDetailVo, CoachHourSettingDetailEntity, CoachHourSettingDetailEditParam, CoachHourSettingDetailPageQueryParam >{

}
