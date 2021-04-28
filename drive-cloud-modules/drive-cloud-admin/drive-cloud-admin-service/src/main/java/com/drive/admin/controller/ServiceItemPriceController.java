package com.drive.admin.controller;

import com.drive.admin.pojo.dto.ServiceItemPriceEditParam;
import com.drive.admin.pojo.dto.ServiceItemPricePageQueryParam;
import com.drive.admin.pojo.entity.ServiceItemPriceEntity;
import com.drive.admin.repository.ServiceItemPriceRepository;
import com.drive.admin.service.ServiceItemPriceService;
import com.drive.admin.service.mapstruct.ServiceItemPriceMapStruct;
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
 * 服务项目价格表管理
 *
 * @author JoyoDuan
 */
@Api(tags = "服务项目价格表管理")
@Slf4j
@RestController
@RequestMapping("/serviceItemPrice")
public class ServiceItemPriceController extends BaseController<ServiceItemPricePageQueryParam, ServiceItemPriceEntity> {

	@Autowired
	private ServiceItemPriceService serviceItemPriceService;
	@Autowired
	private ServiceItemPriceRepository serviceItemPriceRepository;
	@Autowired
	private ServiceItemPriceMapStruct serviceItemPriceMapStruct;

	/**
	* 服务项目价格表 分页列表
	*/
	@ApiOperation("服务项目价格表分页列表")
	//@PreAuthorize("hasPermission('/admin/serviceItemPrice',  'admin:serviceItemPrice:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid ServiceItemPricePageQueryParam param) {
		return serviceItemPriceRepository.pageList(param);
	}
	/**
	* 服务项目价格表 分页列表
	*/
	@ApiOperation("服务项目价格表列表")
	//@PreAuthorize("hasPermission('/admin/serviceItemPrice',  'admin:serviceItemPrice:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid ServiceItemPricePageQueryParam param) {
		return serviceItemPriceRepository.findList(param);
	}


	/**
	 * 服务项目价格表 分页列表
	 */
	@ApiOperation("服务项目价格表列表")
	//@PreAuthorize("hasPermission('/admin/serviceItemPrice',  'admin:serviceItemPrice:query')")
	@PostMapping(value = "/findListContrast")
	public ResObject findListContrast(@Valid ServiceItemPricePageQueryParam param) {
		return serviceItemPriceRepository.findListContrast(param);
	}

	/**
	* 获取服务项目价格表
	*/
	@ApiOperation("获取服务项目价格表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/serviceItemPrice',  'admin:serviceItemPrice:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return serviceItemPriceRepository.getById(id);
	}
	/**
	* 获取服务项目价格表
	*/
	@ApiOperation("获取服务项目价格表")
	@ApiImplicitParam(name = "queryList", required = true, dataType = "Entity", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/serviceItemPrice',  'admin:serviceItemPrice:query')")
	@PostMapping("/queryList")
	public ResObject queryList(@RequestBody ServiceItemPricePageQueryParam param) {
		return serviceItemPriceRepository.queryList(param);
	}
	@ApiOperation("获取分页查询服务项目价格表")
	@ApiImplicitParam(name = "queryPageList", required = true, dataType = "Entity", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/serviceItemPrice',  'admin:serviceItemPrice:query')")
	@PostMapping("/queryPageList")
	public ResObject queryPageList(@RequestBody ServiceItemPricePageQueryParam param) {
		return serviceItemPriceRepository.queryPageList(param);
	}


	@ApiOperation("通过班型ID获取关联服务项目价格表")
	@ApiImplicitParam(name = "queryPageList", required = true, dataType = "Entity", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/serviceItemPrice',  'admin:serviceItemPrice:query')")
	@PostMapping("/getQueryListById")
	public ResObject getQueryListById(@RequestBody ServiceItemPricePageQueryParam param) {
		return serviceItemPriceRepository.getQueryListById(param);
	}

	/**
	* 新增服务项目价格表
	*/
	@ApiOperation("新增服务项目价格表")
	@ApiImplicitParam(name = "ServiceItemPriceEditParam ", value = "新增服务项目价格表", dataType = "ServiceItemPriceEditParam")
	//@PreAuthorize("hasPermission('/admin/serviceItemPrice',  'admin:serviceItemPrice:add')")
	@EventLog(message = "新增服务项目价格表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody ServiceItemPriceEditParam serviceItemPriceEditParam) {
		return serviceItemPriceRepository.save(serviceItemPriceEditParam);
	}

	/**
	* 修改服务项目价格表
	*/
	@ApiOperation("修改服务项目价格表")
	@ApiImplicitParam(name = "ServiceItemPriceEditParam ", value = "修改服务项目价格表", dataType = "ServiceItemPriceEditParam")
	//@PreAuthorize("hasPermission('/admin/serviceItemPrice',  'admin:serviceItemPrice:edit')")
	@EventLog(message = "修改服务项目价格表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody ServiceItemPriceEditParam serviceItemPriceEditParam) {
		return serviceItemPriceRepository.update(serviceItemPriceEditParam);
	}

	/**
	* 删除服务项目价格表
	*/
	@ApiOperation("删除服务项目价格表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/serviceItemPrice',  'admin:serviceItemPrice:delete')")
	@EventLog(message = "删除服务项目价格表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(serviceItemPriceService.removeByIds(Arrays.asList(ids)));
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
		return serviceItemPriceRepository.deleteById(id);
	}

	/**
	* 导出服务项目价格表
	*/
	@ApiOperation("导出服务项目价格表")
	//@PreAuthorize("hasPermission('/admin/serviceItemPrice',  'admin:serviceItemPrice:export')")
	@SneakyThrows
	@EventLog(message = "导出服务项目价格表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(ServiceItemPricePageQueryParam param, HttpServletResponse response) {
			serviceItemPriceRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用服务项目价格表")
	//@PreAuthorize("hasPermission('/admin/serviceItemPrice',  'admin:serviceItemPrice:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用服务项目价格表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody ServiceItemPriceEditParam serviceItemPriceEditParam) {
		return serviceItemPriceRepository.changeStatus(serviceItemPriceEditParam);
	}

}
