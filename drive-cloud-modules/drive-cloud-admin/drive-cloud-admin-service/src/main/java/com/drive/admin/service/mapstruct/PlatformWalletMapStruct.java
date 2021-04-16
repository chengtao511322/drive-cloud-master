package com.drive.admin.service.mapstruct;

import com.drive.admin.pojo.dto.PlatformWalletEditParam;
import com.drive.admin.pojo.dto.PlatformWalletPageQueryParam;
import com.drive.admin.pojo.entity.PlatformWalletEntity;
import com.drive.admin.pojo.vo.PlatformWalletVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface PlatformWalletMapStruct extends BaseMapStruct<PlatformWalletVo, PlatformWalletEntity, PlatformWalletEditParam, PlatformWalletPageQueryParam >{

}
