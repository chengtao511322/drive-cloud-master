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
import com.drive.admin.service.DeductSettingService;
import com.drive.admin.repository.DeductSettingRepository;


/**
 * 提成设置表管理
 *
 * @author xiaoguo
 */
@Api(tags = "提成设置表管理")
@Slf4j
@RestController
@RequestMapping("/deductSetting")
public class DeductSettingController extends BaseController<DeductSettingPageQueryParam, DeductSettingEntity> {

	// 提成设置表 服务
	@Autowired
	private DeductSettingService deductSettingService;
	// 提成设置表 业务服务
	@Autowired
	private DeductSettingRepository deductSettingRepository;
	// 提成设置表 DO-DTO转化
	@Autowired
	private DeductSettingMapStruct deductSettingMapStruct;

	/**
	* 提成设置表 分页列表
	*/
	@ApiOperation("提成设置表分页列表")
	@PreAuthorize("hasPermission('/admin/deductSetting',  'admin:deductSetting:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody DeductSettingPageQueryParam param) {
		return deductSettingRepository.pageList(param);
	}
	/**
	* 提成设置表 列表
	*/
	@ApiOperation("提成设置表列表")
	@PreAuthorize("hasPermission('/admin/deductSetting',  'admin:deductSetting:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody DeductSettingPageQueryParam param) {
		return deductSettingRepository.findList(param);
	}

	/**
	* 获取提成设置表
	*/
	@ApiOperation("获取提成设置表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/deductSetting',  'admin:deductSetting:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return deductSettingRepository.getById(id);
	}

	/**
	 * 条件查询获取提成设置表
	 */
	@ApiOperation("条件查询获取提成设置表")
	@ApiImplicitParam(name = "${pkColumn.propertyName}", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/deductSetting',  'admin:deductSetting:query')")
	@PostMapping("/getInfo")
	public ResObject getInfo(@RequestBody DeductSettingPageQueryParam param) {
		return deductSettingRepository.getInfo(param);
	}

	/**
	* 新增提成设置表
	*/
	@ApiOperation("新增提成设置表")
	@ApiImplicitParam(name = "DeductSettingEditParam ", value = "新增提成设置表", dataType = "DeductSettingEditParam")
	@PreAuthorize("hasPermission('/admin/deductSetting',  'admin:deductSetting:add')")
	@EventLog(message = "新增提成设置表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody DeductSettingInstallParam deductSettingInstallParam) {
		return deductSettingRepository.save(deductSettingInstallParam);
	}
	@ApiOperation("批量新增提成设置表")
	@ApiImplicitParam(name = "DeductSettingEditParam ", value = "新增提成设置表", dataType = "DeductSettingEditParam")
	@PreAuthorize("hasPermission('/admin/deductSetting',  'admin:deductSetting:add')")
	@EventLog(message = "新增提成设置表", businessType = EventLogEnum.CREATE)
	@PostMapping("/saveBatch")
	public ResObject saveBatch(@Valid @RequestBody List<DeductSettingInstallParam> deductSettingInstallParamList) {
		return deductSettingRepository.saveBatch(deductSettingInstallParamList);
	}

	/**
	* 修改提成设置表
	*/
	@ApiOperation("修改提成设置表")
	@ApiImplicitParam(name = "DeductSettingEditParam ", value = "修改提成设置表", dataType = "DeductSettingEditParam")
	@PreAuthorize("hasPermission('/admin/deductSetting',  'admin:deductSetting:edit')")
	@EventLog(message = "修改提成设置表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody DeductSettingEditParam deductSettingEditParam) {
		return deductSettingRepository.update(deductSettingEditParam);
	}

	/**
	* 删除提成设置表
	*/
	@ApiOperation("删除提成设置表")
	@ApiImplicitParam(name = "ids", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/deductSetting',  'admin:deductSetting:delete')")
	@EventLog(message = "删除提成设置表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable String[] ids) {
		return R.toRes(deductSettingService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除提成设置表
	*/
	@ApiOperation("通过主键删除提成设置表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除提成设置表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return deductSettingRepository.deleteById(id);
	}

	/**
	* 导出提成设置表
	*/
	@ApiOperation("导出提成设置表")
	@PreAuthorize("hasPermission('/admin/deductSetting',  'admin:deductSetting:export')")
	@SneakyThrows
	@EventLog(message = "导出提成设置表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody DeductSettingPageQueryParam param, HttpServletResponse response) {
		deductSettingRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用提成设置表")
	@PreAuthorize("hasPermission('/admin/deductSetting',  'admin:deductSetting:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用提成设置表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody DeductSettingEditParam deductSettingEditParam) {
		return deductSettingRepository.changeStatus(deductSettingEditParam);
	}

}
