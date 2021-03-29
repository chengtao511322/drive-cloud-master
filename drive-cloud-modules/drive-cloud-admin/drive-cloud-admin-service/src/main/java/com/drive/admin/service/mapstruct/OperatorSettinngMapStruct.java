package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.OperatorSettinngEditParam;
import com.drive.admin.pojo.dto.OperatorSettinngPageQueryParam;
import com.drive.admin.pojo.entity.OperatorSettinngEntity;
import com.drive.admin.pojo.vo.OperatorSettinngVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface OperatorSettinngMapStruct extends BaseMapStruct<OperatorSettinngVo, OperatorSettinngEntity, OperatorSettinngEditParam, OperatorSettinngPageQueryParam >{

}
