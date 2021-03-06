package com.drive.admin.controller;

import com.drive.admin.pojo.dto.ServiceReturnVisitHistoryEditParam;
import com.drive.admin.pojo.dto.ServiceReturnVisitHistoryInstallParam;
import com.drive.admin.pojo.dto.ServiceReturnVisitHistoryPageQueryParam;
import com.drive.admin.pojo.dto.StudentStudyEnrollPageQueryParam;
import com.drive.admin.pojo.entity.ServiceReturnVisitHistoryEntity;
import com.drive.admin.repository.ServiceReturnVisitHistoryRepository;
import com.drive.admin.service.ServiceReturnVisitHistoryService;
import com.drive.admin.service.mapstruct.ServiceReturnVisitHistoryMapStruct;
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
 * 客服回访记录管理
 *
 * @author xiaoguo
 */
@Api(tags = "客服回访记录管理")
@Slf4j
@RestController
@RequestMapping("/serviceReturnVisitHistory")
public class ServiceReturnVisitHistoryController extends BaseController<ServiceReturnVisitHistoryPageQueryParam, ServiceReturnVisitHistoryEntity> {

	// 客服回访记录 服务
	@Autowired
	private ServiceReturnVisitHistoryService serviceReturnVisitHistoryService;
	// 客服回访记录 业务服务
	@Autowired
	private ServiceReturnVisitHistoryRepository serviceReturnVisitHistoryRepository;
	// 客服回访记录 DO-DTO转化
	@Autowired
	private ServiceReturnVisitHistoryMapStruct serviceReturnVisitHistoryMapStruct;

