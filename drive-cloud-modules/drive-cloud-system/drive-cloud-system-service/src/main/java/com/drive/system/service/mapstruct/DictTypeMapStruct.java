package com.drive.system.service.mapstruct;

import com.drive.common.core.base.BaseMapStruct;
import com.drive.system.pojo.dto.DictTypeEditParam;
import com.drive.system.pojo.dto.DictTypePageQueryParam;
import com.drive.system.pojo.entity.DictTypeEntity;
import com.drive.system.pojo.vo.DictTypeVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface DictTypeMapStruct extends BaseMapStruct<DictTypeVo, DictTypeEntity, DictTypeEditParam, DictTypePageQueryParam >{

}
