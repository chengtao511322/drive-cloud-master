package com.drive.admin.service.impl;

import com.drive.admin.mapper.ServiceInfoMapper;
import com.drive.admin.service.ServiceInfoService;
import com.drive.common.core.base.BaseService;
import com.drive.admin.pojo.entity.ServiceInfoEntity;

import org.springframework.stereotype.Service;

/**
 * 客服人员信息表 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class ServiceInfoServiceImpl extends BaseService<ServiceInfoMapper, ServiceInfoEntity> implements ServiceInfoService {

}

