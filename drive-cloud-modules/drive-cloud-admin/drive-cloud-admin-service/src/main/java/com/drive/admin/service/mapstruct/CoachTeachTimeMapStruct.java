package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.CoachTeachTimeEditParam;
import com.drive.admin.pojo.dto.CoachTeachTimePageQueryParam;
import com.drive.admin.pojo.entity.CoachTeachTimeEntity;
import com.drive.admin.pojo.vo.CoachTeachTimeVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface CoachTeachTimeMapStruct extends BaseMapStruct<CoachTeachTimeVo, CoachTeachTimeEntity, CoachTeachTimeEditParam, CoachTeachTimePageQueryParam >{

}
