package com.drive.system.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.data.utils.ExcelUtils;
import com.drive.common.log.annotation.EventLog;
import com.drive.system.pojo.dto.DeptEditParam;
import com.drive.system.pojo.dto.DeptPageQueryParam;
import com.drive.system.pojo.entity.DeptEntity;
import com.drive.system.pojo.entity.RoleDeptEntity;
import com.drive.system.pojo.vo.DeptVo;
import com.drive.system.pojo.vo.RoleDeptVo;
import com.drive.system.repository.UserRepository;
import com.drive.system.service.DeptService;
import com.drive.system.service.RoleDeptService;
import com.drive.system.service.mapstruct.DeptMapStruct;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 部门管理
 *
 * @author xiaoguo
 */
@Api(tags = "部门管理")
@Slf4j
@RestController
@RequestMapping("/dept")
public class DeptController extends BaseController<DeptPageQueryParam, DeptEntity> {

    @Autowired
    private DeptService deptService;
    @Autowired
    private DeptMapStruct deptMapStruct;

    @Autowired
    private RoleDeptService roleDeptService;
    @Autowired
    private UserRepository userRepository;

    /**
     * 部门 分页列表
     */
    @ApiOperation("部门分页列表")
    @PreAuthorize("hasPermission('/dept',  'system:dept:query')")
    @GetMapping(value = "/pageList")
    public ResObject pageList(@Valid DeptPageQueryParam param) {
        Page<DeptEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<DeptEntity> pageList = deptService.page(page, this.getQueryWrapper(deptMapStruct, param));
        Page<DeptVo> deptVoPage = deptMapStruct.toVoList(pageList);
        return R.success(deptVoPage);
    }


    /**
     * 部门列表
     */
    @ApiOperation("部门列表")
    @PreAuthorize("hasPermission('/dept',  'system:dept:query')")
    @GetMapping(value = "/allList")
    public ResObject allList() {
        List<DeptEntity> allList = deptService.list();
        List<DeptVo> allVoList = deptMapStruct.toVoList(allList);
        return R.success(allVoList);
    }

    /**
     * 获取部门
     */
    @ApiOperation("获取部门")
    @ApiImplicitParam(name = "deptId", required = true, dataType = "Long", paramType = "path")
    @PreAuthorize("hasPermission('/dept',  'system:dept:query')")
    @GetMapping("/{deptId}")
    public ResObject get(@PathVariable Long deptId) {
        DeptEntity dept = deptService.getById(deptId);
        return R.success(dept);
    }
    /**
     * 获取部门
     */
    @ApiOperation("获取部门")
    @ApiImplicitParam(name = "deptId", required = true, dataType = "Long", paramType = "path")
    @PreAuthorize("hasPermission('/dept',  'system:dept:query')")
    @GetMapping("/getDeptByRoleId/{roleId}")
    public ResObject<RoleDeptVo> getDeptByRoleId(@PathVariable Long roleId) {
        if (roleId == null){
            return R.failure(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id",roleId);
        List<RoleDeptEntity> roleDeptList = roleDeptService.list(queryWrapper);
        return R.success(BeanConvertUtils.copyList(roleDeptList,com.drive.system.pojo.vo.RoleDeptVo.class));
    }

    /**
     * 新增部门
     */
    @ApiOperation("新增部门")
    @ApiImplicitParam(name = "DeptEditParam ", value = "新增部门", dataType = "DeptEditParam")
    @PreAuthorize("hasPermission('/dept',  'system:dept:add')")
    @EventLog(message = "新增部门", businessType = EventLogEnum.CREATE)
    @PostMapping
    public ResObject save(@Valid @RequestBody DeptEditParam deptEditParam) {
        DeptEntity deptEntity = deptService.lambdaQuery().eq(DeptEntity::getDeptName, deptEditParam.getDeptName())
                                    .eq(DeptEntity::getParentId, deptEditParam.getParentId()).one();
        if (deptEntity != null) {
            return R.failure("部门名称已存在, 请使用其他部门名称");
        }

        DeptEntity dept = deptMapStruct.toEntity(deptEditParam);
        return R.toRes(deptService.insertDept(dept));
    }

    /**
     * 修改部门
     */
    @ApiOperation("修改部门")
    @ApiImplicitParam(name = "DeptEditParam ", value = "修改部门", dataType = "DeptEditParam")
    @PreAuthorize("hasPermission('/dept',  'system:dept:edit')")
    @EventLog(message = "修改部门", businessType = EventLogEnum.UPDATE)
    @PutMapping
    public ResObject edit(@Valid @RequestBody DeptEditParam deptEditParam) {
        DeptEntity dept = deptMapStruct.toEntity(deptEditParam);
        return R.toRes(deptService.updateById(dept));
    }

    /**
     * 删除部门
     */
    @ApiOperation("删除部门")
    @ApiImplicitParam(name = "deptId", required = true, dataType = "Long", paramType = "path")
    @PreAuthorize("hasPermission('/dept',  'system:dept:delete')")
    @EventLog(message = "删除部门", businessType = EventLogEnum.DELETE)
    @DeleteMapping("/{deptIds}")
    public ResObject delete(@PathVariable Long[] deptIds) {
        return R.toRes(deptService.removeByIds(Arrays.asList(deptIds)));
    }

    /**
     * 导出部门
     */
    @ApiOperation("导出部门")
    @PreAuthorize("hasPermission('/dept',  'system:dept:export')")
    @SneakyThrows
    @EventLog(message = "导出部门", businessType = EventLogEnum.EXPORT)
    @PostMapping(value = "/exportXls")
    public void exportXls(DeptPageQueryParam param, HttpServletResponse response) {
        List<DeptEntity> list = deptService.list(this.getQueryWrapper(deptMapStruct, param));
        List<DeptVo> deptVoList = deptMapStruct.toVoList(list);
        ExcelUtils.exportExcel(deptVoList, DeptVo.class, "部门", new ExportParams(), response);
    }

    /**
     * 获取部门下拉树列表
     */
    @GetMapping("/treeselect")
    public ResObject treeselect(@Valid DeptPageQueryParam param) {
        List<DeptEntity> depts = deptService.list(this.getQueryWrapper(deptMapStruct, param));
        List<DeptVo> deptVos = deptMapStruct.toVoList(depts);
        return R.success(deptService.buildDeptTreeSelect(deptVos));
    }

    /**
     * 加载对应角色部门列表树
     */
    @GetMapping(value = "/roleDeptTreeselect/{roleId}")
    public ResObject roleDeptTreeselect(@PathVariable("roleId") Long roleId) {
        List<DeptEntity> list = deptService.list();
        List<DeptVo> deptVos = deptMapStruct.toVoList(list);

        Map result = new HashMap();
        result.put("checkedKeys", deptService.selectDeptListByRoleId(roleId));
        result.put("depts", deptService.buildDeptTreeSelect(deptVos));
        return R.success(result);
    }

    /**
     * 初始化缓存
     * @return
     */
    @GetMapping(value = "/initCache")
    public ResObject initCache(){
        deptService.init();
        roleDeptService.init();
        userRepository.init();
        return R.success();
    }

}
