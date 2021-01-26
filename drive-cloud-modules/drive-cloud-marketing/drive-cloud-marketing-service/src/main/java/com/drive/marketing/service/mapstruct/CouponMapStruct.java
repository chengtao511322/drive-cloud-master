package com.drive.marketing.service.mapstruct;

import com.drive.common.core.base.BaseMapStruct;
import com.drive.marketing.pojo.dto.CouponEditParam;
import com.drive.marketing.pojo.dto.CouponPageQueryParam;
import com.drive.marketing.pojo.entity.CouponEntity;
import com.drive.marketing.pojo.vo.CouponVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface CouponMapStruct extends BaseMapStruct<CouponVo, CouponEntity, CouponEditParam, CouponPageQueryParam >{

}
