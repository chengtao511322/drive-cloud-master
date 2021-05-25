package com.drive.admin.repository;

import com.drive.admin.pojo.dto.OperatorEditParam;
import com.drive.admin.pojo.dto.OperatorInstallParam;
import com.drive.admin.pojo.dto.OperatorPageQueryParam;
import com.drive.common.core.base.BasicsRepository;
import com.drive.common.core.biz.ResObject;

/**
 *
 * 运营商基础信息 服务类
 *
 * @author xiaoguo
 */
public interface OperatorRepository extends BasicsRepository<OperatorPageQueryParam, OperatorEditParam,OperatorInstallParam> {

    /**
     * 添加运营商
     * @return
     */
    ResObject installOperator(OperatorInstallParam operatorEditParam);

    /**
     * 修改运营商
     * @param operatorEditParam
     * @return
     */
    ResObject updateOperator(OperatorEditParam operatorEditParam);

    /**
     * 删除运营商
     * @param operatorEditParam
     * @return
     */
    ResObject delOperator(OperatorEditParam operatorEditParam);

    /**
     * 通过运营商ID 获取运营区域
     * @param operatorId
     * @return
     */
    ResObject getOperatingArea(String operatorId);
}

