package com.drive.member.service.mapstruct;

import com.drive.common.core.base.BaseMapStruct;
import com.drive.member.pojo.dto.StudentInfoEditParam;
import com.drive.member.pojo.dto.StudentInfoPageQueryParam;
import com.drive.member.pojo.entity.StudentInfoEntity;
import com.drive.member.pojo.vo.StudentInfoVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface StudentInfoMapStruct extends BaseMapStruct<StudentInfoVo, StudentInfoEntity, StudentInfoEditParam, StudentInfoPageQueryParam >{

}
