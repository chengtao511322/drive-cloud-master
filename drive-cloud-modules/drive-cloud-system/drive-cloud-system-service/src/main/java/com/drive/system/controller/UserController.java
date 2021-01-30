package com.drive.system.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.constant.Constants;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.core.utils.StringUtils;
import com.drive.common.data.utils.ExcelUtils;
import com.drive.common.datascope.annotation.DataScope;
import com.drive.common.log.annotation.EventLog;
import com.drive.common.security.utils.SecurityUtils;
import com.drive.system.pojo.UserInfo;
import com.drive.system.pojo.dto.UserEditParam;
import com.drive.system.pojo.dto.UserPageQueryParam;
import com.drive.system.pojo.entity.RoleEntity;
import com.drive.system.pojo.entity.UserEntity;
import com.drive.system.pojo.vo.MenuVo;
import com.drive.system.pojo.vo.UserVo;
import com.drive.system.repository.UserRepository;
import com.drive.system.service.MenuService;
import com.drive.system.service.PostService;
import com.drive.system.service.RoleService;
import com.drive.system.service.UserService;
import com.drive.system.service.mapstruct.UserMapStruct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;


/**
 * 用户信息管理
 *
 * @author xiaoguo
 */
@Api(tags = "用户信息管理")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<UserPageQueryParam, UserEntity> {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapStruct userMapStruct;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PostService postService;
    @Autowired
    private MenuService menuService;

    @Autowired
    private UserRepository userRepository;

    /**
     * 用户信息 分页列表
     */
    @ApiOperation("用户信息分页列表")
    @PreAuthorize("hasPermission('/user',  'system:user:query')")
    @GetMapping(value = "/pageList")
    public ResObject pageList(@Valid UserPageQueryParam param) {
        return userRepository.pageList(param);
    }

    /**
     * 获取用户信息
     */
    @ApiOperation("获取用户信息")
    @ApiImplicitParam(name = "userId", required = true, dataType = "Long", paramType = "path")
    @PreAuthorize("hasPermission('/user',  'system:user:query')")
    @GetMapping(value = {"/", "/{userId}"})
    public ResObject getInfo(@PathVariable(value = "userId", required = false) Long userId) {
        UserEntity user = userService.getById(userId);
        UserVo userVo = userMapStruct.toVo(user);
        Map res = new HashMap<>();
        List<RoleEntity> roles = roleService.list();
        res.put("roles", roles);
        res.put("posts", postService.list());
        if (StringUtils.isNotNull(userId)) {
            res.put("postIds", postService.selectPostListByUserId(userId));
            res.put("roleIds", roleService.selectRoleListByUserId(userId));
        }
        res.put("user", userVo);
        return R.success(res);
    }

    /**
     * 新增用户信息
     */
    @ApiOperation("新增用户信息")
    @ApiImplicitParam(name = "UserEditParam ", value = "新增用户信息", dataType = "UserEditParam")
    @PreAuthorize("hasPermission('/user',  'system:user:add')")
    @EventLog(message = "新增用户信息", businessType = EventLogEnum.CREATE)
    @PostMapping
    public ResObject save(@Valid @RequestBody UserEditParam userEditParam) {
        UserEntity userEntity = userService.lambdaQuery().eq(UserEntity::getUserName, userEditParam.getUserName()).one();
        if (userEntity != null) {
            return R.failure("用户账号已存在, 请使用其他账号名");
        }

        userEditParam.setPassword(SecurityUtils.encryptPassword(userEditParam.getPassword()));

        return R.toRes(userService.insertUser(userEditParam));
    }

    /**
     * 修改用户信息
     */
    @ApiOperation("修改用户信息")
    @ApiImplicitParam(name = "UserEditParam ", value = "修改用户信息", dataType = "UserEditParam")
    @PreAuthorize("hasPermission('/user',  'system:user:edit')")
    @EventLog(message = "修改用户信息", businessType = EventLogEnum.UPDATE)
    @PutMapping
    public ResObject edit(@Valid @RequestBody UserEditParam userEditParam) {

        return R.toRes(userService.updateUser(userEditParam));
    }

    /**
     * 删除用户信息
     */
    @ApiOperation("删除用户信息")
    @ApiImplicitParam(name = "userId", required = true, dataType = "Long", paramType = "path")
    @PreAuthorize("hasPermission('/user',  'system:user:delete')")
    @EventLog(message = "删除用户信息", businessType = EventLogEnum.DELETE)
    @DeleteMapping("/{userIds}")
    public ResObject delete(@PathVariable Long[] userIds) {
        // 管理员admin账号不能删除
        List<Long> userIdList = Arrays.asList(userIds);
        userIdList.remove(Constants.ADMIN_USER_ID);
        return R.toRes(userService.removeByIds(userIdList));
    }

    /**
     * 导出用户信息
     */
    @ApiOperation("导出用户信息")
    @PreAuthorize("hasPermission('/user',  'system:user:export')")
    @SneakyThrows
    @EventLog(message = "导出用户信息", businessType = EventLogEnum.EXPORT)
    @PostMapping(value = "/exportXls")
    public void exportXls(UserPageQueryParam param, HttpServletResponse response) {
        List<UserEntity> list = userService.list(this.getQueryWrapper(userMapStruct, param));
        List<UserVo> userVoList = userMapStruct.toVoList(list);
        ExcelUtils.exportExcel(userVoList, UserVo.class, "用户信息", new ExportParams(), response);
    }

    /**
     * 状态修改
     */
    @PreAuthorize("hasPermission('/user',  'system:user:edit')")
    @EventLog(message = "修改用户信息", businessType = EventLogEnum.UPDATE)
    @PutMapping("/changeStatus")
    public ResObject changeStatus(@RequestBody UserEditParam userEditParam) {
        userService.checkUserAllowed(userEditParam);

        UserEntity user = new UserEntity();
        user.setUserId(userEditParam.getUserId());
        user.setStatus(userEditParam.getStatus());
        return R.toRes(userService.updateById(user));
    }

    /**
     * 重置密码
     */
    @PreAuthorize("hasPermission('/user',  'system:user:edit')")
    @EventLog(message = "重置密码", businessType = EventLogEnum.UPDATE)
    @PutMapping("/resetPwd")
    public ResObject resetPwd(@RequestBody UserEditParam userEditParam) {
        userService.checkUserAllowed(userEditParam);

        UserEntity user = userMapStruct.toEntity(userEditParam);
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));

        return R.toRes(userService.updateById(user));
    }


    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/getInfo")
    public ResObject getInfo() {

        Long userId = SecurityUtils.getLoginUser().getUserId();

        UserEntity user = userService.getById(userId);

        // 角色集合
        Set<String> roles = roleService.getRoleListByUserId(userId);
        // 权限集合
        Set<String> permissions = menuService.getMenuListByUserId(userId);

        Map<String, Object> result = new HashMap<>();
        result.put("user", user);
        result.put("roles", roles);
        result.put("permissions", permissions);
        return R.success(result);
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/info/{username}")
    public ResObject<UserInfo> info(@PathVariable("username") String username) {
        UserVo userVo = userService.getUserVoByUserName(username);

        if (StringUtils.isNull(userVo)) {
            return R.failure("用户不存在");
        }

        // 角色集合
        Set<String> roles = roleService.getRoleListByUserId(userVo.getUserId());
        // 权限集合
        Set<String> permissions = menuService.getMenuListByUserId(userVo.getUserId());

        UserInfo sysUserVo = new UserInfo();
        sysUserVo.setUserVo(userVo);
        sysUserVo.setRoles(roles);
        sysUserVo.setPermissions(permissions);
        return R.success(sysUserVo);
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("/getRouters")
    public ResObject getRouters() {
        // 用户信息
        Long userId = SecurityUtils.getLoginUser().getUserId();
        List<MenuVo> menus = menuService.selectMenuTreeByUserId(userId);
        return R.success(menuService.buildRouters(menus));
    }
}
