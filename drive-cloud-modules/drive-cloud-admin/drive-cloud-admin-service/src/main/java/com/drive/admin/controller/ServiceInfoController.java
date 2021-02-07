package com.drive.admin.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.common.security.utils.SecurityUtils;
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
import com.drive.admin.service.ServiceInfoService;
import com.drive.admin.repository.ServiceInfoRepository;


/**
 * 客服人员信息表管理
 *
 * @author xiaoguo
 */
@Api(tags = "客服人员信息表管理")
@Slf4j
@RestController
@RequestMapping("/serviceInfo")
public class ServiceInfoController extends BaseController<ServiceInfoPageQueryParam, ServiceInfoEntity> {

	@Autowired
	private ServiceInfoService serviceInfoService;
	@Autowired
	private ServiceInfoRepository serviceInfoRepository;
	@Autowired
	private ServiceInfoMapStruct serviceInfoMapStruct;

	/**
	* 客服人员信息表 分页列表
	*/
	@ApiOperation("客服人员信息表分页列表")
	@PreAuthorize("hasPermission('/admin/serviceInfo',  'admin:serviceInfo:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid ServiceInfoPageQueryParam param) {
		return serviceInfoRepository.pageList(param);
	}
	/**
	* 客服人员信息表 列表
	*/
	@ApiOperation("客服人员信息表列表")
	@PreAuthorize("hasPermission('/admin/serviceInfo',  'admin:serviceInfo:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid ServiceInfoPageQueryParam param) {
		return serviceInfoRepository.findList(param);
	}

	/**
	* 获取客服人员信息表
	*/
	@ApiOperation("获取客服人员信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/serviceInfo',  'admin:serviceInfo:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return serviceInfoRepository.getById(id);
	}

	/**
	* 新增客服人员信息表
	*/
	@ApiOperation("新增客服人员信息表")
	@ApiImplicitParam(name = "ServiceInfoEditParam ", value = "新增客服人员信息表", dataType = "ServiceInfoEditParam")
	@PreAuthorize("hasPermission('/admin/serviceInfo',  'admin:serviceInfo:add')")
	@EventLog(message = "新增客服人员信息表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody ServiceInfoEditParam serviceInfoEditParam) {
		return serviceInfoRepository.save(serviceInfoEditParam);
	}

	/**
	* 修改客服人员信息表
	*/
	@ApiOperation("修改客服人员信息表")
	@ApiImplicitParam(name = "ServiceInfoEditParam ", value = "修改客服人员信息表", dataType = "ServiceInfoEditParam")
	@PreAuthorize("hasPermission('/admin/serviceInfo',  'admin:serviceInfo:edit')")
	@EventLog(message = "修改客服人员信息表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody ServiceInfoEditParam serviceInfoEditParam) {
		return serviceInfoRepository.update(serviceInfoEditParam);
	}

	/**
	* 删除客服人员信息表
	*/
	@ApiOperation("删除客服人员信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/serviceInfo',  'admin:serviceInfo:delete')")
	@EventLog(message = "删除客服人员信息表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(serviceInfoService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除客服人员信息表
	*/
	@ApiOperation("通过主键删除客服人员信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除客服人员信息表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return serviceInfoRepository.deleteById(id);
	}

	/**
	* 导出客服人员信息表
	*/
	@ApiOperation("导出客服人员信息表")
	@PreAuthorize("hasPermission('/admin/serviceInfo',  'admin:serviceInfo:export')")
	@SneakyThrows
	@EventLog(message = "导出客服人员信息表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(ServiceInfoPageQueryParam param, HttpServletResponse response) {
		serviceInfoRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用客服人员信息表")
	@PreAuthorize("hasPermission('/admin/serviceInfo',  'admin:serviceInfo:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用客服人员信息表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody ServiceInfoEditParam serviceInfoEditParam) {
		return serviceInfoRepository.changeStatus(serviceInfoEditParam);
	}

	/**
	 * 重置密码
	 */
	@PreAuthorize("hasPermission('/admin/serviceInfo',  'admin:serviceInfo:resetPwd')")
	@EventLog(message = "重置密码", businessType = EventLogEnum.UPDATE)
	@PutMapping("/resetPwd")
	public ResObject resetPwd(@RequestBody ServiceInfoEditParam serviceInfoEditParam) {
		return serviceInfoRepository.resetPwd(serviceInfoEditParam);
	}

}
