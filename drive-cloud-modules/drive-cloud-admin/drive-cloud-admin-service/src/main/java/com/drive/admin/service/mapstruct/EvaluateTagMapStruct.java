package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.EvaluateTagEditParam;
import com.drive.admin.pojo.dto.EvaluateTagPageQueryParam;
import com.drive.admin.pojo.entity.EvaluateTagEntity;
import com.drive.admin.pojo.vo.EvaluateTagVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface EvaluateTagMapStruct extends BaseMapStruct<EvaluateTagVo, EvaluateTagEntity, EvaluateTagEditParam, EvaluateTagPageQueryParam >{

}
