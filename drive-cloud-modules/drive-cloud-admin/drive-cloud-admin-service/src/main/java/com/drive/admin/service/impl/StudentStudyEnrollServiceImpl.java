package com.drive.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.mapper.StudentStudyEnrollMapper;
import com.drive.admin.pojo.dto.StudentStudyEnrollPageQueryParam;
import com.drive.admin.pojo.entity.StudentStudyEnrollEntity;
import com.drive.admin.pojo.vo.StudentStudyEnrollVo;
import com.drive.admin.service.StudentStudyEnrollService;
import com.drive.common.core.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public IPage<StudentStudyEnrollVo> studyEnrollPageList(Page page, Wrapper<StudentStudyEnrollPageQueryParam> ew) {
        return this.getBaseMapper().studyEnrollPageList(page,ew);
    }

    @Override
    public List<StudentStudyEnrollVo> studyEnrollList(Wrapper<StudentStudyEnrollPageQueryParam> ew) {
        return this.getBaseMapper().studyEnrollList(ew);
    }
}

