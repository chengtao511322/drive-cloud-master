package com.drive.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.mapper.StudentInfoMapper;
import com.drive.admin.pojo.dto.StudentInfoPageQueryParam;
import com.drive.admin.pojo.entity.StudentInfoEntity;
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.admin.service.StudentInfoService;
import com.drive.common.core.base.BaseService;
import com.drive.common.datascope.annotation.DataScope;
import org.springframework.stereotype.Service;

/**
 * 学员信息表 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class StudentInfoServiceImpl extends BaseService<StudentInfoMapper, StudentInfoEntity> implements StudentInfoService {

    @DataScope(deptAlias = "tsi.operator_id",userAlias = "tsi.service_id", module = "admin")
    @Override
    public IPage<StudentInfoVo> newStudentPageList(Page page, Wrapper<StudentInfoPageQueryParam> ew) {
        return this.getBaseMapper().newStudentPageList(page,ew);
    }

    @DataScope(deptAlias = "tsi.operator_id",userAlias = "tsi.service_id", module = "admin")
    @Override
    public IPage<StudentInfoVo> newStudentReturnVisitPageList(Page page, Wrapper<StudentInfoPageQueryParam> ew) {
        return this.getBaseMapper().newStudentReturnVisitPageList(page,ew);
    }

    @Override
    public IPage<StudentInfoVo> oneNewStudentReturnVisitPageList(Page page, Wrapper<StudentInfoPageQueryParam> ew) {
        return this.getBaseMapper().oneNewStudentReturnVisitPageList(page,ew);
    }
}

