package com.drive.admin.repository;

import com.drive.admin.pojo.dto.StudentTrainCarApplyEditParam;
import com.drive.admin.pojo.dto.StudentTrainCarApplyInstallParam;
import com.drive.admin.pojo.dto.StudentTrainCarApplyPageQueryParam;
import com.drive.common.core.base.BasicsRepository;

/**
 *
 * 学员学车预约表 服务类
 *
 * @author xiaoguo
 */
public interface StudentTrainCarApplyRepository extends BasicsRepository<StudentTrainCarApplyPageQueryParam, StudentTrainCarApplyEditParam,StudentTrainCarApplyInstallParam> {
}

