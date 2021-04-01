package com.drive.admin.repository;

import com.drive.admin.pojo.dto.CompleteStudyEnrollParam;
import com.drive.admin.pojo.dto.StudentStudyEnrollEditParam;
import com.drive.admin.pojo.dto.StudentStudyEnrollPageQueryParam;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.exception.BizException;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 *
 * 学员学车报名单 服务类
 *
 * @author xiaoguo
 */
public interface StudentStudyEnrollRepository extends BaseRepository<StudentStudyEnrollPageQueryParam, StudentStudyEnrollEditParam>{

    /**
     *通过条件查询出一条数据
     * @param studentStudyEnrollPageQueryParam
     * @return
     */
    ResObject getStudentStudyEnrollInfo(StudentStudyEnrollPageQueryParam studentStudyEnrollPageQueryParam);

    /**
     * 完善信息
     * @param studentStudyEnrollEditParam
     * @return
     */
    ResObject completeStudyEnroll(CompleteStudyEnrollParam completeStudyEnrollParam);

    /**
     * 查询待支付转化列表
     * @param param
     * @return
     * @throws BizException
     */
    ResObject stayPayChangePageList(@Valid StudentStudyEnrollPageQueryParam param) throws BizException;
}

