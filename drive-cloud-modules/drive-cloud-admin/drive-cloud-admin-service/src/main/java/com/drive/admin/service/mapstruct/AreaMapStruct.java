package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.AreaEditParam;
import com.drive.admin.pojo.dto.AreaPageQueryParam;
import com.drive.admin.pojo.entity.AreaEntity;
import com.drive.admin.pojo.vo.AreaVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface AreaMapStruct extends BaseMapStruct<AreaVo, AreaEntity, AreaEditParam, AreaPageQueryParam >{

}
