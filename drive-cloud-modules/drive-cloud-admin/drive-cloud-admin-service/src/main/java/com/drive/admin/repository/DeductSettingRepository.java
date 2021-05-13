package com.drive.admin.repository;

import com.drive.admin.pojo.dto.*;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.base.BasicsRepository;
import com.drive.common.core.biz.ResObject;

import java.util.List;

/**
 *
 * 提成设置表 服务类
 *
 * @author xiaoguo
 */
public interface DeductSettingRepository extends BasicsRepository<DeductSettingPageQueryParam, DeductSettingEditParam,DeductSettingInstallParam>{

    /**
     * 批量添加
     * @param param
     * @return
     */
    ResObject saveBatch(List<DeductSettingInstallParam> deductSettingInstallParamList);

}

