package com.drive.admin.repository;

import com.drive.admin.pojo.dto.*;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.base.BasicsRepository;
import com.drive.common.core.biz.ResObject;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * 平台报名考试练车单价表 服务类
 *
 * @author xiaoguo
 */
public interface TestTrainPriceRepository extends BasicsRepository<TestTrainPricePageQueryParam, TestTrainPriceEditParam,TestTrainPriceInstallParam> {

    /**
     * 查询推广商提成百分比
     * @param operatorId
     * @return
     */
    ResObject getOperatorDeduct(@PathVariable String operatorId);
}

