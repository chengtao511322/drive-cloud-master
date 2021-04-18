package com.drive.admin.repository;

import com.drive.admin.pojo.dto.EvaluateTagAppraiseEditParam;
import com.drive.admin.pojo.dto.EvaluateTagAppraiseInstallParam;
import com.drive.admin.pojo.dto.EvaluateTagAppraisePageQueryParam;
import com.drive.common.core.base.BasicsRepository;

/**
 *
 * 学员教练评价明细表 服务类
 *
 * @author xiaoguo
 */
public interface EvaluateTagAppraiseRepository extends BasicsRepository<EvaluateTagAppraisePageQueryParam, EvaluateTagAppraiseEditParam,EvaluateTagAppraiseInstallParam> {
}

