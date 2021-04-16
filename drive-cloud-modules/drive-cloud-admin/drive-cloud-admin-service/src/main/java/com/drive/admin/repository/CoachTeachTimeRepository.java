package com.drive.admin.repository;

import com.drive.admin.pojo.dto.CoachTeachTimeEditParam;
import com.drive.admin.pojo.dto.CoachTeachTimeInstallParam;
import com.drive.admin.pojo.dto.CoachTeachTimePageQueryParam;
import com.drive.common.core.base.BasicsRepository;

/**
 *
 * 教练课程时间表 服务类
 *
 * @author guyi
 */
public interface CoachTeachTimeRepository extends BasicsRepository<CoachTeachTimePageQueryParam, CoachTeachTimeEditParam,CoachTeachTimeInstallParam> {
}

