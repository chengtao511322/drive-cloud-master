package com.drive.system.service.mapstruct;

import com.drive.common.core.base.BaseMapStruct;
import com.drive.system.pojo.dto.DeptEditParam;
import com.drive.system.pojo.dto.DeptPageQueryParam;
import com.drive.system.pojo.entity.DeptEntity;
import com.drive.system.pojo.vo.DeptVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface DeptMapStruct extends BaseMapStruct<DeptVo, DeptEntity, DeptEditParam, DeptPageQueryParam >{

}
