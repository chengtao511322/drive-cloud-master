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
import com.drive.admin.service.PayFlowLogService;
import com.drive.admin.repository.PayFlowLogRepository;


/**
 * 平台交易流水日志管理
 *
 * @author xiaoguo
 */
@Api(tags = "平台交易流水日志管理")
@Slf4j
@RestController
@RequestMapping("/payFlowLog")
public class PayFlowLogController extends BaseController<PayFlowLogPageQueryParam, PayFlowLogEntity> {

	// 平台交易流水日志 服务
	@Autowired
	private PayFlowLogService payFlowLogService;
	// 平台交易流水日志 业务服务
	@Autowired
	private PayFlowLogRepository payFlowLogRepository;
	// 平台交易流水日志 DO-DTO转化
	@Autowired
	private PayFlowLogMapStruct payFlowLogMapStruct;

	/**
	* 平台交易流水日志 分页列表
	*/
	@ApiOperation("平台交易流水日志分页列表")
	@PreAuthorize("hasPermission('/admin/payFlowLog',  'admin:payFlowLog:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody PayFlowLogPageQueryParam param) {
		return payFlowLogRepository.pageList(param);
	}
	/**
	* 平台交易流水日志 列表
	*/
	@ApiOperation("平台交易流水日志列表")
	@PreAuthorize("hasPermission('/admin/payFlowLog',  'admin:payFlowLog:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody PayFlowLogPageQueryParam param) {
		return payFlowLogRepository.findList(param);
	}

	/**
	* 获取平台交易流水日志
	*/
	@ApiOperation("获取平台交易流水日志")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/payFlowLog',  'admin:payFlowLog:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return payFlowLogRepository.getById(id);
	}

	/**
	 * 条件查询获取平台交易流水日志
	 */
	@ApiOperation("条件查询获取平台交易流水日志")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/payFlowLog',  'admin:payFlowLog:query')")
	@PostMapping("/getInfo")
	public ResObject getInfo(@RequestBody PayFlowLogPageQueryParam param) {
		return payFlowLogRepository.getInfo(param);
	}

	/**
	* 新增平台交易流水日志
	 * author:xiaoguo
	*/
	@ApiOperation("新增平台交易流水日志")
	@ApiImplicitParam(name = "PayFlowLogEditParam ", value = "新增平台交易流水日志", dataType = "PayFlowLogEditParam")
	@PreAuthorize("hasPermission('/admin/payFlowLog',  'admin:payFlowLog:add')")
	@EventLog(message = "新增平台交易流水日志", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody PayFlowLogInstallParam payFlowLogInstallParam) {
		return payFlowLogRepository.save(payFlowLogInstallParam);
	}

	/**
	* 修改平台交易流水日志
	*/
	@ApiOperation("修改平台交易流水日志")
	@ApiImplicitParam(name = "PayFlowLogEditParam ", value = "修改平台交易流水日志", dataType = "PayFlowLogEditParam")
	@PreAuthorize("hasPermission('/admin/payFlowLog',  'admin:payFlowLog:edit')")
	@EventLog(message = "修改平台交易流水日志", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody PayFlowLogEditParam payFlowLogEditParam) {
		return payFlowLogRepository.update(payFlowLogEditParam);
	}

	/**
	* 删除平台交易流水日志
	*/
	@ApiOperation("删除平台交易流水日志")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/payFlowLog',  'admin:payFlowLog:delete')")
	@EventLog(message = "删除平台交易流水日志", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable String[] ids) {
		return R.toRes(payFlowLogService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除平台交易流水日志
	*/
	@ApiOperation("通过主键删除平台交易流水日志")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delete')")
	@EventLog(message = "通过主键删除平台交易流水日志", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return payFlowLogRepository.deleteById(id);
	}

	/**
	* 导出平台交易流水日志
	*/
	@ApiOperation("导出平台交易流水日志")
	@PreAuthorize("hasPermission('/admin/payFlowLog',  'admin:payFlowLog:export')")
	@SneakyThrows
	@EventLog(message = "导出平台交易流水日志", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody PayFlowLogPageQueryParam param, HttpServletResponse response) {
		payFlowLogRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用平台交易流水日志")
	@PreAuthorize("hasPermission('/admin/payFlowLog',  'admin:payFlowLog:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用平台交易流水日志", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody PayFlowLogEditParam payFlowLogEditParam) {
		return payFlowLogRepository.changeStatus(payFlowLogEditParam);
	}

}
