package com.drive.system.pojo;

import com.drive.system.pojo.vo.UserVo;
import lombok.Data;

import java.util.Set;

@Data
public class UserInfo implements java.io.Serializable {

    /**
     * 用户基本信息
     */
    private UserVo userVo;

    /**
     * 权限标识集合
     */
    private Set<String> permissions;

    /**
     * 角色集合
     */
    private Set<String> roles;


}
