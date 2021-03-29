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
import com.drive.admin.service.EvaluateTagService;
import com.drive.admin.repository.EvaluateTagRepository;


/**
 * 评价标签表管理
 *
 * @author xiaoguo
 */
@Api(tags = "评价标签表管理")
@Slf4j
@RestController
@RequestMapping("/evaluateTag")
public class EvaluateTagController extends BaseController<EvaluateTagPageQueryParam, EvaluateTagEntity> {

	// 评价标签表 服务
	@Autowired
	private EvaluateTagService evaluateTagService;
	// 评价标签表 业务服务
	@Autowired
	private EvaluateTagRepository evaluateTagRepository;
	// 评价标签表 DO-DTO转化
	@Autowired
	private EvaluateTagMapStruct evaluateTagMapStruct;

	/**
	* 评价标签表 分页列表
	*/
	@ApiOperation("评价标签表分页列表")
	@PreAuthorize("hasPermission('/admin/evaluateTag',  'admin:evaluateTag:query')")
	@GetMapping(value = "/pageList")
	public ResObject pageList(@Valid EvaluateTagPageQueryParam param) {
		return evaluateTagRepository.pageList(param);
	}
	/**
	* 评价标签表 列表
	*/
	@ApiOperation("评价标签表列表")
	@PreAuthorize("hasPermission('/admin/evaluateTag',  'admin:evaluateTag:query')")
	@GetMapping(value = "/findList")
	public ResObject findList(@Valid EvaluateTagPageQueryParam param) {
		return evaluateTagRepository.findList(param);
	}

	/**
	* 获取评价标签表
	*/
	@ApiOperation("获取评价标签表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/evaluateTag',  'admin:evaluateTag:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return evaluateTagRepository.getById(id);
	}

	/**
	 * 条件查询获取评价标签表
	 */
	@ApiOperation("条件查询获取评价标签表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/evaluateTag',  'admin:evaluateTag:query')")
	@GetMapping("/getInfo")
	public ResObject getInfo(@PathVariable EvaluateTagPageQueryParam param) {
		return evaluateTagRepository.getInfo(param);
	}

	/**
	* 新增评价标签表
	*/
	@ApiOperation("新增评价标签表")
	@ApiImplicitParam(name = "EvaluateTagEditParam ", value = "新增评价标签表", dataType = "EvaluateTagEditParam")
	@PreAuthorize("hasPermission('/admin/evaluateTag',  'admin:evaluateTag:add')")
	@EventLog(message = "新增评价标签表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody EvaluateTagInstallParam evaluateTagInstallParam) {
		return evaluateTagRepository.save(evaluateTagInstallParam);
	}

	/**
	* 修改评价标签表
	*/
	@ApiOperation("修改评价标签表")
	@ApiImplicitParam(name = "EvaluateTagEditParam ", value = "修改评价标签表", dataType = "EvaluateTagEditParam")
	@PreAuthorize("hasPermission('/admin/evaluateTag',  'admin:evaluateTag:edit')")
	@EventLog(message = "修改评价标签表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody EvaluateTagEditParam evaluateTagEditParam) {
		return evaluateTagRepository.update(evaluateTagEditParam);
	}

	/**
	* 删除评价标签表
	*/
	@ApiOperation("删除评价标签表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/evaluateTag',  'admin:evaluateTag:delete')")
	@EventLog(message = "删除评价标签表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable String[] ids) {
		return R.toRes(evaluateTagService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除评价标签表
	*/
	@ApiOperation("通过主键删除评价标签表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除评价标签表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return evaluateTagRepository.deleteById(id);
	}

	/**
	* 导出评价标签表
	*/
	@ApiOperation("导出评价标签表")
	@PreAuthorize("hasPermission('/admin/evaluateTag',  'admin:evaluateTag:export')")
	@SneakyThrows
	@EventLog(message = "导出评价标签表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(EvaluateTagPageQueryParam param, HttpServletResponse response) {
		evaluateTagRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用评价标签表")
	@PreAuthorize("hasPermission('/admin/evaluateTag',  'admin:evaluateTag:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用评价标签表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody EvaluateTagEditParam evaluateTagEditParam) {
		return evaluateTagRepository.changeStatus(evaluateTagEditParam);
	}

}
