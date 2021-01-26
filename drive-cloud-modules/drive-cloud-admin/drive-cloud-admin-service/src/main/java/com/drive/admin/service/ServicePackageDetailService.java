package com.drive.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.admin.pojo.dto.ServicePackageDetailPageQueryParam;
import com.drive.admin.pojo.entity.ServicePackageDetailEntity;

/**
 *
 * 服务项目打包明细表 服务类
 *
 * @author xiaoguo
 */
public interface ServicePackageDetailService extends IService<ServicePackageDetailEntity>{
    /**
     * 条件 -物理删除
     */
    Boolean delByCondition(ServicePackageDetailPageQueryParam servicePackageDetailPageQueryParam);

    /**
     * 通过服务价格Id查询发布状态
     * @param serviceItemPriceId
     * @return
     */
    Boolean getPublishStatus(String serviceItemPriceId);

    /**
     * 通过服务价格Id查询发布状态 总数
     * @param serviceItemPriceId
     * @return
     */
    int getPublishStatusCount(String serviceItemPriceId);
}

