package com.drive.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.drive.admin.pojo.dto.StudentTrainCarApplyPageQueryParam;
import com.drive.admin.pojo.entity.StudentTrainCarApplyEntity;

/**
 * 学员学车预约表 Mapper 接口
 *
 * @author xiaoguo
 */
public interface StudentTrainCarApplyMapper extends BaseMapper<StudentTrainCarApplyEntity> {

    /**
     * 课时求和
     * @param studentId
     * @param subjectType
     * @return
     */
    Integer classHoursSum(String studentId,String subjectType);

    /**
     * 课时求和多条件
     * @param studentTrainCarApplyPageQueryParam
     * @return
     */
    int getClassHoursSum(StudentTrainCarApplyPageQueryParam studentTrainCarApplyPageQueryParam);

    /**
     * 查询课时
     * @param studentTrainCarApplyPageQueryParam
     * @return
     */
    int selectVipTeachingNumber(StudentTrainCarApplyPageQueryParam studentTrainCarApplyPageQueryParam);
}