	/**
	* 客服回访记录 分页列表
	*/
	@ApiOperation("客服回访记录分页列表")
	//@PreAuthorize("hasPermission('/admin/serviceReturnVisitHistory',  'admin:serviceReturnVisitHistory:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody ServiceReturnVisitHistoryPageQueryParam param) {
		return serviceReturnVisitHistoryRepository.pageList(param);
	}
	/**
	* 客服回访记录 列表
	*/
	@ApiOperation("客服回访记录列表")
	//@PreAuthorize("hasPermission('/admin/serviceReturnVisitHistory',  'admin:serviceReturnVisitHistory:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody ServiceReturnVisitHistoryPageQueryParam param) {
		return serviceReturnVisitHistoryRepository.findList(param);
	}
	@ApiOperation("记录列表")
	//@PreAuthorize("hasPermission('/admin/serviceReturnVisitHistory',  'admin:serviceReturnVisitHistory:query')")
	@PostMapping(value = "/pageListReturnVisitHistory")
	public ResObject pageListReturnVisitHistory(@Valid @RequestBody StudentStudyEnrollPageQueryParam param) {
		return serviceReturnVisitHistoryRepository.pageListReturnVisitHistory(param);
	}

	/**
	* 获取客服回访记录
	*/
	@ApiOperation("获取客服回访记录")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/serviceReturnVisitHistory',  'admin:serviceReturnVisitHistory:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return serviceReturnVisitHistoryRepository.getById(id);
	}

	/**
	* 获取客服回访记录
	*/
	@ApiOperation("通过学员ID 获取聚合客服回访记录")
	@ApiImplicitParam(name = "studnetId", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/serviceReturnVisitHistory',  'admin:serviceReturnVisitHistory:query')")
	@GetMapping("/aggregationListReturnVisitHistory/{studnetId}")
	public ResObject aggregationListReturnVisitHistory(@PathVariable String studnetId) {
		return serviceReturnVisitHistoryRepository.aggregationListReturnVisitHistory(studnetId);
	}

	/**
	 * 条件查询获取客服回访记录
	 */
	@ApiOperation("条件查询获取客服回访记录")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/serviceReturnVisitHistory',  'admin:serviceReturnVisitHistory:query')")
	@PostMapping("/getInfo")
	public ResObject getInfo(@PathVariable @RequestBody ServiceReturnVisitHistoryPageQueryParam param) {
		return serviceReturnVisitHistoryRepository.getInfo(param);
	}

	/**
	* 新增客服回访记录
	*/
	@ApiOperation("新增客服回访记录")
	@ApiImplicitParam(name = "ServiceReturnVisitHistoryEditParam ", value = "新增客服回访记录", dataType = "ServiceReturnVisitHistoryEditParam")
	//@PreAuthorize("hasPermission('/admin/serviceReturnVisitHistory',  'admin:serviceReturnVisitHistory:add')")
	@EventLog(message = "新增客服回访记录", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody ServiceReturnVisitHistoryInstallParam serviceReturnVisitHistoryInstallParam) {
		return serviceReturnVisitHistoryRepository.save(serviceReturnVisitHistoryInstallParam);
	}
	@ApiOperation("添加回访记录")
	@ApiImplicitParam(name = "ServiceReturnVisitHistoryEditParam ", value = "新增客服回访记录", dataType = "ServiceReturnVisitHistoryEditParam")
	//@PreAuthorize("hasPermission('/admin/serviceReturnVisitHistory',  'admin:serviceReturnVisitHistory:add')")
	@EventLog(message = "新增客服回访记录", businessType = EventLogEnum.CREATE)
	@PostMapping("/addReturnVisitHistory")
	public ResObject addReturnVisitHistory(@Valid @RequestBody ServiceReturnVisitHistoryInstallParam serviceReturnVisitHistoryInstallParam) {
		return serviceReturnVisitHistoryRepository.addReturnVisitHistory(serviceReturnVisitHistoryInstallParam);
	}

	/**
	* 修改客服回访记录
	*/
	@ApiOperation("修改客服回访记录")
	@ApiImplicitParam(name = "ServiceReturnVisitHistoryEditParam ", value = "修改客服回访记录", dataType = "ServiceReturnVisitHistoryEditParam")
	//@PreAuthorize("hasPermission('/admin/serviceReturnVisitHistory',  'admin:serviceReturnVisitHistory:edit')")
	@EventLog(message = "修改客服回访记录", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody ServiceReturnVisitHistoryEditParam serviceReturnVisitHistoryEditParam) {
		return serviceReturnVisitHistoryRepository.update(serviceReturnVisitHistoryEditParam);
	}

	/**
	 * 发布回访
	 * @return
	 */
	@ApiOperation("发布客服回访记录")
	@ApiImplicitParam(name = "ServiceReturnVisitHistoryEditParam ", value = "修改客服回访记录", dataType = "ServiceReturnVisitHistoryEditParam")
	//@PreAuthorize("hasPermission('/admin/serviceReturnVisitHistory',  'admin:serviceReturnVisitHistory:edit')")
	@EventLog(message = "修改客服回访记录", businessType = EventLogEnum.UPDATE)
	@PostMapping("/publishReturnVisit")
	ResObject publishReturnVisit(@RequestBody ServiceReturnVisitHistoryEditParam returnVisitHistoryEditParam){
		return serviceReturnVisitHistoryRepository.publishReturnVisit(returnVisitHistoryEditParam);
	}


	/**
	* 删除客服回访记录
	*/
	@ApiOperation("删除客服回访记录")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/serviceReturnVisitHistory',  'admin:serviceReturnVisitHistory:delete')")
	@EventLog(message = "删除客服回访记录", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(serviceReturnVisitHistoryService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除客服回访记录
	*/
	@ApiOperation("通过主键删除客服回访记录")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除客服回访记录", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return serviceReturnVisitHistoryRepository.deleteById(id);
	}

	/**
	* 导出客服回访记录
	*/
	@ApiOperation("导出客服回访记录")
	//@PreAuthorize("hasPermission('/admin/serviceReturnVisitHistory',  'admin:serviceReturnVisitHistory:export')")
	@SneakyThrows
	@EventLog(message = "导出客服回访记录", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(ServiceReturnVisitHistoryPageQueryParam param, HttpServletResponse response) {
		serviceReturnVisitHistoryRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用客服回访记录")
	//@PreAuthorize("hasPermission('/admin/serviceReturnVisitHistory',  'admin:serviceReturnVisitHistory:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用客服回访记录", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody ServiceReturnVisitHistoryEditParam serviceReturnVisitHistoryEditParam) {
		return serviceReturnVisitHistoryRepository.changeStatus(serviceReturnVisitHistoryEditParam);
	}

}
