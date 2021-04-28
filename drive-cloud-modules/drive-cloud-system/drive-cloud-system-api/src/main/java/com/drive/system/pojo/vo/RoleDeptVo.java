package com.drive.system.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $
 * @Param $
 * @return $
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDeptVo implements java.io.Serializable{

    // 角色ID
    private Long roleId;

    // 部门ID
    private Long deptId;
}
