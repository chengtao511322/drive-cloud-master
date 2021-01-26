package com.drive.marketing.service.mapstruct;

import com.drive.common.core.base.BaseMapStruct;
import com.drive.marketing.pojo.dto.ActivityApplyEditParam;
import com.drive.marketing.pojo.dto.ActivityApplyPageQueryParam;
import com.drive.marketing.pojo.entity.ActivityApplyEntity;
import com.drive.marketing.pojo.vo.ActivityApplyVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface ActivityApplyMapStruct extends BaseMapStruct<ActivityApplyVo, ActivityApplyEntity, ActivityApplyEditParam, ActivityApplyPageQueryParam >{

}
