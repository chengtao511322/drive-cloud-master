package com.drive.bbs.service.mapstruct;

import com.drive.bbs.pojo.entity.*;
import com.drive.bbs.pojo.vo.*;
import com.drive.bbs.pojo.dto.*;
import org.mapstruct.Mapper;
import com.drive.common.core.base.BaseMapStruct;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface UserLikeMapStruct extends BaseMapStruct<UserLikeVo, UserLikeEntity, UserLikeEditParam, UserLikePageQueryParam >{

}
