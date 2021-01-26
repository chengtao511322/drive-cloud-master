package com.drive.system.service.mapstruct;

import com.drive.common.core.base.BaseMapStruct;
import com.drive.system.pojo.dto.RoleEditParam;
import com.drive.system.pojo.dto.RolePageQueryParam;
import com.drive.system.pojo.entity.RoleEntity;
import com.drive.system.pojo.vo.RoleVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface RoleMapStruct extends BaseMapStruct<RoleVo, RoleEntity, RoleEditParam, RolePageQueryParam >{

}
