package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.StudentStudyProgressHistoryEditParam;
import com.drive.admin.pojo.dto.StudentStudyProgressHistoryPageQueryParam;
import com.drive.admin.pojo.entity.StudentStudyProgressHistoryEntity;
import com.drive.admin.pojo.vo.StudentStudyProgressHistoryVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface StudentStudyProgressHistoryMapStruct extends BaseMapStruct<StudentStudyProgressHistoryVo, StudentStudyProgressHistoryEntity, StudentStudyProgressHistoryEditParam, StudentStudyProgressHistoryPageQueryParam >{

}
