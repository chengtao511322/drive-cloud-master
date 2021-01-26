package com.drive.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.drive.admin.pojo.dto.ServicePackageDetailPageQueryParam;
import com.drive.admin.pojo.entity.ServicePackageDetailEntity;

/**
 * 服务项目打包明细表 Mapper 接口
 *
 * @author xiaoguo
 */
public interface ServicePackageDetailMapper extends BaseMapper<ServicePackageDetailEntity> {

    /**
     * 条件删除数据
     * @param servicePackageDetailPageQueryParam
     * @return
     */
    int delByCondition(ServicePackageDetailPageQueryParam servicePackageDetailPageQueryParam);

    /**
     * 通过服务价格Id查询发布状态
     * @param serviceItemPriceId
     * @return
     */
    int getPublishStatus(String serviceItemPriceId);

}

