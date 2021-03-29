package com.drive.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.mapper.StudentInfoMapper;
import com.drive.admin.pojo.dto.StudentInfoPageQueryParam;
import com.drive.admin.pojo.entity.StudentInfoEntity;
import com.drive.admin.service.StudentInfoService;
import com.drive.common.core.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学员信息表 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class StudentInfoServiceImpl extends BaseService<StudentInfoMapper, StudentInfoEntity> implements StudentInfoService {

    @Override
    public IPage newStudentPageList(Page page, Wrapper<StudentInfoPageQueryParam> ew) {
        return this.getBaseMapper().newStudentPageList(page,ew);
    }
}

