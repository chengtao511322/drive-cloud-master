package com.drive.basics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.drive.basics.pojo.entity.OperatorEntity;
import com.drive.basics.pojo.vo.OperatorItemVo;

import java.util.List;

/**
 * 运营商基础信息 Mapper 接口
 *
 * @author xiaoguo
 */
public interface OperatorMapper extends BaseMapper<OperatorEntity> {

    List<OperatorItemVo> findAllList();

}

