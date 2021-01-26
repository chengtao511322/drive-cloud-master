package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.CoachInfoEditParam;
import com.drive.admin.pojo.dto.CoachInfoPageQueryParam;
import com.drive.admin.pojo.entity.CoachInfoEntity;
import com.drive.admin.pojo.vo.CoachInfoVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface CoachInfoMapStruct extends BaseMapStruct<CoachInfoVo, CoachInfoEntity, CoachInfoEditParam, CoachInfoPageQueryParam >{

}
