package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.SchoolUserEditParam;
import com.drive.admin.pojo.dto.SchoolUserPageQueryParam;
import com.drive.admin.pojo.entity.SchoolUserEntity;
import com.drive.admin.pojo.vo.SchoolUserVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface SchoolUserMapStruct extends BaseMapStruct<SchoolUserVo, SchoolUserEntity, SchoolUserEditParam, SchoolUserPageQueryParam >{

}
