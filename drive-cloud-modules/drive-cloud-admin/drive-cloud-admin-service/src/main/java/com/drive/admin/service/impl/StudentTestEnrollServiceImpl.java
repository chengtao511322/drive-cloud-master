package com.drive.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.drive.admin.mapper.StudentTestEnrollMapper;
import com.drive.admin.pojo.entity.StudentTestEnrollEntity;
import com.drive.admin.service.StudentTestEnrollService;
import com.drive.common.core.base.BaseService;
import com.drive.common.datascope.annotation.DataScope;
import org.springframework.stereotype.Service;

/**
 * 学员考试报名表 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class StudentTestEnrollServiceImpl extends BaseService<StudentTestEnrollMapper, StudentTestEnrollEntity> implements StudentTestEnrollService {

    @DataScope(deptAlias = "operator_id",userAlias = "user_id",module = "admin")
    @Override
    public <E extends IPage<StudentTestEnrollEntity>> E page(E page, Wrapper<StudentTestEnrollEntity> queryWrapper) {
        return super.page(page,queryWrapper);
    }
}

