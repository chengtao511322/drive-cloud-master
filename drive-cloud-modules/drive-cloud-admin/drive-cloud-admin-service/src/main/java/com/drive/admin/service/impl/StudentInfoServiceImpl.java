package com.drive.admin.service.impl;

import com.drive.admin.mapper.StudentInfoMapper;
import com.drive.admin.pojo.entity.StudentInfoEntity;
import com.drive.admin.service.StudentInfoService;
import com.drive.common.core.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * 学员信息表 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class StudentInfoServiceImpl extends BaseService<StudentInfoMapper, StudentInfoEntity> implements StudentInfoService {

}

