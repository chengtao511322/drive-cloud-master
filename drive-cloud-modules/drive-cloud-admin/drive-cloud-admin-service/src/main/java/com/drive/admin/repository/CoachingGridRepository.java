package com.drive.admin.repository;

import com.drive.admin.pojo.dto.CoachingGridEditParam;
import com.drive.admin.pojo.dto.CoachingGridInstallParam;
import com.drive.admin.pojo.dto.CoachingGridPageQueryParam;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.base.BasicsRepository;

/**
 *
 * 平台训练场地表 服务类
 *
 * @author xiaoguo
 */
public interface CoachingGridRepository extends BasicsRepository<CoachingGridPageQueryParam, CoachingGridEditParam, CoachingGridInstallParam> {
}

