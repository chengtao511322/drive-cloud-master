package com.drive.admin.repository;

import com.drive.admin.pojo.dto.StudentCoachAppraiseEditParam;
import com.drive.admin.pojo.dto.StudentCoachAppraiseInstallParam;
import com.drive.admin.pojo.dto.StudentCoachAppraisePageQueryParam;
import com.drive.common.core.base.BasicsRepository;

/**
 *
 * 学员教练互评表 服务类
 *
 * @author xiaoguo
 */
public interface StudentCoachAppraiseRepository extends BasicsRepository<StudentCoachAppraisePageQueryParam, StudentCoachAppraiseEditParam,StudentCoachAppraiseInstallParam> {
}

