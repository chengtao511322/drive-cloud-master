package com.drive.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.drive.system.pojo.entity.RoleDeptEntity;

import java.util.List;

/**
 * 角色和部门关联 Mapper 接口
 *
 * @author xiaoguo
 * @since 2020-07-15
 */
public interface RoleDeptMapper extends BaseMapper<RoleDeptEntity> {

    /**
     * 获取部门运营商数据
     * @return
     */
    List<RoleDeptEntity> getAllDept();
}

