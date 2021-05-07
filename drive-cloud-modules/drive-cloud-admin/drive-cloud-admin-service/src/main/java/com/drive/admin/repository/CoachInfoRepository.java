package com.drive.admin.repository;

import com.drive.admin.pojo.dto.CoachInfoDataEditParam;
import com.drive.admin.pojo.dto.CoachInfoEditParam;
import com.drive.admin.pojo.dto.CoachInfoInstallParam;
import com.drive.admin.pojo.dto.CoachInfoPageQueryParam;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.biz.ResObject;

/**
 *
 * 教练信息表 服务类
 *
 * @author xiaoguo
 */
public interface CoachInfoRepository extends BaseRepository<CoachInfoPageQueryParam, CoachInfoEditParam>{

    /**
     * 修改教练信息
     * @param updateParam
     * @return
     */
    ResObject updateCoachInfo(CoachInfoDataEditParam updateParam);

    /**
     * 驳回教练
     * @param updateParam
     * @return
     */
    ResObject rejectCoach(CoachInfoInstallParam updateParam);

}

