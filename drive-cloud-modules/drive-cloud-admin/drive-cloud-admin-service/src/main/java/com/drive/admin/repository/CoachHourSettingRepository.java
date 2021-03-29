package com.drive.admin.repository;

import com.drive.admin.pojo.dto.CoachHourSettingDetailEditParam;
import com.drive.admin.pojo.dto.CoachHourSettingEditParam;
import com.drive.admin.pojo.dto.CoachHourSettingPageQueryParam;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.biz.ResObject;

import java.util.List;

/**
 *
 * 教练发课设置 服务类
 *
 * @author xiaoguo
 */
public interface CoachHourSettingRepository extends BaseRepository<CoachHourSettingPageQueryParam, CoachHourSettingEditParam>{

    /**
     * 发布教练发课
     * @param coachHourSettingEditParam
     * @return
     */
    ResObject publish(CoachHourSettingEditParam coachHourSettingEditParam);

    /**
     * 修改教练发课
     * @param coachHourSettingEditParam
     * @return
     */
    ResObject updateCoachHourSetting(CoachHourSettingEditParam coachHourSettingEditParam);

    /**
     * 修改时间段
     * @param coachHourSettingDetailList
     * @return
     */
    ResObject updateCoachHourSettingDetail(List<CoachHourSettingDetailEditParam> coachHourSettingDetailList);

    /**
     * 运营商查询获取生效时间
     * @param operatorId
     * @return
     */
    ResObject getEffectiveDateTime(String operatorId);
}

