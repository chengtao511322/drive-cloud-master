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
import com.drive.admin.service.QuestionOptionsService;
import com.drive.admin.repository.QuestionOptionsRepository;


/**
 * 平台题目选项管理
 *
 * @author chentao
 */
@Api(tags = "平台题目选项管理")
@Slf4j
@RestController
@RequestMapping("/questionOptions")
public class QuestionOptionsController extends BaseController<QuestionOptionsPageQueryParam, QuestionOptionsEntity> {

	// 平台题目选项 服务
	@Autowired
	private QuestionOptionsService questionOptionsService;
	// 平台题目选项 业务服务
	@Autowired
	private QuestionOptionsRepository questionOptionsRepository;
	// 平台题目选项 DO-DTO转化
	@Autowired
	private QuestionOptionsMapStruct questionOptionsMapStruct;

	/**
	* 平台题目选项 分页列表
	*/
	@ApiOperation("平台题目选项分页列表")
	@PreAuthorize("hasPermission('/admin/questionOptions',  'admin:questionOptions:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody QuestionOptionsPageQueryParam param) {
		return questionOptionsRepository.pageList(param);
	}
	/**
	* 平台题目选项 列表
	*/
	@ApiOperation("平台题目选项列表")
	@PreAuthorize("hasPermission('/admin/questionOptions',  'admin:questionOptions:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody QuestionOptionsPageQueryParam param) {
		return questionOptionsRepository.findList(param);
	}

	/**
	* 获取平台题目选项
	*/
	@ApiOperation("获取平台题目选项")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/questionOptions',  'admin:questionOptions:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return questionOptionsRepository.getById(id);
	}

	/**
	 * 条件查询获取平台题目选项
	 */
	@ApiOperation("条件查询获取平台题目选项")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/questionOptions',  'admin:questionOptions:query')")
	@PostMapping("/getInfo")
	public ResObject getInfo(@RequestBody QuestionOptionsPageQueryParam param) {
		return questionOptionsRepository.getInfo(param);
	}

	/**
	* 新增平台题目选项
	 * author:xiaoguo
	*/
	@ApiOperation("新增平台题目选项")
	@ApiImplicitParam(name = "QuestionOptionsEditParam ", value = "新增平台题目选项", dataType = "QuestionOptionsEditParam")
	@PreAuthorize("hasPermission('/admin/questionOptions',  'admin:questionOptions:add')")
	@EventLog(message = "新增平台题目选项", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody QuestionOptionsInstallParam questionOptionsInstallParam) {
		return questionOptionsRepository.save(questionOptionsInstallParam);
	}

	/**
	* 修改平台题目选项
	*/
	@ApiOperation("修改平台题目选项")
	@ApiImplicitParam(name = "QuestionOptionsEditParam ", value = "修改平台题目选项", dataType = "QuestionOptionsEditParam")
	@PreAuthorize("hasPermission('/admin/questionOptions',  'admin:questionOptions:edit')")
	@EventLog(message = "修改平台题目选项", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody QuestionOptionsEditParam questionOptionsEditParam) {
		return questionOptionsRepository.update(questionOptionsEditParam);
	}

	/**
	* 删除平台题目选项
	*/
	@ApiOperation("删除平台题目选项")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/questionOptions',  'admin:questionOptions:delete')")
	@EventLog(message = "删除平台题目选项", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable String[] ids) {
		return R.toRes(questionOptionsService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除平台题目选项
	*/
	@ApiOperation("通过主键删除平台题目选项")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delete')")
	@EventLog(message = "通过主键删除平台题目选项", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return questionOptionsRepository.deleteById(id);
	}

	/**
	* 导出平台题目选项
	*/
	@ApiOperation("导出平台题目选项")
	@PreAuthorize("hasPermission('/admin/questionOptions',  'admin:questionOptions:export')")
	@SneakyThrows
	@EventLog(message = "导出平台题目选项", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody QuestionOptionsPageQueryParam param, HttpServletResponse response) {
		questionOptionsRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	/*@ApiOperation("状态启用/停用平台题目选项")
	@PreAuthorize("hasPermission('/admin/questionOptions',  'admin:questionOptions:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用平台题目选项", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody QuestionOptionsEditParam questionOptionsEditParam) {
		return questionOptionsRepository.changeStatus(questionOptionsEditParam);
	}*/

}
