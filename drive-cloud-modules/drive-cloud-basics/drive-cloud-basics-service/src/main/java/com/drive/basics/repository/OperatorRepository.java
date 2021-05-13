package com.drive.basics.repository;

import com.drive.basics.pojo.dto.OperatorEditParam;
import com.drive.basics.pojo.dto.OperatorPageQueryParam;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.biz.ResObject;

/**
 * 運運輸
 */
public interface OperatorRepository extends BaseRepository<OperatorPageQueryParam, OperatorEditParam> {


    ResObject getOperatorByOperatorId(String operatorId);

    /**
     * 查询所有缓存
     * @return
     */
    ResObject findAllList();

    /**
     * 保存
     * @param operatorEditParam
     * @return
     */
    ResObject saveOperator(OperatorEditParam operatorEditParam);
}
