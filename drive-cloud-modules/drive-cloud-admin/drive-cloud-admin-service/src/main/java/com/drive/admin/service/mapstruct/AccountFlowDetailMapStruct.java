package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.AccountFlowDetailEditParam;
import com.drive.admin.pojo.dto.AccountFlowDetailPageQueryParam;
import com.drive.admin.pojo.entity.AccountFlowDetailEntity;
import com.drive.admin.pojo.vo.AccountFlowDetailVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface AccountFlowDetailMapStruct extends BaseMapStruct<AccountFlowDetailVo, AccountFlowDetailEntity, AccountFlowDetailEditParam, AccountFlowDetailPageQueryParam >{

}
