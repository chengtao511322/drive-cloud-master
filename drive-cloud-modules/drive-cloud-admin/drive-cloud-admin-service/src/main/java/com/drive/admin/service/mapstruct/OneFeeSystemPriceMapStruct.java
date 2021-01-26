package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.OneFeeSystemPriceEditParam;
import com.drive.admin.pojo.dto.OneFeeSystemPricePageQueryParam;
import com.drive.admin.pojo.entity.OneFeeSystemPriceEntity;
import com.drive.admin.pojo.vo.OneFeeSystemPriceVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface OneFeeSystemPriceMapStruct extends BaseMapStruct<OneFeeSystemPriceVo, OneFeeSystemPriceEntity, OneFeeSystemPriceEditParam, OneFeeSystemPricePageQueryParam >{

}
