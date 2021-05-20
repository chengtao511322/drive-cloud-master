package com.drive.system.repository;

import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.biz.ResObject;
import com.drive.system.pojo.dto.UserEditParam;
import com.drive.system.pojo.dto.UserPageQueryParam;

/**
 * 用户
 */
public interface UserRepository extends BaseRepository<UserPageQueryParam, UserEditParam> {

    /**
     * 初始化
     * @return
     */
    ResObject init();


    ResObject pageUserList(UserPageQueryParam param);
}
