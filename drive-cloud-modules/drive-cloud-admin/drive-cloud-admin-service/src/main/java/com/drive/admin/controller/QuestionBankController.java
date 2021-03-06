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
import com.drive.admin.service.QuestionBankService;
import com.drive.admin.repository.QuestionBankRepository;


/**
 * 平台题库表管理
 *
 * @author chentao
 */
@Api(tags = "平台题库表管理")
@Slf4j
@RestController
@RequestMapping("/questionBank")
public class QuestionBankController extends BaseController<QuestionBankPageQueryParam, QuestionBankEntity> {

	// 平台题库表 服务
	@Autowired
	private QuestionBankService questionBankService;
	// 平台题库表 业务服务
	@Autowired
	private QuestionBankRepository questionBankRepository;
	// 平台题库表 DO-DTO转化
	@Autowired
	private QuestionBankMapStruct questionBankMapStruct;

	/**
	* 平台题库表 分页列表
	*/
	@ApiOperation("平台题库表分页列表")
	@PreAuthorize("hasPermission('/admin/questionBank',  'admin:questionBank:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody QuestionBankPageQueryParam param) {
		return questionBankRepository.pageList(param);
	}
	/**
	* 平台题库表 列表
	*/
	@ApiOperation("平台题库表列表")
	@PreAuthorize("hasPermission('/admin/questionBank',  'admin:questionBank:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody QuestionBankPageQueryParam param) {
		return questionBankRepository.findList(param);
	}

	/**
	* 获取平台题库表
	*/
	@ApiOperation("获取平台题库表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/questionBank',  'admin:questionBank:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return questionBankRepository.getById(id);
	}

	/**
	 * 条件查询获取平台题库表
	 */
	@ApiOperation("条件查询获取平台题库表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/questionBank',  'admin:questionBank:query')")
	@PostMapping("/getInfo")
	public ResObject getInfo(@RequestBody QuestionBankPageQueryParam param) {
		return questionBankRepository.getInfo(param);
	}

	/**
	* 新增平台题库表
	 * author:xiaoguo
	*/
	@ApiOperation("新增平台题库表")
	@ApiImplicitParam(name = "QuestionBankEditParam ", value = "新增平台题库表", dataType = "QuestionBankEditParam")
	@PreAuthorize("hasPermission('/admin/questionBank',  'admin:questionBank:add')")
	@EventLog(message = "新增平台题库表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody QuestionBankInstallParam questionBankInstallParam) {
		return questionBankRepository.save(questionBankInstallParam);
	}

	/**
	* 修改平台题库表
	*/
	@ApiOperation("修改平台题库表")
	@ApiImplicitParam(name = "QuestionBankEditParam ", value = "修改平台题库表", dataType = "QuestionBankEditParam")
	@PreAuthorize("hasPermission('/admin/questionBank',  'admin:questionBank:edit')")
	@EventLog(message = "修改平台题库表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody QuestionBankEditParam questionBankEditParam) {
		return questionBankRepository.update(questionBankEditParam);
	}

	/**
	* 删除平台题库表
	*/
	@ApiOperation("删除平台题库表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/questionBank',  'admin:questionBank:delete')")
	@EventLog(message = "删除平台题库表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable String[] ids) {
		return R.toRes(questionBankService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除平台题库表
	*/
	@ApiOperation("通过主键删除平台题库表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delete')")
	@EventLog(message = "通过主键删除平台题库表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return questionBankRepository.deleteById(id);
	}

	/**
	* 导出平台题库表
	*/
	@ApiOperation("导出平台题库表")
	@PreAuthorize("hasPermission('/admin/questionBank',  'admin:questionBank:export')")
	@SneakyThrows
	@EventLog(message = "导出平台题库表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody QuestionBankPageQueryParam param, HttpServletResponse response) {
		questionBankRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	/*@ApiOperation("状态启用/停用平台题库表")
	@PreAuthorize("hasPermission('/admin/questionBank',  'admin:questionBank:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用平台题库表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody QuestionBankEditParam questionBankEditParam) {
		return questionBankRepository.changeStatus(questionBankEditParam);
	}*/

}
