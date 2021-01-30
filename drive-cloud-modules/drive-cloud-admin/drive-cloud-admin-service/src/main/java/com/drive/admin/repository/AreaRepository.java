package com.drive.admin.repository;

import com.drive.admin.pojo.dto.AreaEditParam;
import com.drive.admin.pojo.dto.AreaPageQueryParam;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.biz.ResObject;

/**
 *
 *  服务类
 *
 * @author xiaoguo
 */
public interface AreaRepository extends BaseRepository<AreaPageQueryParam, AreaEditParam>{

    /**
     * 查询所有
     * @return
     */
    ResObject allList();
}

