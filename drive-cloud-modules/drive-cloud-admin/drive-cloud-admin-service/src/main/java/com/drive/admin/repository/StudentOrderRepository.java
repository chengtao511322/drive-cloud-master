package com.drive.admin.repository;

import com.alipay.api.AlipayApiException;
import com.drive.admin.pojo.dto.StudentOrderEditParam;
import com.drive.admin.pojo.dto.StudentOrderPageQueryParam;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.biz.ResObject;
import org.springframework.web.bind.annotation.RequestBody;

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

    /**
     * 取消订单
     * @param studentOrderEditParam
     * @return
     */
    ResObject cancelOrder(StudentOrderEditParam studentOrderEditParam);

    /**
     * 订单退款
     * @param studentOrderEditParam
     * @return
     */
    ResObject orderRefund(StudentOrderEditParam studentOrderEditParam) throws Exception;
}

