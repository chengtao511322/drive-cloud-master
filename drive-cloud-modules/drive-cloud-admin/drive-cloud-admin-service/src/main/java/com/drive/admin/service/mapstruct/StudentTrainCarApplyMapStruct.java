package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.StudentTrainCarApplyEditParam;
import com.drive.admin.pojo.dto.StudentTrainCarApplyPageQueryParam;
import com.drive.admin.pojo.entity.StudentTrainCarApplyEntity;
import com.drive.admin.pojo.vo.StudentTrainCarApplyVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface StudentTrainCarApplyMapStruct extends BaseMapStruct<StudentTrainCarApplyVo, StudentTrainCarApplyEntity, StudentTrainCarApplyEditParam, StudentTrainCarApplyPageQueryParam >{

}
