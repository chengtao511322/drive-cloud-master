package com.drive.admin.repository;

import com.drive.admin.pojo.dto.CompleteStudyEnrollParam;
import com.drive.admin.pojo.dto.StudentTestEnrollEditParam;
import com.drive.admin.pojo.dto.StudentTestEnrollPageQueryParam;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.biz.ResObject;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 *
 * 学员考试报名表 服务类
 *
 * @author xiaoguo
 */
public interface StudentTestEnrollRepository extends BaseRepository<StudentTestEnrollPageQueryParam, StudentTestEnrollEditParam>{


    /**
     * 未预约科目一考试
     * @param studentTestEnrollPageQueryParam
     * @return
     */
    ResObject noSubscribeSubjectOneExamPageList(StudentTestEnrollPageQueryParam studentTestEnrollPageQueryParam);

    /**
     * 查看通过率
     * @param studentId
     * @return
     */
    ResObject findPassingList(String studentId);

    /**
     * 查看练车通过率
     * @param studentId
     * @return
     */
    ResObject getDrivingPassing(String studentId);

    /**
     * 未预约科目二考试
     * @param studentTestEnrollPageQueryParam
     * @return
     */
    ResObject noSubscribeSubjectTwoExamPageList(StudentTestEnrollPageQueryParam studentTestEnrollPageQueryParam);

    /**
     * 未预约科目三考试
     * @param studentTestEnrollPageQueryParam
     * @return
     */
    ResObject noSubscribeSubjectThreeExamPageList(StudentTestEnrollPageQueryParam studentTestEnrollPageQueryParam);

    /**
     * 未预约科目四考试
     * @param studentTestEnrollPageQueryParam
     * @return
     */
    ResObject noSubscribeSubjectFourExamPageList(StudentTestEnrollPageQueryParam studentTestEnrollPageQueryParam);

    /**
     * 考试中
     * @param studentTestEnrollPageQueryParam
     * @return
     */
    ResObject examLoadingPageList(StudentTestEnrollPageQueryParam studentTestEnrollPageQueryParam);


    /**
     * 完善考试
     * @param completeStudyEnrollParam
     * @return
     */
    ResObject completeExamEnroll(CompleteStudyEnrollParam completeStudyEnrollParam);
}

