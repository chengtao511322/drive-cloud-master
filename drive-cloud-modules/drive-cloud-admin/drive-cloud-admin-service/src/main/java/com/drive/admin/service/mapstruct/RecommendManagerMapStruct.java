package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.RecommendManagerEditParam;
import com.drive.admin.pojo.dto.RecommendManagerPageQueryParam;
import com.drive.admin.pojo.entity.RecommendManagerEntity;
import com.drive.admin.pojo.vo.RecommendManagerVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface RecommendManagerMapStruct extends BaseMapStruct<RecommendManagerVo, RecommendManagerEntity, RecommendManagerEditParam, RecommendManagerPageQueryParam >{

}
