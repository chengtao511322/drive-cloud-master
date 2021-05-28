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
import com.drive.admin.service.WalletSettlementDetailService;
import com.drive.admin.repository.WalletSettlementDetailRepository;


/**
 * 管理
 *
 * @author chentao
 */
@Api(tags = "钱包清算明细管理")
@Slf4j
@RestController
@RequestMapping("/walletSettlementDetail")
public class WalletSettlementDetailController extends BaseController<WalletSettlementDetailPageQueryParam, WalletSettlementDetailEntity> {

	//  服务
	@Autowired
	private WalletSettlementDetailService walletSettlementDetailService;
	//  业务服务
	@Autowired
	private WalletSettlementDetailRepository walletSettlementDetailRepository;
	//  DO-DTO转化
	@Autowired
	private WalletSettlementDetailMapStruct walletSettlementDetailMapStruct;

	/**
	*  分页列表
	*/
	@ApiOperation("钱包清算明细分页列表")
	@PreAuthorize("hasPermission('/admin/walletSettlementDetail',  'admin:walletSettlementDetail:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody WalletSettlementDetailPageQueryParam param) {
		return walletSettlementDetailRepository.pageList(param);
	}
	/**
	*  列表
	*/
	@ApiOperation("钱包清算明细列表")
	@PreAuthorize("hasPermission('/admin/walletSettlementDetail',  'admin:walletSettlementDetail:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody WalletSettlementDetailPageQueryParam param) {
		return walletSettlementDetailRepository.findList(param);
	}

	/**
	* 获取
	*/
	@ApiOperation("获取钱包清算明细")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/walletSettlementDetail',  'admin:walletSettlementDetail:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return walletSettlementDetailRepository.getById(id);
	}

	/**
	 * 条件查询获取
	 */
	@ApiOperation("钱包清算明细条件查询获取")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/walletSettlementDetail',  'admin:walletSettlementDetail:query')")
	@PostMapping("/getInfo")
	public ResObject getInfo(@RequestBody WalletSettlementDetailPageQueryParam param) {
		return walletSettlementDetailRepository.getInfo(param);
	}

	/**
	* 新增
	 * author:xiaoguo
	*/
	@ApiOperation("新增钱包清算明细")
	@ApiImplicitParam(name = "WalletSettlementDetailEditParam ", value = "新增", dataType = "WalletSettlementDetailEditParam")
	@PreAuthorize("hasPermission('/admin/walletSettlementDetail',  'admin:walletSettlementDetail:add')")
	@EventLog(message = "新增", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody WalletSettlementDetailInstallParam walletSettlementDetailInstallParam) {
		return walletSettlementDetailRepository.save(walletSettlementDetailInstallParam);
	}

	/**
	* 修改
	*/
	@ApiOperation("修改钱包清算明细")
	@ApiImplicitParam(name = "WalletSettlementDetailEditParam ", value = "修改", dataType = "WalletSettlementDetailEditParam")
	@PreAuthorize("hasPermission('/admin/walletSettlementDetail',  'admin:walletSettlementDetail:edit')")
	@EventLog(message = "修改", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody WalletSettlementDetailEditParam walletSettlementDetailEditParam) {
		return walletSettlementDetailRepository.update(walletSettlementDetailEditParam);
	}

	/**
	* 删除
	*/
	@ApiOperation("删除钱包清算明细")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/walletSettlementDetail',  'admin:walletSettlementDetail:delete')")
	@EventLog(message = "删除", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable String[] ids) {
		return R.toRes(walletSettlementDetailService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除
	*/
	@ApiOperation("通过主键删除")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delete')")
	@EventLog(message = "通过主键删除钱包清算明细", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return walletSettlementDetailRepository.deleteById(id);
	}

	/**
	* 导出
	*/
	@ApiOperation("导出钱包清算明细")
	@PreAuthorize("hasPermission('/admin/walletSettlementDetail',  'admin:walletSettlementDetail:export')")
	@SneakyThrows
	@EventLog(message = "导出", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody WalletSettlementDetailPageQueryParam param, HttpServletResponse response) {
		walletSettlementDetailRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用")
	@PreAuthorize("hasPermission('/admin/walletSettlementDetail',  'admin:walletSettlementDetail:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody WalletSettlementDetailEditParam walletSettlementDetailEditParam) {
		return walletSettlementDetailRepository.changeStatus(walletSettlementDetailEditParam);
	}

}
