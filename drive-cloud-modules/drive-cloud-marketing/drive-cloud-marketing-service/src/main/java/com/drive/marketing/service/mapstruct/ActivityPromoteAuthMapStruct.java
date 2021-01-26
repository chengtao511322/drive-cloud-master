package com.drive.marketing.service.mapstruct;

import com.drive.common.core.base.BaseMapStruct;
import com.drive.marketing.pojo.dto.ActivityPromoteAuthEditParam;
import com.drive.marketing.pojo.dto.ActivityPromoteAuthPageQueryParam;
import com.drive.marketing.pojo.entity.ActivityPromoteAuthEntity;
import com.drive.marketing.pojo.vo.ActivityPromoteAuthVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface ActivityPromoteAuthMapStruct extends BaseMapStruct<ActivityPromoteAuthVo, ActivityPromoteAuthEntity, ActivityPromoteAuthEditParam, ActivityPromoteAuthPageQueryParam >{

}
