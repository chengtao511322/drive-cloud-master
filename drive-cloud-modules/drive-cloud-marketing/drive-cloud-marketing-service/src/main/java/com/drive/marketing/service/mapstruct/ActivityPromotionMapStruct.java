package com.drive.marketing.service.mapstruct;

import com.drive.common.core.base.BaseMapStruct;
import com.drive.marketing.pojo.dto.ActivityPromotionEditParam;
import com.drive.marketing.pojo.dto.ActivityPromotionPageQueryParam;
import com.drive.marketing.pojo.entity.ActivityPromotionEntity;
import com.drive.marketing.pojo.vo.ActivityPromotionVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface ActivityPromotionMapStruct extends BaseMapStruct<ActivityPromotionVo, ActivityPromotionEntity, ActivityPromotionEditParam, ActivityPromotionPageQueryParam > {

}
