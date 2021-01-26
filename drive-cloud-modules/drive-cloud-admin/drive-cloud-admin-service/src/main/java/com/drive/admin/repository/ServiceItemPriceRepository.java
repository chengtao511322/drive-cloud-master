package com.drive.admin.repository;

import com.drive.admin.pojo.dto.ServiceItemPriceEditParam;
import com.drive.admin.pojo.dto.ServiceItemPricePageQueryParam;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.biz.ResObject;

/**
 *
 * 服务项目价格表 服务类
 *
 * @author JoyoDuan
 */
public interface ServiceItemPriceRepository extends BaseRepository<ServiceItemPricePageQueryParam, ServiceItemPriceEditParam>{

    /**
     * 查询服务项目价格列表
     * @param ew
     * @return
     */
    ResObject queryList(ServiceItemPricePageQueryParam param);

    /**
     * 分页查询
     * @param param
     * @return
     */
    ResObject queryPageList(ServiceItemPricePageQueryParam param);

    /**
     * 通过id 查询
     * @param param
     * @return
     */
    ResObject getQueryListById(ServiceItemPricePageQueryParam param);

    /**
     * 处理 数据
     * @param param
     * @return
     */
    ResObject findListContrast(ServiceItemPricePageQueryParam param);
}

