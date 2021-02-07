package com.drive.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.admin.pojo.entity.StudentStudyEnrollEntity;

/**
 *
 * 学员学车报名单 服务类
 *
 * @author xiaoguo
 */
public interface StudentStudyEnrollService extends IService<StudentStudyEnrollEntity>{

    /**
     * 通过报名单号修改数据
     * @param studentStudyEnrollEntity
     * @return
     */
    Boolean updateByEnrollNo(StudentStudyEnrollEntity studentStudyEnrollEntity);
}

