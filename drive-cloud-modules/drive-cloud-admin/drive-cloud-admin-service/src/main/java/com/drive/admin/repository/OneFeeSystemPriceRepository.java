package com.drive.admin.repository;

import com.drive.admin.pojo.dto.OneFeeSystemPriceEditParam;
import com.drive.admin.pojo.dto.OneFeeSystemPricePageQueryParam;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.biz.ResObject;

/**
 *
 * 学车一费制定价表 服务类
 *
 * @author xiaoguo
 */
public interface OneFeeSystemPriceRepository extends BaseRepository<OneFeeSystemPricePageQueryParam, OneFeeSystemPriceEditParam>{

    /**
     *发布服务包
     * @return
     */
    ResObject publishServicePackage(OneFeeSystemPriceEditParam oneFeeSystemPriceEditParam);

    /**
     * 修改服务包
     * @param oneFeeSystemPriceEditParam
     * @return
     */
    ResObject updateServicePackage(OneFeeSystemPriceEditParam oneFeeSystemPriceEditParam);


    /**
     * 复制服务包
     * @param oneFeeSystemPriceEditParam
     * @return
     */
    ResObject copyServicePackage(OneFeeSystemPriceEditParam oneFeeSystemPriceEditParam);

    /**
     * 分页
     * @param param
     * @return
     */
    ResObject findPageSubList(OneFeeSystemPricePageQueryParam param);

    /**
     * 获取树形结构
     * @param param
     * @return
     */
    ResObject getServicePackageTree(String tenantId);
}

