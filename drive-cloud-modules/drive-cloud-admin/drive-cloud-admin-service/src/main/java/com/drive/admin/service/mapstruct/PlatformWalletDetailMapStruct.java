package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.PlatformWalletDetailEditParam;
import com.drive.admin.pojo.dto.PlatformWalletDetailPageQueryParam;
import com.drive.admin.pojo.entity.PlatformWalletDetailEntity;
import com.drive.admin.pojo.vo.PlatformWalletDetailVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface PlatformWalletDetailMapStruct extends BaseMapStruct<PlatformWalletDetailVo, PlatformWalletDetailEntity, PlatformWalletDetailEditParam, PlatformWalletDetailPageQueryParam >{

}
