package com.drive.basics.service.mapstruct;

import com.drive.basics.pojo.dto.BannerEditParam;
import com.drive.basics.pojo.dto.BannerPageQueryParam;
import com.drive.basics.pojo.entity.BannerEntity;
import com.drive.basics.pojo.vo.BannerVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface BannerMapStruct extends BaseMapStruct<BannerVo, BannerEntity, BannerEditParam, BannerPageQueryParam >{

}
