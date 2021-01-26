package com.drive.marketing.service.mapstruct;

import com.drive.common.core.base.BaseMapStruct;
import com.drive.marketing.pojo.dto.ActivityEditParam;
import com.drive.marketing.pojo.dto.ActivityInfoPageQueryParam;
import com.drive.marketing.pojo.entity.ActivityInfoEntity;
import com.drive.marketing.pojo.vo.ActivityInfoVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@Component
@Mapper(componentModel = "spring")
public interface ActivityMapStruct extends BaseMapStruct<ActivityInfoVo, ActivityInfoEntity, ActivityEditParam, ActivityInfoPageQueryParam>{

}
