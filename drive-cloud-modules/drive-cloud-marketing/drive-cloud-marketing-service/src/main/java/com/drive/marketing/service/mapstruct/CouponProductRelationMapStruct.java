package com.drive.marketing.service.mapstruct;

import com.drive.common.core.base.BaseMapStruct;
import com.drive.marketing.pojo.dto.CouponProductRelationEditParam;
import com.drive.marketing.pojo.dto.CouponProductRelationPageQueryParam;
import com.drive.marketing.pojo.entity.CouponProductRelationEntity;
import com.drive.marketing.pojo.vo.CouponProductRelationVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface CouponProductRelationMapStruct extends BaseMapStruct<CouponProductRelationVo, CouponProductRelationEntity, CouponProductRelationEditParam, CouponProductRelationPageQueryParam >{

}
