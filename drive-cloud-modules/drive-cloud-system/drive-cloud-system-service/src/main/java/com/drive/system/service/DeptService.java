package com.drive.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.common.core.tree.TreeSelect;
import com.drive.system.pojo.entity.DeptEntity;
import com.drive.system.pojo.vo.DeptVo;

import java.util.List;

/**
 *
 * 部门 服务类
 *
 * @author xiaoguo
 */
public interface DeptService extends IService<DeptEntity>{

    int insertDept(DeptEntity dept);

    List<Integer> selectDeptListByRoleId(Long roleId);

    List<TreeSelect> buildDeptTreeSelect(List<DeptVo> depts);

}

