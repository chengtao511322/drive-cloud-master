package com.drive.admin.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
import com.drive.admin.service.SysTaskService;
import com.drive.admin.repository.SysTaskRepository;


/**
 * 系统任务表管理
 *
 * @author chentao
 */
@Api(tags = "系统任务表管理")
@Slf4j
@RestController
@RequestMapping("/sysTask")
public class SysTaskController extends BaseController<SysTaskPageQueryParam, SysTaskEntity> {

	// 系统任务表 服务
	@Autowired
	private SysTaskService sysTaskService;
	// 系统任务表 业务服务
	@Autowired
	private SysTaskRepository sysTaskRepository;
	// 系统任务表 DO-DTO转化
	@Autowired
	private SysTaskMapStruct sysTaskMapStruct;

	/**
	* 系统任务表 分页列表
	*/
	@ApiOperation("系统任务表分页列表")
	@PreAuthorize("hasPermission('/admin/sysTask',  'admin:sysTask:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody SysTaskPageQueryParam param) {
		return sysTaskRepository.pageList(param);
	}
	/**
	* 系统任务表 列表
	*/
	@ApiOperation("系统任务表列表")
	@PreAuthorize("hasPermission('/admin/sysTask',  'admin:sysTask:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody SysTaskPageQueryParam param) {
		return sysTaskRepository.findList(param);
	}

	/**
	* 获取系统任务表
	*/
	@ApiOperation("获取系统任务表")
	@ApiImplicitParam(name = "stTaskId", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/sysTask',  'admin:sysTask:query')")
	@GetMapping("/{stTaskId}")
	public ResObject get(@PathVariable String stTaskId) {
		return sysTaskRepository.getById(stTaskId);
	}

	/**
	 * 条件查询获取系统任务表
	 */
	@ApiOperation("条件查询获取系统任务表")
	@ApiImplicitParam(name = "stTaskId", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/sysTask',  'admin:sysTask:query')")
	@PostMapping("/getInfo")
	public ResObject getInfo(@RequestBody SysTaskPageQueryParam param) {
		return sysTaskRepository.getInfo(param);
	}

	/**
	* 新增系统任务表
	 * author:xiaoguo
	*/
	@ApiOperation("新增系统任务表")
	@ApiImplicitParam(name = "SysTaskEditParam ", value = "新增系统任务表", dataType = "SysTaskEditParam")
	@PreAuthorize("hasPermission('/admin/sysTask',  'admin:sysTask:add')")
	@EventLog(message = "新增系统任务表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody SysTaskInstallParam sysTaskInstallParam) {
		return sysTaskRepository.save(sysTaskInstallParam);
	}

	/**
	* 修改系统任务表
	*/
	@ApiOperation("修改系统任务表")
	@ApiImplicitParam(name = "SysTaskEditParam ", value = "修改系统任务表", dataType = "SysTaskEditParam")
	@PreAuthorize("hasPermission('/admin/sysTask',  'admin:sysTask:edit')")
	@EventLog(message = "修改系统任务表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody SysTaskEditParam sysTaskEditParam) {
		return sysTaskRepository.update(sysTaskEditParam);
	}

	/**
	* 删除系统任务表
	*/
	@ApiOperation("删除系统任务表")
	@ApiImplicitParam(name = "stTaskId", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/sysTask',  'admin:sysTask:delete')")
	@EventLog(message = "删除系统任务表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{stTaskIds}")
	public ResObject delete(@PathVariable String[] stTaskIds) {
		return R.toRes(sysTaskService.removeByIds(Arrays.asList(stTaskIds)));
	}

	/**
	* 通过主键删除系统任务表
	*/
	@ApiOperation("通过主键删除系统任务表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delete')")
	@EventLog(message = "通过主键删除系统任务表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return sysTaskRepository.deleteById(id);
	}

	/**
	* 导出系统任务表
	*/
	@ApiOperation("导出系统任务表")
	@PreAuthorize("hasPermission('/admin/sysTask',  'admin:sysTask:export')")
	@SneakyThrows
	@EventLog(message = "导出系统任务表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody SysTaskPageQueryParam param, HttpServletResponse response) {
		sysTaskRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用系统任务表")
	@PreAuthorize("hasPermission('/admin/sysTask',  'admin:sysTask:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用系统任务表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody SysTaskEditParam sysTaskEditParam) {
		return sysTaskRepository.changeStatus(sysTaskEditParam);
	}

}
