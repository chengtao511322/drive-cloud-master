package com.drive.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.drive.system.pojo.entity.DeptEntity;

import java.util.List;

/**
 * 部门 Mapper 接口
 *
 * @author xiaoguo
 */
public interface DeptMapper extends BaseMapper<DeptEntity> {

    List<Integer> selectDeptListByRoleId(Long roleId);
}

