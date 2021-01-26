package com.drive.admin.repository;

import com.drive.admin.pojo.dto.ServicePackageDetailEditParam;
import com.drive.admin.pojo.dto.ServicePackageDetailPageQueryParam;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.biz.ResObject;

import java.util.List;

/**
 *
 * 服务项目打包明细表 服务类
 *
 * @author xiaoguo
 */
public interface ServicePackageDetailRepository extends BaseRepository<ServicePackageDetailPageQueryParam, ServicePackageDetailEditParam>{

    /**
     * 修改服务项
     * @param servicePackageDetailEditParamList
     * @return
     */
    ResObject updateBatch(List<ServicePackageDetailEditParam> servicePackageDetailEditParamList);
}

