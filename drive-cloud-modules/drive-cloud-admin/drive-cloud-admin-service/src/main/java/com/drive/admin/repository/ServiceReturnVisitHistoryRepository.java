package com.drive.admin.repository;

import com.drive.admin.pojo.dto.*;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.base.BasicsRepository;
import com.drive.common.core.biz.ResObject;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * 客服回访记录 服务类
 *
 * @author xiaoguo
 */
public interface ServiceReturnVisitHistoryRepository extends BasicsRepository<ServiceReturnVisitHistoryPageQueryParam, ServiceReturnVisitHistoryEditParam,ServiceReturnVisitHistoryInstallParam> {

    /**
     * 发布回访
     * @param returnVisitHistoryEditParam
     * @return
     */
    ResObject publishReturnVisit(ServiceReturnVisitHistoryEditParam returnVisitHistoryEditParam);

    /**
     * 分页查询
     * @param param
     * @return
     */
    ResObject pageListReturnVisitHistory(StudentStudyEnrollPageQueryParam param);

    /**
     * 通过学员ID 聚合查询 售前 售后数据
     * @param param
     * @return
     */
    ResObject aggregationListReturnVisitHistory(String studentId);

    /**
     * 添加回访记录
     * @param serviceReturnVisitHistoryEditParam
     * @return
     */
    ResObject addReturnVisitHistory(ServiceReturnVisitHistoryInstallParam serviceReturnVisitHistoryEditParam);
}

