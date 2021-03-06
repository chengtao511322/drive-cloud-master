package com.drive.system.controller;

import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.log.annotation.EventLog;
import com.drive.common.security.domain.LoginUser;
import com.drive.common.security.utils.SecurityUtils;
import com.drive.system.pojo.dto.UserEditParam;
import com.drive.system.pojo.entity.UserEntity;
import com.drive.system.service.UserService;
import com.drive.system.service.mapstruct.UserMapStruct;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

import static com.drive.common.security.utils.SecurityUtils.getLoginUser;

/**
 * 个人信息
 *
 * @author xiaoguo
 */
@Api(tags = "个人信息管理")
@RestController
@RequestMapping("/user/profile")
public class ProfileController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapStruct userMapStruct;
   /* @Autowired
    private RemoteOssService ossService;*/

    /**
     * 个人信息
     */
    @GetMapping
    public ResObject profile() {
        Long userId = getLoginUser().getUserId();
        UserEntity user = userService.getById(userId);

        Map result = new HashMap();
        result.put("user", user);
        result.put("roleGroup", userService.selectUserRoleGroup(user.getUserName()));
        result.put("postGroup", userService.selectUserPostGroup(user.getUserName()));
        return R.success(result);
    }

    /**
     * 修改用户
     */
    @EventLog(message = "个人信息", businessType = EventLogEnum.UPDATE)
    @PutMapping
    public ResObject updateProfile(@RequestBody UserEditParam user) {
        UserEntity userEntity = userMapStruct.toEntity(user);
        if (userService.updateUserProfile(userEntity)) {
            return R.success();
        }
        return R.failure("修改个人信息异常，请联系管理员");
    }

    /**
     * 重置密码
     */
    @EventLog(message = "重置密码", businessType = EventLogEnum.UPDATE)
    @PutMapping("/updatePwd")
    public ResObject updatePwd(String oldPassword, String newPassword) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        String userName = loginUser.getUsername();
        String password = userService.getById(loginUser.getUserId()).getPassword();

        if (!SecurityUtils.matchesPassword(oldPassword, password)) {
            return R.failure("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, password)) {
            return R.failure("新密码不能与旧密码相同");
        }
        if (userService.resetUserPwd(userName, SecurityUtils.encryptPassword(newPassword))) {
            return R.success();
        }
        return R.failure("修改密码异常，请联系管理员");
    }

    /**
     * 修改头像
     */
    @EventLog(message = "修改头像", businessType = EventLogEnum.UPDATE)
    @PostMapping("/avatar")
    public ResObject avatar(@RequestParam("file") MultipartFile file){
        // 文件存储路径  /2020-01-01/43897583617343545473.jpg

        ResObject<Map> resObject = null;
        // ResObject<Map> resObject = ossService.upload(file);

        if(resObject != null){
            String filePath = resObject.getData().get("filePath").toString();

            LoginUser loginUser = SecurityUtils.getLoginUser();
            UserEntity userEntity = new UserEntity();
            userEntity.setUserId(loginUser.getUserId());
            userEntity.setAvatar(filePath);
            userService.updateById(userEntity);
        }
        return resObject;
    }

}
