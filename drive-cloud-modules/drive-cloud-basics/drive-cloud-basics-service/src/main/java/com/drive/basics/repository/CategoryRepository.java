package com.drive.basics.repository;

import com.drive.basics.pojo.dto.CategoryEditParam;
import com.drive.basics.pojo.dto.CategoryPageQueryParam;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.biz.ResObject;

/**
 *
 * 产品分类表 服务类
 *
 * @author xiaoguo
 */
public interface CategoryRepository extends BaseRepository<CategoryPageQueryParam, CategoryEditParam>{

    /**
     * 通过类型获取
     * @param type
     * @return
     */
    ResObject getCategoryByType(String type,String tenantId);
}

