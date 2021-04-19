package com.drive.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.drive.admin.pojo.dto.CoachTeachTimePageQueryParam;
import com.drive.admin.pojo.entity.CoachTeachTimeEntity;

import java.math.BigDecimal;

/**
 * 教练课程时间表 Mapper 接口
 *
 * @author guyi
 */
public interface CoachTeachTimeMapper extends BaseMapper<CoachTeachTimeEntity> {

    /**
     * 查询教练收入
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

