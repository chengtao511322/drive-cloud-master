package com.drive.admin.repository;

import com.drive.admin.pojo.dto.DriveSchoolEditParam;
import com.drive.admin.pojo.dto.DriveSchoolPageQueryParam;
import com.drive.common.core.base.BaseRepository;

/**
 *
 * 平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。 服务类
 *
 * @author xiaoguo
 */
public interface DriveSchoolRepository extends BaseRepository<DriveSchoolPageQueryParam, DriveSchoolEditParam>{
}

