package com.drive.pay.service.mapstruct;

import com.drive.common.core.base.BaseMapStruct;
import com.drive.pay.pojo.dto.MentTransactionEditParam;
import com.drive.pay.pojo.dto.MentTransactionPageQueryParam;
import com.drive.pay.pojo.entity.MentTransactionEntity;
import com.drive.pay.pojo.vo.MentTransactionVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@SuppressWarnings("all")
@Component
@Mapper(componentModel = "spring")
public interface MentTransactionMapStruct extends BaseMapStruct<MentTransactionVo, MentTransactionEntity, MentTransactionEditParam, MentTransactionPageQueryParam >{

}
