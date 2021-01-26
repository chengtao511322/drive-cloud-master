package com.drive.marketing.service.mapstruct;

import com.drive.common.core.base.BaseMapStruct;
import com.drive.marketing.pojo.dto.ActivityCouponRelationEditParam;
import com.drive.marketing.pojo.dto.ActivityCouponRelationPageQueryParam;
import com.drive.marketing.pojo.entity.ActivityCouponRelationEntity;
import com.drive.marketing.pojo.vo.ActivityCouponRelationVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface ActivityCouponRelationMapStruct extends BaseMapStruct<ActivityCouponRelationVo, ActivityCouponRelationEntity, ActivityCouponRelationEditParam, ActivityCouponRelationPageQueryParam >{

}
