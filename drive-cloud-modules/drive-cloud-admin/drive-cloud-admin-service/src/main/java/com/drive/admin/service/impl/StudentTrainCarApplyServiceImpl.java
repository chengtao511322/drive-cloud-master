package com.drive.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.mapper.StudentTrainCarApplyMapper;
import com.drive.admin.pojo.dto.StudentTrainCarApplyPageQueryParam;
import com.drive.admin.pojo.entity.StudentTrainCarApplyEntity;
import com.drive.admin.service.StudentTrainCarApplyService;
import com.drive.common.core.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * 学员学车预约表 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class StudentTrainCarApplyServiceImpl extends BaseService<StudentTrainCarApplyMapper, StudentTrainCarApplyEntity> implements StudentTrainCarApplyService {

    @Override
    public Integer classHoursSum(String studentId, String subjectType) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("student_id",studentId);
        queryWrapper.eq("subject_type",subjectType);
        queryWrapper.in("apply_status",1,4,5,6,7);
        return this.getBaseMapper().selectCount(queryWrapper);
    }

    @Override
    public int getClassHoursSum(StudentTrainCarApplyPageQueryParam studentTrainCarApplyPageQueryParam) {
        return this.getBaseMapper().getClassHoursSum(studentTrainCarApplyPageQueryParam);
    }

    @Override
    public int selectVipTeachingNumber(StudentTrainCarApplyPageQueryParam studentTrainCarApplyPageQueryParam) {
        return this.getBaseMapper().selectVipTeachingNumber(studentTrainCarApplyPageQueryParam);
    }
}

