package com.drive.pay.api;

import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.log.annotation.EventLog;
import com.drive.pay.pojo.dto.MentTransactionEditParam;
import com.drive.pay.pojo.dto.MentTransactionInstallParam;
import com.drive.pay.pojo.dto.MentTransactionPageQueryParam;
import com.drive.pay.pojo.entity.MentTransactionEntity;
import com.drive.pay.repository.MentTransactionRepository;
import com.drive.pay.service.MentTransactionService;
import com.drive.pay.service.mapstruct.MentTransactionMapStruct;
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
 * 支付交易流水信息表管理
 *
 * @author xiaoguo
 */
@Api(tags = "支付交易流水信息表管理")
@Slf4j
@RestController
@RequestMapping("/mentTransaction")
public class ApiMentTransactionController extends BaseController<MentTransactionPageQueryParam, MentTransactionEntity> {

	// 支付交易流水信息表 服务
	@Autowired
	private MentTransactionService mentTransactionService;
	// 支付交易流水信息表 业务服务
	@Autowired
	private MentTransactionRepository mentTransactionRepository;
	// 支付交易流水信息表 DO-DTO转化
	@Autowired
	private MentTransactionMapStruct mentTransactionMapStruct;

	/**
	* 支付交易流水信息表 分页列表
	*/
	@ApiOperation("支付交易流水信息表分页列表")
	@PreAuthorize("hasPermission('/pay/mentTransaction',  'pay:mentTransaction:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody MentTransactionPageQueryParam param) {
		return mentTransactionRepository.pageList(param);
	}
	/**
	* 支付交易流水信息表 列表
	*/
	@ApiOperation("支付交易流水信息表列表")
	@PreAuthorize("hasPermission('/pay/mentTransaction',  'pay:mentTransaction:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody MentTransactionPageQueryParam param) {
		return mentTransactionRepository.findList(param);
	}

	/**
	* 获取支付交易流水信息表
	*/
	@ApiOperation("获取支付交易流水信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/pay/mentTransaction',  'pay:mentTransaction:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return mentTransactionRepository.getById(id);
	}
	@GetMapping("/transactionRollback/{id}")
	public ResObject transactionRollback(@PathVariable String id) {
		return mentTransactionRepository.transactionRollback(id);
	}

	/**
	 * 条件查询获取支付交易流水信息表
	 */
	@ApiOperation("条件查询获取支付交易流水信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/pay/mentTransaction',  'pay:mentTransaction:query')")
	@GetMapping("/getInfo")
	public ResObject getInfo(@PathVariable MentTransactionPageQueryParam param) {
		return mentTransactionRepository.getInfo(param);
	}

	/**
	* 新增支付交易流水信息表
	*/
	@ApiOperation("新增支付交易流水信息表")
	@ApiImplicitParam(name = "MentTransactionEditParam ", value = "新增支付交易流水信息表", dataType = "MentTransactionEditParam")
	@PreAuthorize("hasPermission('/pay/mentTransaction',  'pay:mentTransaction:add')")
	@EventLog(message = "新增支付交易流水信息表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody MentTransactionInstallParam mentTransactionInstallParam) {
		return mentTransactionRepository.save(mentTransactionInstallParam);
	}

	/**
	* 修改支付交易流水信息表
	*/
	@ApiOperation("修改支付交易流水信息表")
	@ApiImplicitParam(name = "MentTransactionEditParam ", value = "修改支付交易流水信息表", dataType = "MentTransactionEditParam")
	@PreAuthorize("hasPermission('/pay/mentTransaction',  'pay:mentTransaction:edit')")
	@EventLog(message = "修改支付交易流水信息表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody MentTransactionEditParam mentTransactionEditParam) {
		return mentTransactionRepository.update(mentTransactionEditParam);
	}

	/**
	* 删除支付交易流水信息表
	*/
	@ApiOperation("删除支付交易流水信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/pay/mentTransaction',  'pay:mentTransaction:delete')")
	@EventLog(message = "删除支付交易流水信息表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(mentTransactionService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除支付交易流水信息表
	*/
	@ApiOperation("通过主键删除支付交易流水信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除支付交易流水信息表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return mentTransactionRepository.deleteById(id);
	}

	/**
	* 导出支付交易流水信息表
	*/
	@ApiOperation("导出支付交易流水信息表")
	@PreAuthorize("hasPermission('/pay/mentTransaction',  'pay:mentTransaction:export')")
	@SneakyThrows
	@EventLog(message = "导出支付交易流水信息表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(MentTransactionPageQueryParam param, HttpServletResponse response) {
		mentTransactionRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用支付交易流水信息表")
	@PreAuthorize("hasPermission('/pay/mentTransaction',  'pay:mentTransaction:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用支付交易流水信息表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody MentTransactionEditParam mentTransactionEditParam) {
		return mentTransactionRepository.changeStatus(mentTransactionEditParam);
	}

}
