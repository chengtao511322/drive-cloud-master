package com.drive.marketing.service.mapstruct;

import com.drive.common.core.base.BaseMapStruct;
import com.drive.marketing.pojo.dto.ActivityProjectSettingEditParam;
import com.drive.marketing.pojo.dto.ActivityProjectSettingPageQueryParam;
import com.drive.marketing.pojo.entity.ActivityProjectSettingEntity;
import com.drive.marketing.pojo.vo.ActivityProjectSettingVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface ActivityProjectSettingMapStruct extends BaseMapStruct<ActivityProjectSettingVo, ActivityProjectSettingEntity, ActivityProjectSettingEditParam, ActivityProjectSettingPageQueryParam >{

}
