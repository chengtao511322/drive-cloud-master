package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.CoachGiveAreaEditParam;
import com.drive.admin.pojo.dto.CoachGiveAreaPageQueryParam;
import com.drive.admin.pojo.entity.CoachGiveAreaEntity;
import com.drive.admin.pojo.vo.CoachGiveAreaVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface CoachGiveAreaMapStruct extends BaseMapStruct<CoachGiveAreaVo, CoachGiveAreaEntity, CoachGiveAreaEditParam, CoachGiveAreaPageQueryParam >{

}
