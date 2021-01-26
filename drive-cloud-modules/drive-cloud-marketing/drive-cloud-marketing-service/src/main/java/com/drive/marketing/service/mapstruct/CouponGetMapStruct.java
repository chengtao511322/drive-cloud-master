package com.drive.marketing.service.mapstruct;

import com.drive.common.core.base.BaseMapStruct;
import com.drive.marketing.pojo.dto.CouponGetEditParam;
import com.drive.marketing.pojo.dto.CouponGetPageQueryParam;
import com.drive.marketing.pojo.entity.CouponGetEntity;
import com.drive.marketing.pojo.vo.CouponGetVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface CouponGetMapStruct extends BaseMapStruct<CouponGetVo, CouponGetEntity, CouponGetEditParam, CouponGetPageQueryParam >{

}
