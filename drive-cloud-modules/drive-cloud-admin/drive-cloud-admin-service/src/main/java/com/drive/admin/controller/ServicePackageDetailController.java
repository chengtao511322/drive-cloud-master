package com.drive.admin.controller;

import com.drive.admin.pojo.dto.ServicePackageDetailEditParam;
import com.drive.admin.pojo.dto.ServicePackageDetailPageQueryParam;
import com.drive.admin.pojo.entity.ServicePackageDetailEntity;
import com.drive.admin.repository.ServicePackageDetailRepository;
import com.drive.admin.service.ServicePackageDetailService;
import com.drive.admin.service.mapstruct.ServicePackageDetailMapStruct;
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
import java.util.List;


/**
 * 服务项目打包明细表管理
 *
 * @author xiaoguo
 */
@Api(tags = "服务项目打包明细表管理")
@Slf4j
@RestController
@RequestMapping("/servicePackageDetail")
public class ServicePackageDetailController extends BaseController<ServicePackageDetailPageQueryParam, ServicePackageDetailEntity> {

	@Autowired
	private ServicePackageDetailService servicePackageDetailService;
	@Autowired
	private ServicePackageDetailRepository servicePackageDetailRepository;
	@Autowired
	private ServicePackageDetailMapStruct servicePackageDetailMapStruct;

	/**
	* 服务项目打包明细表 分页列表
	*/
	@ApiOperation("服务项目打包明细表分页列表")
	@PreAuthorize("hasPermission('/admin/servicePackageDetail',  'admin:servicePackageDetail:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid ServicePackageDetailPageQueryParam param) {
		return servicePackageDetailRepository.pageList(param);
	}
	/**
	* 服务项目打包明细表 分页列表
	*/
	@ApiOperation("服务项目打包明细表列表")
	@PreAuthorize("hasPermission('/admin/servicePackageDetail',  'admin:servicePackageDetail:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid ServicePackageDetailPageQueryParam param) {
		return servicePackageDetailRepository.findList(param);
	}

	/**
	* 获取服务项目打包明细表
	*/
	@ApiOperation("获取服务项目打包明细表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/servicePackageDetail',  'admin:servicePackageDetail:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return servicePackageDetailRepository.getInfo(id);
	}

	/**
	* 新增服务项目打包明细表
	*/
	@ApiOperation("新增服务项目打包明细表")
	@ApiImplicitParam(name = "ServicePackageDetailEditParam ", value = "新增服务项目打包明细表", dataType = "ServicePackageDetailEditParam")
	@PreAuthorize("hasPermission('/admin/servicePackageDetail',  'admin:servicePackageDetail:add')")
	@EventLog(message = "新增服务项目打包明细表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody ServicePackageDetailEditParam servicePackageDetailEditParam) {
		return servicePackageDetailRepository.save(servicePackageDetailEditParam);
	}


	/**
	* 修改服务项目打包明细表
	*/
	@ApiOperation("批量修改服务项目打包明细表")
	@ApiImplicitParam(name = "ServicePackageDetailEditParam ", value = "批量修改服务项目打包明细表", dataType = "ServicePackageDetailEditParam")
	@PreAuthorize("hasPermission('/admin/servicePackageDetail',  'admin:servicePackageDetail:updateBatch')")
	@EventLog(message = "批量修改服务项目打包明细表", businessType = EventLogEnum.UPDATE)
	@PostMapping("/updateBatch")
	public ResObject updateBatch(@Valid @RequestBody List<ServicePackageDetailEditParam> servicePackageDetailEditParamList) {
		return servicePackageDetailRepository.updateBatch(servicePackageDetailEditParamList);
	}
	@ApiOperation("修改服务项目打包明细表")
	@ApiImplicitParam(name = "ServicePackageDetailEditParam ", value = "修改服务项目打包明细表", dataType = "ServicePackageDetailEditParam")
	@PreAuthorize("hasPermission('/admin/servicePackageDetail',  'admin:servicePackageDetail:edit')")
	@EventLog(message = "修改服务项目打包明细表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody ServicePackageDetailEditParam servicePackageDetailEditParam) {
		return servicePackageDetailRepository.update(servicePackageDetailEditParam);
	}

	/**
	* 删除服务项目打包明细表
	*/
	@ApiOperation("删除服务项目打包明细表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/servicePackageDetail',  'admin:servicePackageDetail:delete')")
	@EventLog(message = "删除服务项目打包明细表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable String[] ids) {
		return R.toRes(servicePackageDetailService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除教练信息表
	*/
	@ApiOperation("删除教练信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delete')")
	@EventLog(message = "删除教练信息表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return servicePackageDetailRepository.deleteById(id);
	}

	/**
	* 导出服务项目打包明细表
	*/
	@ApiOperation("导出服务项目打包明细表")
	@PreAuthorize("hasPermission('/admin/servicePackageDetail',  'admin:servicePackageDetail:export')")
	@SneakyThrows
	@EventLog(message = "导出服务项目打包明细表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(ServicePackageDetailPageQueryParam param, HttpServletResponse response) {
		servicePackageDetailRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用服务项目打包明细表")
	@PreAuthorize("hasPermission('/admin/servicePackageDetail',  'admin:servicePackageDetail:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用服务项目打包明细表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody ServicePackageDetailEditParam servicePackageDetailEditParam) {
		return servicePackageDetailRepository.changeStatus(servicePackageDetailEditParam);
	}

}
