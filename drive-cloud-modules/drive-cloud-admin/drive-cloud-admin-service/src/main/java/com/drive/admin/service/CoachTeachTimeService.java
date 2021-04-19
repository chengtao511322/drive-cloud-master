package com.drive.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.admin.pojo.dto.CoachTeachTimePageQueryParam;
import com.drive.admin.pojo.entity.CoachTeachTimeEntity;

import java.math.BigDecimal;

/**
 *
 * 教练课程时间表 服务类
 *
 * @author guyi
 */
public interface CoachTeachTimeService extends IService<CoachTeachTimeEntity>{

    /**
     * 查询总课时
     * @param coachTeachTimePageQueryParam
     * @return
     */
    BigDecimal selectExpectCoachIncomeSum(CoachTeachTimePageQueryParam coachTeachTimePageQueryParam);

    /**
     * 查询驾校收入
     * @param coachTeachTimePageQueryParam
     * @return
     */
    BigDecimal selectExpectSchoolIncomeSum(CoachTeachTimePageQueryParam coachTeachTimePageQueryParam);
}

