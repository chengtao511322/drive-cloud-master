package com.drive.system.service.mapstruct;

import com.drive.common.core.base.BaseMapStruct;
import com.drive.system.pojo.dto.PostEditParam;
import com.drive.system.pojo.dto.PostPageQueryParam;
import com.drive.system.pojo.entity.PostEntity;
import com.drive.system.pojo.vo.PostVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface PostMapStruct extends BaseMapStruct<PostVo, PostEntity, PostEditParam, PostPageQueryParam >{

}
