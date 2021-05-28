package com.drive.admin.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParams;
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
import com.drive.admin.service.WalletSettlementSummaryService;
import com.drive.admin.repository.WalletSettlementSummaryRepository;


/**
 * 管理
 *
 * @author chentao
 */
@Api(tags = "钱包清算汇总管理")
@Slf4j
@RestController
@RequestMapping("/walletSettlementSummary")
public class WalletSettlementSummaryController extends BaseController<WalletSettlementSummaryPageQueryParam, WalletSettlementSummaryEntity> {

	//  服务
	@Autowired
	private WalletSettlementSummaryService walletSettlementSummaryService;
	//  业务服务
	@Autowired
	private WalletSettlementSummaryRepository walletSettlementSummaryRepository;
	//  DO-DTO转化
	@Autowired
	private WalletSettlementSummaryMapStruct walletSettlementSummaryMapStruct;

	/**
	*  分页列表
	*/
	@ApiOperation("钱包清算汇总分页列表")
	@PreAuthorize("hasPermission('/admin/walletSettlementSummary',  'admin:walletSettlementSummary:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody WalletSettlementSummaryPageQueryParam param) {
		return walletSettlementSummaryRepository.pageList(param);
	}
	/**
	*  列表
	*/
	@ApiOperation("钱包清算汇总列表")
	@PreAuthorize("hasPermission('/admin/walletSettlementSummary',  'admin:walletSettlementSummary:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody WalletSettlementSummaryPageQueryParam param) {
		return walletSettlementSummaryRepository.findList(param);
	}

	/**
	* 获取
	*/
	@ApiOperation("钱包清算汇总获取")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/walletSettlementSummary',  'admin:walletSettlementSummary:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return walletSettlementSummaryRepository.getById(id);
	}

	@ApiOperation("钱包清算汇总审核")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/walletSettlementSummary',  'admin:walletSettlementSummary:query')")
	@GetMapping("/examine/{id}")
	public ResObject examine(@PathVariable String id) {
		return walletSettlementSummaryRepository.examine(id);
	}
	@ApiOperation("钱包清算汇总驳回")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "id",value = "钱包清算ID", required = true, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "cause",value = "驳回理由",required = true, dataType = "String"),
	})
	//@PreAuthorize("hasPermission('/admin/walletSettlementSummary',  'admin:walletSettlementSummary:query')")
	@PostMapping("/unusual")
	ResObject unusual(@RequestBody WalletSettlementSummaryEditParam walletSettlementSummaryEditParam){
		return walletSettlementSummaryRepository.unusual(walletSettlementSummaryEditParam);
	}

	@ApiOperation("钱包清算汇总状态操作")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "id",value = "钱包清算ID", required = true, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "cause",value = "驳回理由",required = false, dataType = "String"),
			@ApiImplicitParam(paramType="query", name = "status",value = "操作状态",required = true, dataType = "String"),
	})
	@PostMapping("/operationStatus")
	ResObject operationStatus(@RequestBody WalletSettlementSummaryEditParam walletSettlementSummaryEditParam){
		return walletSettlementSummaryRepository.operationStatus(walletSettlementSummaryEditParam);
	}
	@ApiOperation("钱包清算汇总清算操作")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "id",value = "钱包清算ID", required = true, dataType = "String"),
	})
	@PostMapping("/executeSingleSettlement")
	ResObject executeSingleSettlement(@RequestBody WalletSettlementSummaryEditParam walletSettlementSummaryEditParam) throws Exception {
		return walletSettlementSummaryRepository.executeSingleSettlement(walletSettlementSummaryEditParam);
	}
	/**
	 * 条件查询获取
	 */
	@ApiOperation("钱包清算汇总条件查询获取")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/walletSettlementSummary',  'admin:walletSettlementSummary:query')")
	@PostMapping("/getInfo")
	public ResObject getInfo(@RequestBody WalletSettlementSummaryPageQueryParam param) {
		return walletSettlementSummaryRepository.getInfo(param);
	}

	/**
	* 新增
	 * author:xiaoguo
	*/
	@ApiOperation("钱包清算汇总新增")
	@ApiImplicitParam(name = "WalletSettlementSummaryEditParam ", value = "新增", dataType = "WalletSettlementSummaryEditParam")
	@PreAuthorize("hasPermission('/admin/walletSettlementSummary',  'admin:walletSettlementSummary:add')")
	@EventLog(message = "新增", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody WalletSettlementSummaryInstallParam walletSettlementSummaryInstallParam) {
		return walletSettlementSummaryRepository.save(walletSettlementSummaryInstallParam);
	}

	/**
	* 修改
	*/
	@ApiOperation("钱包清算汇总修改")
	@ApiImplicitParam(name = "WalletSettlementSummaryEditParam ", value = "修改", dataType = "WalletSettlementSummaryEditParam")
	@PreAuthorize("hasPermission('/admin/walletSettlementSummary',  'admin:walletSettlementSummary:edit')")
	@EventLog(message = "修改", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody WalletSettlementSummaryEditParam walletSettlementSummaryEditParam) {
		return walletSettlementSummaryRepository.update(walletSettlementSummaryEditParam);
	}

	/**
	* 删除
	*/
	@ApiOperation("删除钱包清算汇总")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/walletSettlementSummary',  'admin:walletSettlementSummary:delete')")
	@EventLog(message = "删除", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable String[] ids) {
		return walletSettlementSummaryRepository.deleteByIds(ids);
	}

	/**
	* 通过主键删除
	*/
	@ApiOperation("通过主键删除钱包清算汇总")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delete')")
	@EventLog(message = "通过主键删除", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return walletSettlementSummaryRepository.deleteById(id);
	}

	/**
	* 导出
	*/
	@ApiOperation("导出钱包清算汇总")
	@PreAuthorize("hasPermission('/admin/walletSettlementSummary',  'admin:walletSettlementSummary:export')")
	@SneakyThrows
	@EventLog(message = "导出", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody WalletSettlementSummaryPageQueryParam param, HttpServletResponse response) {
		walletSettlementSummaryRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用")
	@PreAuthorize("hasPermission('/admin/walletSettlementSummary',  'admin:walletSettlementSummary:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody WalletSettlementSummaryEditParam walletSettlementSummaryEditParam) {
		return walletSettlementSummaryRepository.changeStatus(walletSettlementSummaryEditParam);
	}

}
