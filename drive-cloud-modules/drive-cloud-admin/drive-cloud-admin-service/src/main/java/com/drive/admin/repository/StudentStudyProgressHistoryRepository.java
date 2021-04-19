package com.drive.admin.repository;

import com.drive.admin.pojo.dto.*;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.base.BasicsRepository;
import com.drive.common.core.biz.ResObject;

/**
 *
 * 学员学车报名单 服务类
 *
 * @author xiaoguo
 */
public interface StudentStudyProgressHistoryRepository extends BasicsRepository<StudentStudyProgressHistoryPageQueryParam, StudentStudyProgressHistoryEditParam,StudentStudyProgressHistoryInstallParam> {


    /**
     * 新增
     * @param studentStudyProgressHistoryInstallParam
     * @return
     */
    ResObject addStudyProgressHisstory(StudentStudyProgressHistoryInstallParam studentStudyProgressHistoryInstallParam);
}

