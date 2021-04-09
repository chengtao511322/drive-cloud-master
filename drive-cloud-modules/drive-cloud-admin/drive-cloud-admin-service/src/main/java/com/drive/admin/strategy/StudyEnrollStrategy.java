package com.drive.admin.strategy;

import com.drive.admin.pojo.dto.CompleteStudyEnrollParam;
import com.drive.common.core.biz.ResObject;

/**
 * @Author 小郭
 * @Description //完善报名策略
 * @Date $ $
 * @Param $
 * @return $
 **/
public interface StudyEnrollStrategy {

    /**
     * 完善报名信息
     * @param studentStudyEnrollEditParam
     * @return
     */
    ResObject completeStudyEnroll(CompleteStudyEnrollParam studentStudyEnrollEditParam);

    /**
     * 完善考试报名
     * @param studentStudyEnrollEditParam
     * @return
     */
    ResObject completeExamEnroll(CompleteStudyEnrollParam studentStudyEnrollEditParam);
}
