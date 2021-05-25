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
import com.drive.admin.service.CodeService;
import com.drive.admin.repository.CodeRepository;


/**
 * 字典表管理
 *
 * @author chentao
 */
@Api(tags = "字典表管理")
@Slf4j
@RestController
@RequestMapping("/code")
public class CodeController extends BaseController<CodePageQueryParam, CodeEntity> {

	// 字典表 服务
	@Autowired
	private CodeService codeService;
	// 字典表 业务服务
	@Autowired
	private CodeRepository codeRepository;
	// 字典表 DO-DTO转化
	@Autowired
	private CodeMapStruct codeMapStruct;

	/**
	* 字典表 分页列表
	*/
	@ApiOperation("字典表分页列表")
	@PreAuthorize("hasPermission('/admin/code',  'admin:code:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody CodePageQueryParam param) {
		return codeRepository.pageList(param);
	}
	/**
	* 字典表 列表
	*/
	@ApiOperation("字典表列表")
	@PreAuthorize("hasPermission('/admin/code',  'admin:code:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody CodePageQueryParam param) {
		return codeRepository.findList(param);
	}

	/**
	* 获取字典表
	*/
	@ApiOperation("获取字典表")
	@ApiImplicitParam(name = "codeId", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/code',  'admin:code:query')")
	@GetMapping("/{codeId}")
	public ResObject get(@PathVariable String codeId) {
		return codeRepository.getById(codeId);
	}

	/**
	 * 条件查询获取字典表
	 */
	@ApiOperation("条件查询获取字典表")
	@ApiImplicitParam(name = "codeId", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/code',  'admin:code:query')")
	@PostMapping("/getInfo")
	public ResObject getInfo(@RequestBody CodePageQueryParam param) {
		return codeRepository.getInfo(param);
	}

	/**
	* 新增字典表
	 * author:xiaoguo
	*/
	@ApiOperation("新增字典表")
	@ApiImplicitParam(name = "CodeEditParam ", value = "新增字典表", dataType = "CodeEditParam")
	@PreAuthorize("hasPermission('/admin/code',  'admin:code:add')")
	@EventLog(message = "新增字典表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody CodeInstallParam codeInstallParam) {
		return codeRepository.save(codeInstallParam);
	}

	/**
	* 修改字典表
	*/
	@ApiOperation("修改字典表")
	@ApiImplicitParam(name = "CodeEditParam ", value = "修改字典表", dataType = "CodeEditParam")
	@PreAuthorize("hasPermission('/admin/code',  'admin:code:edit')")
	@EventLog(message = "修改字典表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody CodeEditParam codeEditParam) {
		return codeRepository.update(codeEditParam);
	}

	/**
	* 删除字典表
	*/
	@ApiOperation("删除字典表")
	@ApiImplicitParam(name = "codeId", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/code',  'admin:code:delete')")
	@EventLog(message = "删除字典表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{codeIds}")
	public ResObject delete(@PathVariable String[] codeIds) {
		return R.toRes(codeService.removeByIds(Arrays.asList(codeIds)));
	}

	/**
	* 通过主键删除字典表
	*/
	@ApiOperation("通过主键删除字典表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delete')")
	@EventLog(message = "通过主键删除字典表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return codeRepository.deleteById(id);
	}

	/**
	* 导出字典表
	*/
	@ApiOperation("导出字典表")
	@PreAuthorize("hasPermission('/admin/code',  'admin:code:export')")
	@SneakyThrows
	@EventLog(message = "导出字典表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody CodePageQueryParam param, HttpServletResponse response) {
		codeRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用字典表")
	@PreAuthorize("hasPermission('/admin/code',  'admin:code:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用字典表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody CodeEditParam codeEditParam) {
		return codeRepository.changeStatus(codeEditParam);
	}

}
