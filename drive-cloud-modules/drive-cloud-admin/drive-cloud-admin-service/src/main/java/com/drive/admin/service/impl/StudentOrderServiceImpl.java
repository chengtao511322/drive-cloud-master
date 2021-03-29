package com.drive.admin.service.impl;

import com.drive.admin.mapper.StudentOrderMapper;
import com.drive.admin.pojo.entity.StudentOrderEntity;
import com.drive.admin.service.StudentOrderService;
import com.drive.common.core.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * 学员订单表 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class StudentOrderServiceImpl extends BaseService<StudentOrderMapper, StudentOrderEntity> implements StudentOrderService {

}

