package com.drive.admin.service.impl;

import com.drive.admin.mapper.DriveSchoolMapper;
import com.drive.admin.service.DriveSchoolService;
import com.drive.common.core.base.BaseService;
import com.drive.admin.pojo.entity.DriveSchoolEntity;

import org.springframework.stereotype.Service;

/**
 * 平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class DriveSchoolServiceImpl extends BaseService<DriveSchoolMapper, DriveSchoolEntity> implements DriveSchoolService {

}

