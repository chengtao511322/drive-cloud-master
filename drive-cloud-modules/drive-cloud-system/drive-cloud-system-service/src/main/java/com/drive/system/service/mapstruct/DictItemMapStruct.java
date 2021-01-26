package com.drive.system.service.mapstruct;

import com.drive.common.core.base.BaseMapStruct;
import com.drive.system.pojo.dto.DictItemEditParam;
import com.drive.system.pojo.dto.DictItemPageQueryParam;
import com.drive.system.pojo.entity.DictItemEntity;
import com.drive.system.pojo.vo.DictItemVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface DictItemMapStruct extends BaseMapStruct<DictItemVo, DictItemEntity, DictItemEditParam, DictItemPageQueryParam >{

}
