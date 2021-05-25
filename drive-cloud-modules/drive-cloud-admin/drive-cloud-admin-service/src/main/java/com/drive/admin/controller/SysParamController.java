package com.drive.admin.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.common.security.utils.SecurityUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Arrays;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.data.utils.ExcelUtils;
import com.drive.common.log.annotation.EventLog;
import io.swagger.annotations.Api;
import com.drive.common.core.base.BaseController;
import com.drive.admin.pojo.entity.*;
import com.drive.admin.pojo.vo.*;
import com.drive.admin.pojo.dto.*;
import com.drive.admin.service.mapstruct.*;
import com.drive.admin.service.SysParamService;
import com.drive.admin.repository.SysParamRepository;


/**
 * 系统配置参数表管理
 *
 * @author chentao
 */
@Api(tags = "系统配置参数表管理")
@Slf4j
@RestController
@RequestMapping("/sysParam")
public class SysParamController extends BaseController<SysParamPageQueryParam, SysParamEntity> {

	// 系统配置参数表 服务
	@Autowired
	private SysParamService sysParamService;
	// 系统配置参数表 业务服务
	@Autowired
	private SysParamRepository sysParamRepository;
	// 系统配置参数表 DO-DTO转化
	@Autowired
	private SysParamMapStruct sysParamMapStruct;

	/**
	* 系统配置参数表 分页列表
	*/
	@ApiOperation("系统配置参数表分页列表")
	@PreAuthorize("hasPermission('/admin/sysParam',  'admin:sysParam:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody SysParamPageQueryParam param) {
		return sysParamRepository.pageList(param);
	}
	/**
	* 系统配置参数表 列表
	*/
	@ApiOperation("系统配置参数表列表")
	@PreAuthorize("hasPermission('/admin/sysParam',  'admin:sysParam:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody SysParamPageQueryParam param) {
		return sysParamRepository.findList(param);
	}

	/**
	* 获取系统配置参数表
	*/
	@ApiOperation("获取系统配置参数表")
	@ApiImplicitParam(name = "prmEnumId", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/sysParam',  'admin:sysParam:query')")
	@GetMapping("/{prmEnumId}")
	public ResObject get(@PathVariable String prmEnumId) {
		return sysParamRepository.getById(prmEnumId);
	}

	/**
	 * 条件查询获取系统配置参数表
	 */
	@ApiOperation("条件查询获取系统配置参数表")
	@ApiImplicitParam(name = "prmEnumId", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/sysParam',  'admin:sysParam:query')")
	@PostMapping("/getInfo")
	public ResObject getInfo(@RequestBody SysParamPageQueryParam param) {
		return sysParamRepository.getInfo(param);
	}

	/**
	* 新增系统配置参数表
	 * author:xiaoguo
	*/
	@ApiOperation("新增系统配置参数表")
	@ApiImplicitParam(name = "SysParamEditParam ", value = "新增系统配置参数表", dataType = "SysParamEditParam")
	@PreAuthorize("hasPermission('/admin/sysParam',  'admin:sysParam:add')")
	@EventLog(message = "新增系统配置参数表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody SysParamInstallParam sysParamInstallParam) {
		//创建人
		sysParamInstallParam.setCreateUser(SecurityUtils.getUsername());
		return sysParamRepository.save(sysParamInstallParam);
	}

	/**
	* 修改系统配置参数表
	*/
	@ApiOperation("修改系统配置参数表")
	@ApiImplicitParam(name = "SysParamEditParam ", value = "修改系统配置参数表", dataType = "SysParamEditParam")
	@PreAuthorize("hasPermission('/admin/sysParam',  'admin:sysParam:edit')")
	@EventLog(message = "修改系统配置参数表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody SysParamEditParam sysParamEditParam) {
		//更新人
		sysParamEditParam.setUpdateUser(SecurityUtils.getUsername());
		return sysParamRepository.update(sysParamEditParam);
	}

	/**
	* 删除系统配置参数表
	*/
	@ApiOperation("删除系统配置参数表")
	@ApiImplicitParam(name = "prmEnumId", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/sysParam',  'admin:sysParam:delete')")
	@EventLog(message = "删除系统配置参数表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{prmEnumIds}")
	public ResObject delete(@PathVariable String[] prmEnumIds) {
		return R.toRes(sysParamService.removeByIds(Arrays.asList(prmEnumIds)));
	}

	/**
	* 通过主键删除系统配置参数表
	*/
	@ApiOperation("通过主键删除系统配置参数表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delete')")
	@EventLog(message = "通过主键删除系统配置参数表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return sysParamRepository.deleteById(id);
	}

	/**
	* 导出系统配置参数表
	*/
	@ApiOperation("导出系统配置参数表")
	@PreAuthorize("hasPermission('/admin/sysParam',  'admin:sysParam:export')")
	@SneakyThrows
	@EventLog(message = "导出系统配置参数表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody SysParamPageQueryParam param, HttpServletResponse response) {
		sysParamRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用系统配置参数表")
	@PreAuthorize("hasPermission('/admin/sysParam',  'admin:sysParam:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用系统配置参数表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody SysParamEditParam sysParamEditParam) {
		return sysParamRepository.changeStatus(sysParamEditParam);
	}

}
