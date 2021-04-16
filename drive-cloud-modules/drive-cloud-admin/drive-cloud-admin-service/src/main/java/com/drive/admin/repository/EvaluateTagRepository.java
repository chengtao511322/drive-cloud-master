package com.drive.admin.repository;

import com.drive.admin.pojo.dto.EvaluateTagEditParam;
import com.drive.admin.pojo.dto.EvaluateTagInstallParam;
import com.drive.admin.pojo.dto.EvaluateTagPageQueryParam;
import com.drive.common.core.base.BasicsRepository;

/**
 *
 * 评价标签表 服务类
 *
 * @author xiaoguo
 */
public interface EvaluateTagRepository extends BasicsRepository<EvaluateTagPageQueryParam, EvaluateTagEditParam,EvaluateTagInstallParam> {
}

