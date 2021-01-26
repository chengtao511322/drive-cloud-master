package com.drive.basics.service.impl;

import com.drive.basics.mapper.AppVersionMapper;
import com.drive.basics.pojo.entity.AppVersionEntity;
import com.drive.basics.service.AppVersionService;
import com.drive.common.core.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * app版本控制信息表 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class AppVersionServiceImpl extends BaseService<AppVersionMapper, AppVersionEntity> implements AppVersionService {

}

