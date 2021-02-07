package com.drive.admin.service.impl;

import com.drive.admin.mapper.StudentStudyEnrollMapper;
import com.drive.admin.service.StudentStudyEnrollService;
import com.drive.common.core.base.BaseService;
import com.drive.admin.pojo.entity.StudentStudyEnrollEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学员学车报名单 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class StudentStudyEnrollServiceImpl extends BaseService<StudentStudyEnrollMapper, StudentStudyEnrollEntity> implements StudentStudyEnrollService {

    @Autowired
    private StudentStudyEnrollMapper studentStudyEnrollMapper;
    @Override
    public Boolean updateByEnrollNo(StudentStudyEnrollEntity studentStudyEnrollEntity) {
        return null;
    }
}

