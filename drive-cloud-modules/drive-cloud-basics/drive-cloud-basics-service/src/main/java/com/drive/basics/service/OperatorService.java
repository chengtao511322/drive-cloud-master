package com.drive.basics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.basics.pojo.entity.OperatorEntity;
import com.drive.basics.pojo.vo.OperatorItemVo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 *
 * 运营商基础信息 服务类
 *
 * @author xiaoguo
 */
public interface OperatorService extends IService<OperatorEntity>{

    boolean removeByIds(OperatorEntity entity, Collection<? extends Serializable> idList);

    List<OperatorEntity> getOperatorByOperatorId(String operatorId);

    List<OperatorItemVo> findAllList();

}

