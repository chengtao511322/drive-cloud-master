package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.OneFeeSystemVipCoachEditParam;
import com.drive.admin.pojo.dto.OneFeeSystemVipCoachPageQueryParam;
import com.drive.admin.pojo.entity.OneFeeSystemVipCoachEntity;
import com.drive.admin.pojo.vo.OneFeeSystemVipCoachVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface OneFeeSystemVipCoachMapStruct extends BaseMapStruct<OneFeeSystemVipCoachVo, OneFeeSystemVipCoachEntity, OneFeeSystemVipCoachEditParam, OneFeeSystemVipCoachPageQueryParam >{

}
