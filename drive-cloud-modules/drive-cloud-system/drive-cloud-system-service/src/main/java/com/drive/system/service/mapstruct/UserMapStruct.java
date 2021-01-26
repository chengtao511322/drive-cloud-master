package com.drive.system.service.mapstruct;

import com.drive.common.core.base.BaseMapStruct;
import com.drive.system.pojo.dto.UserEditParam;
import com.drive.system.pojo.dto.UserPageQueryParam;
import com.drive.system.pojo.entity.UserEntity;
import com.drive.system.pojo.vo.UserVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface UserMapStruct extends BaseMapStruct<UserVo, UserEntity, UserEditParam, UserPageQueryParam >{

}
