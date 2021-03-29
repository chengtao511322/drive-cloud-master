package com.drive.admin.service.impl;

import com.drive.admin.mapper.AppVersionMapper;
import com.drive.admin.pojo.entity.AppVersionEntity;
import com.drive.admin.service.AppVersionService;
import com.drive.common.core.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * 平台应用版本表 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class AppVersionServiceImpl extends BaseService<AppVersionMapper, AppVersionEntity> implements AppVersionService {

}

