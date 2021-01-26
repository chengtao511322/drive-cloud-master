package com.drive.basics.service.mapstruct;

import com.drive.basics.pojo.dto.CategoryEditParam;
import com.drive.basics.pojo.dto.CategoryPageQueryParam;
import com.drive.basics.pojo.entity.CategoryEntity;
import com.drive.basics.pojo.vo.CategoryVo;
import com.drive.common.core.base.BaseMapStruct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface CategoryMapStruct extends BaseMapStruct<CategoryVo, CategoryEntity, CategoryEditParam, CategoryPageQueryParam >{

}
