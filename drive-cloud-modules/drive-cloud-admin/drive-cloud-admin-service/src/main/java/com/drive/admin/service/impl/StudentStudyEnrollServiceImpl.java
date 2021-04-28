package com.drive.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.mapper.StudentStudyEnrollMapper;
import com.drive.admin.pojo.dto.StudentStudyEnrollPageQueryParam;
import com.drive.admin.pojo.dto.StudyCarScheduleQueryParam;
import com.drive.admin.pojo.entity.StudentStudyEnrollEntity;
import com.drive.admin.pojo.vo.StatisticsStudentDataVo;
import com.drive.admin.pojo.vo.StudentStudyEnrollVo;
import com.drive.admin.pojo.vo.StudyCarScheduleVo;
import com.drive.admin.service.StudentStudyEnrollService;
import com.drive.common.core.base.BaseService;
import com.drive.common.core.enums.DataScopeEnum;
import com.drive.common.datascope.annotation.DataScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 学员学车报名单 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class StudentStudyEnrollServiceImpl extends BaseService<StudentStudyEnrollMapper, StudentStudyEnrollEntity> implements StudentStudyEnrollService {

    @Autowired
    private StudentStudyEnrollMapper studentStudyEnrollMapper;

    @DataScope(deptAlias = "operator_id", userAlias = "user_id",module = "admin")
    @Override
    public <E extends IPage<StudentStudyEnrollEntity>> E page(E page, Wrapper<StudentStudyEnrollEntity> queryWrapper) {
        return super.page(page,queryWrapper);
    }

    @Override
    public Boolean updateByEnrollNo(StudentStudyEnrollEntity studentStudyEnrollEntity) {
        return null;
    }

    @DataScope(deptAlias = "t1.operator_id", module = "admin")
    @Override
    public IPage<StudentStudyEnrollVo> studyEnrollPageList(Page page, Wrapper<StudentStudyEnrollPageQueryParam> ew) {
        return this.getBaseMapper().studyEnrollPageList(page,ew);
    }

    @Override
    public List<StudentStudyEnrollVo> studyEnrollList(Wrapper<StudentStudyEnrollPageQueryParam> ew) {
        return this.getBaseMapper().studyEnrollList(ew);
    }
    @DataScope(deptAlias = "t1.operator_id", userAlias = "t1.user_id",module = "admin")
    @Override
    public IPage<StatisticsStudentDataVo> statisticsStudentDataPageList(Page page, Wrapper<StudentStudyEnrollPageQueryParam> ew) {
        return this.getBaseMapper().statisticsStudentDataPageList(page,ew);
    }

    @DataScope(deptAlias = "tsse.operator_id",userAlias = "tsse.user_id", module = "admin")
    @Override
    public IPage<StudyCarScheduleVo> studyCarSchedulePageList(Page page, Wrapper<StudyCarScheduleQueryParam> ew) {
        return this.getBaseMapper().studyCarSchedulePageList(page,ew);
    }
}

