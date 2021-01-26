package com.drive.system.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.constant.Constants;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.data.utils.ExcelUtils;
import com.drive.common.log.annotation.EventLog;
import com.drive.system.pojo.dto.RoleEditParam;
import com.drive.system.pojo.dto.RolePageQueryParam;
import com.drive.system.pojo.entity.RoleEntity;
import com.drive.system.pojo.vo.RoleVo;
import com.drive.system.service.RoleService;
import com.drive.system.service.mapstruct.RoleMapStruct;
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
import java.util.Arrays;
import java.util.List;


/**
 * 角色信息管理
 *
 * @author xiaoguo
 */
@Api(tags = "角色信息管理")
@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController<RolePageQueryParam, RoleEntity> {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMapStruct roleMapStruct;

    /**
     * 角色信息 分页列表
     */
    @ApiOperation("角色信息分页列表")
    @PreAuthorize("hasPermission('/role',  'system:role:query')")
    @GetMapping(value = "/pageList")
    public ResObject pageList(@Valid RolePageQueryParam param) {

        Page<RoleEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<RoleEntity> pageList = roleService.page(page, this.getQueryWrapper(roleMapStruct, param));
        Page<RoleVo> roleVoPage = roleMapStruct.toVoList(pageList);
        return R.success(roleVoPage);
    }

    /**
     * 获取角色信息
     */
    @ApiOperation("获取角色信息")
    @ApiImplicitParam(name = "roleId", required = true, dataType = "Long", paramType = "path")
    @PreAuthorize("hasPermission('/role',  'system:role:query')")
    @GetMapping("/{roleId}")
    public ResObject get(@PathVariable Long roleId) {
        RoleEntity role = roleService.getById(roleId);
        return R.success(role);
    }

    /**
     * 新增角色信息
     */
    @ApiOperation("新增角色信息")
    @ApiImplicitParam(name = "RoleEditParam ", value = "新增角色信息", dataType = "RoleEditParam")
    @PreAuthorize("hasPermission('/role',  'system:role:add')")
    @EventLog(message = "新增角色信息", businessType = EventLogEnum.CREATE)
    @PostMapping
    public ResObject save(@Valid @RequestBody RoleEditParam roleEditParam) {

        RoleEntity roleEntity = roleService.lambdaQuery().eq(RoleEntity::getRoleKey, roleEditParam.getRoleKey()).one();
        if (roleEntity != null) {
            return R.failure("角色名称已存在, 请使用其他角色名称");
        }

        return R.toRes(roleService.insertRole(roleEditParam));
    }

    /**
     * 修改角色信息
     */
    @ApiOperation("修改角色信息")
    @ApiImplicitParam(name = "RoleEditParam ", value = "修改角色信息", dataType = "RoleEditParam")
    @PreAuthorize("hasPermission('/role',  'system:role:edit')")
    @EventLog(message = "修改角色信息", businessType = EventLogEnum.UPDATE)
    @PutMapping
    public ResObject edit(@Valid @RequestBody RoleEditParam roleEditParam) {
        roleService.checkRoleAllowed(roleEditParam);
        return R.toRes(roleService.updateRole(roleEditParam));
    }

    /**
     * 删除角色信息
     */
    @ApiOperation("删除角色信息")
    @ApiImplicitParam(name = "roleId", required = true, dataType = "Long", paramType = "path")
    @PreAuthorize("hasPermission('/role',  'system:role:delete')")
    @EventLog(message = "删除角色信息", businessType = EventLogEnum.DELETE)
    @DeleteMapping("/{roleIds}")
    public ResObject delete(@PathVariable Long[] roleIds) {
        List<Long> roleIdList = Arrays.asList(roleIds);
        roleIdList.remove(Constants.ADMIN_ROLE_ID);
        return R.toRes(roleService.removeByIds(roleIdList));
    }

    /**
     * 导出角色信息
     */
    @ApiOperation("导出角色信息")
    @PreAuthorize("hasPermission('/role',  'system:role:export')")
    @SneakyThrows
    @EventLog(message = "导出角色信息", businessType = EventLogEnum.EXPORT)
    @PostMapping(value = "/exportXls")
    public void exportXls(RolePageQueryParam param, HttpServletResponse response) {
        List<RoleEntity> list = roleService.list(this.getQueryWrapper(roleMapStruct, param));
        List<RoleVo> roleVoList = roleMapStruct.toVoList(list);
        ExcelUtils.exportExcel(roleVoList, RoleVo.class, "角色信息", new ExportParams(), response);
    }


    /**
     * 获取角色选择框列表
     */
    @GetMapping("/optionselect")
    public ResObject optionselect() {
        List<RoleEntity> list = roleService.list();
        return R.success(roleMapStruct.toVoList(list));
    }

    /**
     * 修改保存数据权限
     */
    @PreAuthorize("hasPermission('/role',  'system:role:edit')")
    @PutMapping("/dataScope")
    public ResObject dataScope(@RequestBody RoleEditParam roleEditParam) {
        roleService.checkRoleAllowed(roleEditParam);

        roleService.authDataScope(roleEditParam);
        return R.success();
    }

    /**
     * 状态修改
     */
    @PreAuthorize("hasPermission('/role',  'system:role:edit')")
    @PutMapping("/changeStatus")
    public ResObject changeStatus(@RequestBody RoleEditParam roleEditParam) {
        roleService.checkRoleAllowed(roleEditParam);

        RoleEntity role = roleMapStruct.toEntity(roleEditParam);
        return R.toRes(roleService.updateById(role));
    }

}
