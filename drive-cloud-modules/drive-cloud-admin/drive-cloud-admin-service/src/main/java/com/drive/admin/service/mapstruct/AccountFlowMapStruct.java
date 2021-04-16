package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.AccountFlowEditParam;
import com.drive.admin.pojo.dto.AccountFlowPageQueryParam;
import com.drive.admin.pojo.entity.AccountFlowEntity;
import com.drive.admin.pojo.vo.AccountFlowVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface AccountFlowMapStruct extends BaseMapStruct<AccountFlowVo, AccountFlowEntity, AccountFlowEditParam, AccountFlowPageQueryParam >{

}
