package com.drive.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.admin.pojo.entity.StudentTrainCarApplyEntity;

/**
 *
 * 学员学车预约表 服务类
 *
 * @author xiaoguo
 */
public interface StudentTrainCarApplyService extends IService<StudentTrainCarApplyEntity>{


    /**
     * 课时求和
     * @param studentId
     * @param subjectType
     * @return
     */
    Integer classHoursSum(String studentId,String subjectType);
}

