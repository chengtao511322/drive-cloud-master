package com.drive.admin.repository;

import com.drive.admin.pojo.dto.ServiceInfoEditParam;
import com.drive.admin.pojo.dto.ServiceInfoPageQueryParam;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.biz.ResObject;

/**
 *
 * 客服人员信息表 服务类
 *
 * @author xiaoguo
 */
public interface ServiceInfoRepository extends BaseRepository<ServiceInfoPageQueryParam, ServiceInfoEditParam>{


    /**
     * 密码重置
     * @param serviceInfoEditParam
     * @return
     */
    ResObject resetPwd(ServiceInfoEditParam serviceInfoEditParam);
}

