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
import com.drive.admin.service.AppVersionService;
import com.drive.admin.repository.AppVersionRepository;


/**
 * 平台应用版本表管理
 *
 * @author xiaoguo
 */
@Api(tags = "平台应用版本表管理")
@Slf4j
@RestController
@RequestMapping("/appVersion")
public class AppVersionController extends BaseController<AppVersionPageQueryParam, AppVersionEntity> {

	// 平台应用版本表 服务
	@Autowired
	private AppVersionService appVersionService;
	// 平台应用版本表 业务服务
	@Autowired
	private AppVersionRepository appVersionRepository;
	// 平台应用版本表 DO-DTO转化
	@Autowired
	private AppVersionMapStruct appVersionMapStruct;

	/**
	* 平台应用版本表 分页列表
	*/
	@ApiOperation("平台应用版本表分页列表")
	@PreAuthorize("hasPermission('/admin/appVersion',  'admin:appVersion:query')")
	@GetMapping(value = "/pageList")
	public ResObject pageList(@Valid AppVersionPageQueryParam param) {
		return appVersionRepository.pageList(param);
	}
	/**
	* 平台应用版本表 列表
	*/
	@ApiOperation("平台应用版本表列表")
	@PreAuthorize("hasPermission('/admin/appVersion',  'admin:appVersion:query')")
	@GetMapping(value = "/findList")
	public ResObject findList(@Valid AppVersionPageQueryParam param) {
		return appVersionRepository.findList(param);
	}

	/**
	* 获取平台应用版本表
	*/
	@ApiOperation("获取平台应用版本表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/appVersion',  'admin:appVersion:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return appVersionRepository.getById(id);
	}

	/**
	 * 条件查询获取平台应用版本表
	 */
	@ApiOperation("条件查询获取平台应用版本表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/appVersion',  'admin:appVersion:query')")
	@GetMapping("/getInfo")
	public ResObject getInfo(@PathVariable String id) {
		return appVersionRepository.getById(id);
	}

	/**
	* 新增平台应用版本表
	*/
	@ApiOperation("新增平台应用版本表")
	@ApiImplicitParam(name = "AppVersionEditParam ", value = "新增平台应用版本表", dataType = "AppVersionEditParam")
	@PreAuthorize("hasPermission('/admin/appVersion',  'admin:appVersion:add')")
	@EventLog(message = "新增平台应用版本表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody AppVersionEditParam appVersionEditParam) {
		return appVersionRepository.save(appVersionEditParam);
	}

	/**
	* 修改平台应用版本表
	*/
	@ApiOperation("修改平台应用版本表")
	@ApiImplicitParam(name = "AppVersionEditParam ", value = "修改平台应用版本表", dataType = "AppVersionEditParam")
	@PreAuthorize("hasPermission('/admin/appVersion',  'admin:appVersion:edit')")
	@EventLog(message = "修改平台应用版本表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody AppVersionEditParam appVersionEditParam) {
		return appVersionRepository.update(appVersionEditParam);
	}

	/**
	* 删除平台应用版本表
	*/
	@ApiOperation("删除平台应用版本表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/appVersion',  'admin:appVersion:delete')")
	@EventLog(message = "删除平台应用版本表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(appVersionService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除平台应用版本表
	*/
	@ApiOperation("通过主键删除平台应用版本表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除平台应用版本表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return appVersionRepository.deleteById(id);
	}

	/**
	* 导出平台应用版本表
	*/
	@ApiOperation("导出平台应用版本表")
	@PreAuthorize("hasPermission('/admin/appVersion',  'admin:appVersion:export')")
	@SneakyThrows
	@EventLog(message = "导出平台应用版本表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(AppVersionPageQueryParam param, HttpServletResponse response) {
		appVersionRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用平台应用版本表")
	@PreAuthorize("hasPermission('/admin/appVersion',  'admin:appVersion:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用平台应用版本表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody AppVersionEditParam appVersionEditParam) {
		return appVersionRepository.changeStatus(appVersionEditParam);
	}

}
