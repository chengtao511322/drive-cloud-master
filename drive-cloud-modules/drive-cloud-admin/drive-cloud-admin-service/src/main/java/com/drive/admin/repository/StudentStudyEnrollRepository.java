package com.drive.admin.repository;

import com.drive.admin.pojo.dto.CompleteStudyEnrollParam;
import com.drive.admin.pojo.dto.StudentOrderPageQueryParam;
import com.drive.admin.pojo.dto.StudentStudyEnrollEditParam;
import com.drive.admin.pojo.dto.StudentStudyEnrollPageQueryParam;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.exception.BizException;

import javax.validation.Valid;

/**
 *
 * 学员学车报名单 服务类
 *
 * @author xiaoguo
 */
public interface StudentStudyEnrollRepository extends BaseRepository<StudentStudyEnrollPageQueryParam, StudentStudyEnrollEditParam>{


    /**
     * 分页查询数据
     * @param param
     * @return
     */
    ResObject studyEnrollPageList(StudentStudyEnrollPageQueryParam param);

    /**
     *通过条件查询出一条数据
     * @param studentStudyEnrollPageQueryParam
     * @return
     */
    ResObject getStudentStudyEnrollInfo(StudentStudyEnrollPageQueryParam studentStudyEnrollPageQueryParam);

    /**
     * 完善信息
     * @param
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


    /**
     * 统计数据
     * @return
     */
    ResObject statisticsStudentDataPageList(@Valid StudentStudyEnrollPageQueryParam param);

    /**
     * 练车数据
     * @param param
     * @return
     */
    ResObject drivingStudentDataPageList(@Valid StudentStudyEnrollPageQueryParam param);

    /**
     * 训练订单数据
     * @param param
     * @return
     */
    ResObject drillStudentDataPageList(@Valid StudentOrderPageQueryParam param);
}

