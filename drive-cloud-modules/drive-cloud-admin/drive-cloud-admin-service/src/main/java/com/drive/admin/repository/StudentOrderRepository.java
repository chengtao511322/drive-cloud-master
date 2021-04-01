package com.drive.admin.repository;

import com.drive.admin.pojo.dto.StudentOrderEditParam;
import com.drive.admin.pojo.dto.StudentOrderPageQueryParam;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.biz.ResObject;

/**
 *
 * 学员订单表 服务类
 *
 * @author xiaoguo
 */
public interface StudentOrderRepository extends BaseRepository<StudentOrderPageQueryParam, StudentOrderEditParam>{


    /**
     * 通过学员ID 查询订单信息
     * @param studentId
     * @return
     */
    ResObject getOrderByStudentId(String studentId);
}

