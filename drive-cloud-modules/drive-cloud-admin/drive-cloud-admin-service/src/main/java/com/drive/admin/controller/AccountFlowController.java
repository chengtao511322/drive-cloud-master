package com.drive.admin.controller;

import com.drive.admin.pojo.dto.AccountFlowEditParam;
import com.drive.admin.pojo.dto.AccountFlowInstallParam;
import com.drive.admin.pojo.dto.AccountFlowPageQueryParam;
import com.drive.admin.pojo.entity.AccountFlowEntity;
import com.drive.admin.repository.AccountFlowRepository;
import com.drive.admin.service.AccountFlowService;
import com.drive.admin.service.mapstruct.AccountFlowMapStruct;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.log.annotation.EventLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;


/**
 * 平台账务流水管理
 *
 * @author xiaoguo
 */
@Api(tags = "平台账务流水管理")
@Slf4j
@RestController
@RequestMapping("/accountFlow")
public class AccountFlowController extends BaseController<AccountFlowPageQueryParam, AccountFlowEntity> {

	// 平台账务流水 服务
	@Autowired
	private AccountFlowService accountFlowService;
	// 平台账务流水 业务服务
	@Autowired
	private AccountFlowRepository accountFlowRepository;
	// 平台账务流水 DO-DTO转化
	@Autowired
	private AccountFlowMapStruct accountFlowMapStruct;

	/**
	* 平台账务流水 分页列表
	*/
	@ApiOperation("平台账务流水分页列表")
	@PreAuthorize("hasPermission('/admin/accountFlow',  'admin:accountFlow:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody AccountFlowPageQueryParam param) {
		return accountFlowRepository.pageList(param);
	}
	/**
	* 平台账务流水 列表
	*/
	@ApiOperation("平台账务流水列表")
	@PreAuthorize("hasPermission('/admin/accountFlow',  'admin:accountFlow:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody AccountFlowPageQueryParam param) {
		return accountFlowRepository.findList(param);
	}

	/**
	* 获取平台账务流水
	*/
	@ApiOperation("获取平台账务流水")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/accountFlow',  'admin:accountFlow:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return accountFlowRepository.getById(id);
	}

	/**
	 * 条件查询获取平台账务流水
	 */
	@ApiOperation("条件查询获取平台账务流水")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/accountFlow',  'admin:accountFlow:query')")
	@PostMapping("/getInfo")
	public ResObject getInfo(@RequestBody AccountFlowPageQueryParam param) {
		return accountFlowRepository.getInfo(param);
	}

	/**
	* 新增平台账务流水
	*/
	@ApiOperation("新增平台账务流水")
	@ApiImplicitParam(name = "AccountFlowEditParam ", value = "新增平台账务流水", dataType = "AccountFlowEditParam")
	@PreAuthorize("hasPermission('/admin/accountFlow',  'admin:accountFlow:add')")
	@EventLog(message = "新增平台账务流水", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody AccountFlowInstallParam accountFlowInstallParam) {
		return accountFlowRepository.save(accountFlowInstallParam);
	}

	/**
	* 修改平台账务流水
	*/
	@ApiOperation("修改平台账务流水")
	@ApiImplicitParam(name = "AccountFlowEditParam ", value = "修改平台账务流水", dataType = "AccountFlowEditParam")
	@PreAuthorize("hasPermission('/admin/accountFlow',  'admin:accountFlow:edit')")
	@EventLog(message = "修改平台账务流水", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody AccountFlowEditParam accountFlowEditParam) {
		return accountFlowRepository.update(accountFlowEditParam);
	}

	/**
	* 删除平台账务流水
	*/
	@ApiOperation("删除平台账务流水")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/accountFlow',  'admin:accountFlow:delete')")
	@EventLog(message = "删除平台账务流水", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(accountFlowService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除平台账务流水
	*/
	@ApiOperation("通过主键删除平台账务流水")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除平台账务流水", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return accountFlowRepository.deleteById(id);
	}

	/**
	* 导出平台账务流水
	*/
	@ApiOperation("导出平台账务流水")
	@PreAuthorize("hasPermission('/admin/accountFlow',  'admin:accountFlow:export')")
	@SneakyThrows
	@EventLog(message = "导出平台账务流水", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody AccountFlowPageQueryParam param, HttpServletResponse response) {
		accountFlowRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用平台账务流水")
	@PreAuthorize("hasPermission('/admin/accountFlow',  'admin:accountFlow:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用平台账务流水", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody AccountFlowEditParam accountFlowEditParam) {
		return accountFlowRepository.changeStatus(accountFlowEditParam);
	}

}
