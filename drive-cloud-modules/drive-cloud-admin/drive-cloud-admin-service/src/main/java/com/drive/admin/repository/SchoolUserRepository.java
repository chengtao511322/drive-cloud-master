package com.drive.admin.repository;

import com.drive.admin.pojo.dto.SchoolUserEditParam;
import com.drive.admin.pojo.dto.SchoolUserInstallParam;
import com.drive.admin.pojo.dto.SchoolUserPageQueryParam;
import com.drive.common.core.base.BasicsRepository;

/**
 *
 * 合作驾校用户 服务类
 *
 * @author xiaoguo
 */
public interface SchoolUserRepository extends BasicsRepository<SchoolUserPageQueryParam, SchoolUserEditParam,SchoolUserInstallParam> {
}

