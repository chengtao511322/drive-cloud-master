package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.CoachingGridEditParam;
import com.drive.admin.pojo.dto.CoachingGridPageQueryParam;
import com.drive.admin.pojo.entity.CoachingGridEntity;
import com.drive.admin.pojo.vo.CoachingGridVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface CoachingGridMapStruct extends BaseMapStruct<CoachingGridVo, CoachingGridEntity, CoachingGridEditParam, CoachingGridPageQueryParam >{

}
