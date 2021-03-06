package com.drive.system.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.data.utils.ExcelUtils;
import com.drive.common.log.annotation.EventLog;
import com.drive.common.security.utils.SecurityUtils;
import com.drive.system.pojo.dto.MenuEditParam;
import com.drive.system.pojo.dto.MenuPageQueryParam;
import com.drive.system.pojo.entity.MenuEntity;
import com.drive.system.pojo.vo.MenuVo;
import com.drive.system.service.MenuService;
import com.drive.system.service.RoleMenuService;
import com.drive.system.service.mapstruct.MenuMapStruct;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 菜单权限管理
 *
 * @author xiaoguo
 */
@Api(tags = "菜单权限管理")
@Slf4j
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController<MenuPageQueryParam, MenuEntity> {

    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuMapStruct menuMapStruct;
    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * 菜单权限 分页列表
     */
    @ApiOperation("菜单权限分页列表")
    @PreAuthorize("hasPermission('/menu',  'system:menu:query')")
    @GetMapping(value = "/pageList")
    public ResObject pageList(@Valid MenuPageQueryParam param) {
        Page<MenuEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper queryWrapper = this.getQueryWrapper(menuMapStruct, param);
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueMenuNameSearch()),"menu_name",param.getVagueMenuNameSearch());
        IPage<MenuEntity> pageList = menuService.page(page, queryWrapper);
        Page<MenuVo> menuVoPage = menuMapStruct.toVoList(pageList);
        return R.success(menuVoPage);
    }

    /**
     * 菜单权限 列表
     */
    @ApiOperation("菜单权限列表")
    @PreAuthorize("hasPermission('/menu',  'system:menu:query')")
    @GetMapping(value = "/allList")
    public ResObject allList() {
        List<MenuEntity> allList = menuService.list();
        List<MenuVo> allVoList = menuMapStruct.toVoList(allList);
        return R.success(allVoList);
    }


    /**
     * 获取菜单权限
     */
    @ApiOperation("获取菜单权限")
    @ApiImplicitParam(name = "menuId", required = true, dataType = "Long", paramType = "path")
    @PreAuthorize("hasPermission('/menu',  'system:menu:query')")
    @GetMapping("/{menuId}")
    public ResObject get(@PathVariable Long menuId) {
        MenuEntity menu = menuService.getById(menuId);
        return R.success(menu);
    }

    /**
     * 新增菜单权限
     */
    @ApiOperation("新增菜单权限")
    @ApiImplicitParam(name = "MenuEditParam ", value = "新增菜单权限", dataType = "MenuEditParam")
    @PreAuthorize("hasPermission('/menu',  'system:menu:add')")
    @EventLog(message = "新增菜单权限", businessType = EventLogEnum.CREATE)
    @PostMapping
    public ResObject save(@Valid @RequestBody MenuEditParam menuEditParam) {
        MenuEntity menu = menuMapStruct.toEntity(menuEditParam);
        return R.toRes(menuService.save(menu));
    }

    /**
     * 修改菜单权限
     */
    @ApiOperation("修改菜单权限")
    @ApiImplicitParam(name = "MenuEditParam ", value = "修改菜单权限", dataType = "MenuEditParam")
    @PreAuthorize("hasPermission('/menu',  'system:menu:edit')")
    @EventLog(message = "修改菜单权限", businessType = EventLogEnum.UPDATE)
    @PutMapping
    public ResObject edit(@Valid @RequestBody MenuEditParam menuEditParam) {
        MenuEntity menu = menuMapStruct.toEntity(menuEditParam);
        return R.toRes(menuService.updateById(menu));
    }

    /**
     * 删除菜单权限
     */
    @ApiOperation("删除菜单权限")
    @ApiImplicitParam(name = "menuId", required = true, dataType = "Long", paramType = "path")
    @PreAuthorize("hasPermission('/menu',  'system:menu:delete')")
    @EventLog(message = "删除菜单权限", businessType = EventLogEnum.DELETE)
    @DeleteMapping("/{menuId}")
    public ResObject delete(@PathVariable Long menuId) {
        if (menuService.hasChildByMenuId(menuId)) {
            return R.failure("存在子菜单,不允许删除");
        }
        if (roleMenuService.checkMenuExistRole(menuId)) {
            return R.failure("菜单已分配,不允许删除");
        }
        return R.toRes(menuService.removeById(menuId));
    }

    /**
     * 导出菜单权限
     */
    @ApiOperation("导出菜单权限")
    @PreAuthorize("hasPermission('/menu',  'system:menu:export')")
    @SneakyThrows
    @EventLog(message = "导出菜单权限", businessType = EventLogEnum.EXPORT)
    @PostMapping(value = "/exportXls")
    public void exportXls(MenuPageQueryParam param, HttpServletResponse response) {
        List<MenuEntity> list = menuService.list(this.getQueryWrapper(menuMapStruct, param));
        List<MenuVo> menuVoList = menuMapStruct.toVoList(list);
        ExcelUtils.exportExcel(menuVoList, MenuVo.class, "菜单权限", new ExportParams(), response);
    }


    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/treeselect")
    public ResObject treeselect(@Valid MenuPageQueryParam param) {
        Long userId = SecurityUtils.getLoginUser().getUserId();
        MenuEntity menuEntity = menuMapStruct.pageQueryParamToEntity(param);
        List<MenuVo> menus = menuService.selectMenuList(menuMapStruct.toVo(menuEntity), userId);
        return R.success(menuService.buildMenuTreeSelect(menus));
    }

    /**
     * 加载对应角色菜单列表树
     */
    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    public ResObject roleMenuTreeselect(@PathVariable("roleId") Long roleId) {

        Long userId = SecurityUtils.getLoginUser().getUserId();
        List<MenuVo> menus = menuService.selectMenuList(userId);

        Map result = new HashMap<>();
        result.put("checkedKeys", menuService.selectMenuListByRoleId(roleId));
        result.put("menus", menuService.buildMenuTreeSelect(menus));
        return R.success(result);
    }
}
