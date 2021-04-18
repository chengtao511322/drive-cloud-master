package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.EvaluateTagAppraiseEditParam;
import com.drive.admin.pojo.dto.EvaluateTagAppraisePageQueryParam;
import com.drive.admin.pojo.entity.EvaluateTagAppraiseEntity;
import com.drive.admin.pojo.vo.EvaluateTagAppraiseVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface EvaluateTagAppraiseMapStruct extends BaseMapStruct<EvaluateTagAppraiseVo, EvaluateTagAppraiseEntity, EvaluateTagAppraiseEditParam, EvaluateTagAppraisePageQueryParam >{

}
