package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.RecommendUserEditParam;
import com.drive.admin.pojo.dto.RecommendUserPageQueryParam;
import com.drive.admin.pojo.entity.RecommendUserEntity;
import com.drive.admin.pojo.vo.RecommendUserVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface RecommendUserMapStruct extends BaseMapStruct<RecommendUserVo, RecommendUserEntity, RecommendUserEditParam, RecommendUserPageQueryParam >{

}
