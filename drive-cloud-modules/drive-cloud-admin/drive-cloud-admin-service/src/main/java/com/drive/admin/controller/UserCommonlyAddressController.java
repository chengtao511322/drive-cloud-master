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
import com.drive.admin.service.UserCommonlyAddressService;
import com.drive.admin.repository.UserCommonlyAddressRepository;


/**
 * 用户常用地址关联表管理
 *
 * @author xiaoguo
 */
@Api(tags = "用户常用地址关联表管理")
@Slf4j
@RestController
@RequestMapping("/userCommonlyAddress")
public class UserCommonlyAddressController extends BaseController<UserCommonlyAddressPageQueryParam, UserCommonlyAddressEntity> {

	// 用户常用地址关联表 服务
	@Autowired
	private UserCommonlyAddressService userCommonlyAddressService;
	// 用户常用地址关联表 业务服务
	@Autowired
	private UserCommonlyAddressRepository userCommonlyAddressRepository;
	// 用户常用地址关联表 DO-DTO转化
	@Autowired
	private UserCommonlyAddressMapStruct userCommonlyAddressMapStruct;

	/**
	* 用户常用地址关联表 分页列表
	*/
	@ApiOperation("用户常用地址关联表分页列表")
	@PreAuthorize("hasPermission('/admin/userCommonlyAddress',  'admin:userCommonlyAddress:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody UserCommonlyAddressPageQueryParam param) {
		return userCommonlyAddressRepository.pageList(param);
	}
	/**
	* 用户常用地址关联表 列表
	*/
	@ApiOperation("用户常用地址关联表列表")
	@PreAuthorize("hasPermission('/admin/userCommonlyAddress',  'admin:userCommonlyAddress:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody UserCommonlyAddressPageQueryParam param) {
		return userCommonlyAddressRepository.findList(param);
	}

	/**
	* 获取用户常用地址关联表
	*/
	@ApiOperation("获取用户常用地址关联表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/userCommonlyAddress',  'admin:userCommonlyAddress:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return userCommonlyAddressRepository.getById(id);
	}

	/**
	 * 条件查询获取用户常用地址关联表
	 */
	@ApiOperation("条件查询获取用户常用地址关联表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/userCommonlyAddress',  'admin:userCommonlyAddress:query')")
	@PostMapping("/getInfo")
	public ResObject getInfo(@RequestBody UserCommonlyAddressPageQueryParam param) {
		return userCommonlyAddressRepository.getInfo(param);
	}

	/**
	* 新增用户常用地址关联表
	 * author:xiaoguo
	*/
	@ApiOperation("新增用户常用地址关联表")
	@ApiImplicitParam(name = "UserCommonlyAddressEditParam ", value = "新增用户常用地址关联表", dataType = "UserCommonlyAddressEditParam")
	@PreAuthorize("hasPermission('/admin/userCommonlyAddress',  'admin:userCommonlyAddress:add')")
	@EventLog(message = "新增用户常用地址关联表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody UserCommonlyAddressInstallParam userCommonlyAddressInstallParam) {
		return userCommonlyAddressRepository.save(userCommonlyAddressInstallParam);
	}

	/**
	* 修改用户常用地址关联表
	*/
	@ApiOperation("修改用户常用地址关联表")
	@ApiImplicitParam(name = "UserCommonlyAddressEditParam ", value = "修改用户常用地址关联表", dataType = "UserCommonlyAddressEditParam")
	@PreAuthorize("hasPermission('/admin/userCommonlyAddress',  'admin:userCommonlyAddress:edit')")
	@EventLog(message = "修改用户常用地址关联表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody UserCommonlyAddressEditParam userCommonlyAddressEditParam) {
		return userCommonlyAddressRepository.update(userCommonlyAddressEditParam);
	}

	/**
	* 删除用户常用地址关联表
	*/
	@ApiOperation("删除用户常用地址关联表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/userCommonlyAddress',  'admin:userCommonlyAddress:delete')")
	@EventLog(message = "删除用户常用地址关联表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable String[] ids) {
		return R.toRes(userCommonlyAddressService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除用户常用地址关联表
	*/
	@ApiOperation("通过主键删除用户常用地址关联表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delete')")
	@EventLog(message = "通过主键删除用户常用地址关联表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return userCommonlyAddressRepository.deleteById(id);
	}

	/**
	* 导出用户常用地址关联表
	*/
	@ApiOperation("导出用户常用地址关联表")
	@PreAuthorize("hasPermission('/admin/userCommonlyAddress',  'admin:userCommonlyAddress:export')")
	@SneakyThrows
	@EventLog(message = "导出用户常用地址关联表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody UserCommonlyAddressPageQueryParam param, HttpServletResponse response) {
		userCommonlyAddressRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用用户常用地址关联表")
	@PreAuthorize("hasPermission('/admin/userCommonlyAddress',  'admin:userCommonlyAddress:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用用户常用地址关联表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody UserCommonlyAddressEditParam userCommonlyAddressEditParam) {
		return userCommonlyAddressRepository.changeStatus(userCommonlyAddressEditParam);
	}

}
