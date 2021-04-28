package com.drive.system.pojo.vo;

import lombok.Data;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $
 * @Param $
 * @return $
 **/
@Data
public class RoleDeptVo {

    // 角色ID
    private Long roleId;

    // 部门ID
    private Long deptId;

    private String tenantIds;
}
