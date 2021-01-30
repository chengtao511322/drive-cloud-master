package com.drive.system.repository;

import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.biz.ResObject;
import com.drive.system.pojo.dto.DictItemEditParam;
import com.drive.system.pojo.dto.DictItemPageQueryParam;
import com.drive.system.pojo.dto.UserEditParam;
import com.drive.system.pojo.dto.UserPageQueryParam;

public interface DictItemRepository extends BaseRepository<DictItemPageQueryParam, DictItemEditParam> {

    /**
     * 获取所有数据
     * @return
     */
    ResObject allList();
}
