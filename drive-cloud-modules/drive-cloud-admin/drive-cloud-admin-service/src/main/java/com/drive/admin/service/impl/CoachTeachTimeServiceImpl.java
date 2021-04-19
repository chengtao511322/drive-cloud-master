package com.drive.admin.service.impl;

import com.drive.admin.mapper.CoachTeachTimeMapper;
import com.drive.admin.pojo.dto.CoachTeachTimePageQueryParam;
import com.drive.admin.pojo.entity.CoachTeachTimeEntity;
import com.drive.admin.service.CoachTeachTimeService;
import com.drive.common.core.base.BaseService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 教练课程时间表 服务实现类
 *
 * @author guyi
 */
@Service
public class CoachTeachTimeServiceImpl extends BaseService<CoachTeachTimeMapper, CoachTeachTimeEntity> implements CoachTeachTimeService {

    @Override
    public BigDecimal selectExpectCoachIncomeSum(CoachTeachTimePageQueryParam coachTeachTimePageQueryParam) {
        return this.getBaseMapper().selectExpectCoachIncomeSum(coachTeachTimePageQueryParam);
    }

    @Override
    public BigDecimal selectExpectSchoolIncomeSum(CoachTeachTimePageQueryParam coachTeachTimePageQueryParam) {
        return this.getBaseMapper().selectExpectSchoolIncomeSum(coachTeachTimePageQueryParam);
    }
}

