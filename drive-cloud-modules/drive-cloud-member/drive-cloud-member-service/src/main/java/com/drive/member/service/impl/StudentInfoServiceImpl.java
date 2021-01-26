package com.drive.member.service.impl;

import com.drive.common.core.base.BaseService;
import com.drive.member.mapper.StudentInfoMapper;
import com.drive.member.pojo.entity.StudentInfoEntity;
import com.drive.member.service.StudentInfoService;
import org.springframework.stereotype.Service;

/**
 * 学员信息表 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class StudentInfoServiceImpl extends BaseService<StudentInfoMapper, StudentInfoEntity> implements StudentInfoService {

}

