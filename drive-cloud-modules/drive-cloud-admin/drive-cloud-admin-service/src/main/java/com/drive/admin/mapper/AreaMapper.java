package com.drive.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.drive.admin.pojo.entity.AreaEntity;
import com.drive.admin.pojo.vo.ViewDataVo;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author xiaoguo
 */
public interface AreaMapper extends BaseMapper<AreaEntity> {

    List<ViewDataVo> findView();

}

