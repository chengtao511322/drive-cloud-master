package com.drive.admin.controller;

import com.drive.admin.pojo.dto.ServiceItemEditParam;
import com.drive.admin.pojo.dto.ServiceItemPageQueryParam;
import com.drive.admin.pojo.entity.ServiceItemEntity;
import com.drive.admin.repository.ServiceItemRepository;
import com.drive.admin.service.ServiceItemService;
import com.drive.admin.service.mapstruct.ServiceItemMapStruct;
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
 * 服务项目表管理
 *
 * @author JoyoDuan
 */
@Api(tags = "服务项目表管理")
@Slf4j
@RestController
@RequestMapping("/serviceItem")
public class ServiceItemController extends BaseController<ServiceItemPageQueryParam, ServiceItemEntity> {

	@Autowired
	private ServiceItemService serviceItemService;
	@Autowired
	private ServiceItemRepository serviceItemRepository;
	@Autowired
	private ServiceItemMapStruct serviceItemMapStruct;

	/**
	* 服务项目表 分页列表
	*/
	@ApiOperation("服务项目表分页列表")
	//@PreAuthorize("hasPermission('/admin/serviceItem',  'admin:serviceItem:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody ServiceItemPageQueryParam param) {
		return serviceItemRepository.pageList(param);
	}
	/**
	* 服务项目表 分页列表
	*/
	@ApiOperation("服务项目表列表")
	//@PreAuthorize("hasPermission('/admin/serviceItem',  'admin:serviceItem:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody ServiceItemPageQueryParam param) {
		return serviceItemRepository.findList(param);
	}

	/**
	* 获取服务项目表
	*/
	@ApiOperation("获取服务项目表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/serviceItem',  'admin:serviceItem:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return serviceItemRepository.getById(id);
	}

	/**
	* 新增服务项目表
	*/
	@ApiOperation("新增服务项目表")
	@ApiImplicitParam(name = "ServiceItemEditParam ", value = "新增服务项目表", dataType = "ServiceItemEditParam")
	//@PreAuthorize("hasPermission('/admin/serviceItem',  'admin:serviceItem:add')")
	@EventLog(message = "新增服务项目表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody ServiceItemEditParam serviceItemEditParam) {
		return serviceItemRepository.save(serviceItemEditParam);
	}

	/**
	* 修改服务项目表
	*/
	@ApiOperation("修改服务项目表")
	@ApiImplicitParam(name = "ServiceItemEditParam ", value = "修改服务项目表", dataType = "ServiceItemEditParam")
	//@PreAuthorize("hasPermission('/admin/serviceItem',  'admin:serviceItem:edit')")
	@EventLog(message = "修改服务项目表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody ServiceItemEditParam serviceItemEditParam) {
		return serviceItemRepository.update(serviceItemEditParam);
	}

	/**
	* 删除服务项目表
	*/
	@ApiOperation("删除服务项目表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/serviceItem',  'admin:serviceItem:delete')")
	@EventLog(message = "删除服务项目表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(serviceItemService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除教练信息表
	*/
	@ApiOperation("删除教练信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delete')")
	@EventLog(message = "删除教练信息表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return serviceItemRepository.deleteById(id);
	}

	/**
	* 导出服务项目表
	*/
	@ApiOperation("导出服务项目表")
	//@PreAuthorize("hasPermission('/admin/serviceItem',  'admin:serviceItem:export')")
	@SneakyThrows
	@EventLog(message = "导出服务项目表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody ServiceItemPageQueryParam param, HttpServletResponse response) {
			serviceItemRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用服务项目表")
	//@PreAuthorize("hasPermission('/admin/serviceItem',  'admin:serviceItem:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用服务项目表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody ServiceItemEditParam serviceItemEditParam) {
		return serviceItemRepository.changeStatus(serviceItemEditParam);
	}

}
