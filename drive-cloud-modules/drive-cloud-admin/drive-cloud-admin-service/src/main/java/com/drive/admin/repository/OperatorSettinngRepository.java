package com.drive.admin.repository;

import com.drive.admin.pojo.dto.*;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.base.BasicsRepository;
import com.drive.common.core.biz.ResObject;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * 运营商参数设置表 服务类
 *
 * @author xiaoguo
 */
public interface OperatorSettinngRepository extends BasicsRepository<OperatorSettinngPageQueryParam, OperatorSettinngEditParam,OperatorSettinngInstallParam>{
}

