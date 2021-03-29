package com.drive.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.drive.admin.pojo.entity.StudentStudyEnrollEntity;

/**
 * 学员学车报名单 Mapper 接口
 *
 * @author xiaoguo
 */
public interface StudentStudyEnrollMapper extends BaseMapper<StudentStudyEnrollEntity> {

    /**
     * 通过报名单号修改数据
     * @param studentStudyEnrollEntity
     * @return
     */
    int updateByEnrollNo(StudentStudyEnrollEntity studentStudyEnrollEntity);
}

