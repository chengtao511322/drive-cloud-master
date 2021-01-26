package com.drive.system.service.mapstruct;

import com.drive.common.core.base.BaseMapStruct;
import com.drive.system.pojo.dto.MenuEditParam;
import com.drive.system.pojo.dto.MenuPageQueryParam;
import com.drive.system.pojo.entity.MenuEntity;
import com.drive.system.pojo.vo.MenuVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface MenuMapStruct extends BaseMapStruct<MenuVo, MenuEntity, MenuEditParam, MenuPageQueryParam >{

}
