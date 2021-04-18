package com.drive.admin.repository;

import com.drive.admin.pojo.dto.OneFeeSystemCoachStudentEditParam;
import com.drive.admin.pojo.dto.OneFeeSystemCoachStudentPageQueryParam;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.biz.ResObject;

/**
 *
 * 一费制学员教练关联表 服务类
 *
 * @author xiaoguo
 */
public interface OneFeeSystemCoachStudentRepository extends BaseRepository<OneFeeSystemCoachStudentPageQueryParam, OneFeeSystemCoachStudentEditParam>{

    /**
     * 绑定一费制教练
     * @param oneFeeSystemCoachStudentEditParam
     * @return
     */
    ResObject bindingCoach(OneFeeSystemCoachStudentEditParam oneFeeSystemCoachStudentEditParam);

    /**
     * 通过订单号查询学员教练关联表数据
     * @param orderNo
     * @return
     */
    ResObject getCoachStudentByOrderNo(String orderNo);
}

