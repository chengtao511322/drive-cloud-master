package com.drive.admin.controller;

import com.drive.admin.pojo.dto.PlatformWalletDetailPageQueryParam;
import com.drive.admin.pojo.dto.PlatformWalletEditParam;
import com.drive.admin.pojo.dto.PlatformWalletInstallParam;
import com.drive.admin.pojo.dto.PlatformWalletPageQueryParam;
import com.drive.admin.pojo.entity.PlatformWalletEntity;
import com.drive.admin.repository.PlatformWalletDetailRepository;
import com.drive.admin.repository.PlatformWalletRepository;
import com.drive.admin.service.PlatformWalletService;
import com.drive.admin.service.mapstruct.PlatformWalletMapStruct;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;


/**
 * 教练钱包表管理
 *
 * @author xiaoguo
 */
@Api(tags = "教练钱包表管理")
@Slf4j
@RestController
@RequestMapping("/platformWallet")
public class PlatformWalletController extends BaseController<PlatformWalletPageQueryParam, PlatformWalletEntity> {

	// 教练钱包表 服务
	@Autowired
	private PlatformWalletService platformWalletService;
	// 教练钱包表 业务服务
	@Autowired
	private PlatformWalletRepository platformWalletRepository;
	@Autowired
	private PlatformWalletDetailRepository platformWalletDetailRepository;
	// 教练钱包表 DO-DTO转化
	@Autowired
	private PlatformWalletMapStruct platformWalletMapStruct;

	/**
	* 教练钱包表 分页列表
	*/
	@ApiOperation("教练钱包明细表分页列表")
	//@PreAuthorize("hasPermission('/admin/platformWallet',  'admin:platformWallet:query')")
	@PostMapping(value = "/pageWalletDetailList")
	public ResObject pagePlatformWalletDetailList(@Valid @RequestBody PlatformWalletDetailPageQueryParam param) {
		return platformWalletDetailRepository.pageList(param);
	}
	@ApiOperation("教练钱包表分页列表")
	//@PreAuthorize("hasPermission('/admin/platformWallet',  'admin:platformWallet:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody PlatformWalletPageQueryParam param) {
		return platformWalletRepository.pageList(param);
	}
	/**
	* 教练钱包表 列表
	*/
	@ApiOperation("教练钱包表列表")
	//@PreAuthorize("hasPermission('/admin/platformWallet',  'admin:platformWallet:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody PlatformWalletPageQueryParam param) {
		return platformWalletRepository.findList(param);
	}

	/**
	* 获取教练钱包表
	*/
	@ApiOperation("获取教练钱包表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/platformWallet',  'admin:platformWallet:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return platformWalletRepository.getById(id);
	}

	/**
	 * 条件查询获取教练钱包表
	 */
	@ApiOperation("条件查询获取教练钱包表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/platformWallet',  'admin:platformWallet:query')")
	@PostMapping("/getInfo")
	public ResObject getInfo(@RequestBody PlatformWalletPageQueryParam param) {
		return platformWalletRepository.getInfo(param);
	}

	/**
	* 新增教练钱包表
	*/
	@ApiOperation("新增教练钱包表")
	@ApiImplicitParam(name = "PlatformWalletEditParam ", value = "新增教练钱包表", dataType = "PlatformWalletEditParam")
	//@PreAuthorize("hasPermission('/admin/platformWallet',  'admin:platformWallet:add')")
	@EventLog(message = "新增教练钱包表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody PlatformWalletInstallParam platformWalletInstallParam) {
		return platformWalletRepository.save(platformWalletInstallParam);
	}

	/**
	* 修改教练钱包表
	*/
	@ApiOperation("修改教练钱包表")
	@ApiImplicitParam(name = "PlatformWalletEditParam ", value = "修改教练钱包表", dataType = "PlatformWalletEditParam")
	//@PreAuthorize("hasPermission('/admin/platformWallet',  'admin:platformWallet:edit')")
	@EventLog(message = "修改教练钱包表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody PlatformWalletEditParam platformWalletEditParam) {
		return platformWalletRepository.update(platformWalletEditParam);
	}

	/**
	* 删除教练钱包表
	*/
	@ApiOperation("删除教练钱包表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/platformWallet',  'admin:platformWallet:delete')")
	@EventLog(message = "删除教练钱包表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(platformWalletService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除教练钱包表
	*/
	@ApiOperation("通过主键删除教练钱包表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除教练钱包表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return platformWalletRepository.deleteById(id);
	}

	/**
	* 导出教练钱包表
	*/
	@ApiOperation("导出教练钱包表")
	//@PreAuthorize("hasPermission('/admin/platformWallet',  'admin:platformWallet:export')")
	@SneakyThrows
	@EventLog(message = "导出教练钱包表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody PlatformWalletPageQueryParam param, HttpServletResponse response) {
		platformWalletRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用教练钱包表")
	//@PreAuthorize("hasPermission('/admin/platformWallet',  'admin:platformWallet:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用教练钱包表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody PlatformWalletEditParam platformWalletEditParam) {
		return platformWalletRepository.changeStatus(platformWalletEditParam);
	}

	/**
	 * 钱包对账
	 */
	@ApiOperation("钱包对账")
	@EventLog(message = "钱包对账",businessType = EventLogEnum.UPDATE)
	@PostMapping("/walletReconciliationById/{id}")
	public ResObject walletReconciliation(@PathVariable String id){
		return platformWalletRepository.walletReconciliation(id);
	}

}
