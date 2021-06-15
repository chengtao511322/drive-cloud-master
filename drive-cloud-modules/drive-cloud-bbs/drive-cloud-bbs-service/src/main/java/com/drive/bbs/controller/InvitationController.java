package com.drive.bbs.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.bbs.pojo.dto.InvitationEditParam;
import com.drive.bbs.pojo.dto.InvitationInstallParam;
import com.drive.bbs.pojo.dto.InvitationPageQueryParam;
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
import com.drive.bbs.pojo.entity.*;
import com.drive.bbs.service.mapstruct.*;
import com.drive.bbs.service.InvitationService;
import com.drive.bbs.repository.InvitationRepository;


/**
 * 论坛帖子表管理
 *
 * @author xiaoguo
 */
@Api(tags = "论坛帖子表管理")
@Slf4j
@RestController
@RequestMapping("/invitation")
public class InvitationController extends BaseController<InvitationPageQueryParam, InvitationEntity> {

	// 论坛帖子表 服务
	@Autowired
	private InvitationService invitationService;
	// 论坛帖子表 业务服务
	@Autowired
	private InvitationRepository invitationRepository;
	// 论坛帖子表 DO-DTO转化
	@Autowired
	private InvitationMapStruct invitationMapStruct;

	/**
	* 论坛帖子表 分页列表
	*/
	@ApiOperation("论坛帖子表分页列表")
	@PreAuthorize("hasPermission('/bbs/invitation',  'bbs:invitation:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody InvitationPageQueryParam param) {
		return invitationRepository.pageList(param);
	}
	/**
	* 论坛帖子表 列表
	*/
	@ApiOperation("论坛帖子表列表")
	@PreAuthorize("hasPermission('/bbs/invitation',  'bbs:invitation:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody InvitationPageQueryParam param) {
		return invitationRepository.findList(param);
	}

	/**
	* 获取论坛帖子表
	*/
	@ApiOperation("获取论坛帖子表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/bbs/invitation',  'bbs:invitation:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return invitationRepository.getById(id);
	}

	/**
	 * 条件查询获取论坛帖子表
	 */
	@ApiOperation("条件查询获取论坛帖子表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/bbs/invitation',  'bbs:invitation:query')")
	@PostMapping("/getInfo")
	public ResObject getInfo(@RequestBody InvitationPageQueryParam param) {
		return invitationRepository.getInfo(param);
	}

	/**
	* 新增论坛帖子表
	 * author:xiaoguo
	*/
	@ApiOperation("新增论坛帖子表")
	@ApiImplicitParam(name = "InvitationEditParam ", value = "新增论坛帖子表", dataType = "InvitationEditParam")
	@PreAuthorize("hasPermission('/bbs/invitation',  'bbs:invitation:add')")
	@EventLog(message = "新增论坛帖子表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody InvitationInstallParam invitationInstallParam) {
		return invitationRepository.save(invitationInstallParam);
	}

	/**
	* 修改论坛帖子表
	*/
	@ApiOperation("修改论坛帖子表")
	@ApiImplicitParam(name = "InvitationEditParam ", value = "修改论坛帖子表", dataType = "InvitationEditParam")
	@PreAuthorize("hasPermission('/bbs/invitation',  'bbs:invitation:edit')")
	@EventLog(message = "修改论坛帖子表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody InvitationEditParam invitationEditParam) {
		return invitationRepository.update(invitationEditParam);
	}

	/**
	* 删除论坛帖子表
	*/
	@ApiOperation("删除论坛帖子表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/bbs/invitation',  'bbs:invitation:delete')")
	@EventLog(message = "删除论坛帖子表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable String[] ids) {
		return invitationRepository.deleteByIds(ids);
	}

	/**
	* 通过主键删除论坛帖子表
	*/
	@ApiOperation("通过主键删除论坛帖子表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delete')")
	@EventLog(message = "通过主键删除论坛帖子表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return invitationRepository.deleteById(id);
	}

	/**
	* 导出论坛帖子表
	*/
	@ApiOperation("导出论坛帖子表")
	@PreAuthorize("hasPermission('/bbs/invitation',  'bbs:invitation:export')")
	@SneakyThrows
	@EventLog(message = "导出论坛帖子表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody InvitationPageQueryParam param, HttpServletResponse response) {
		invitationRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用论坛帖子表")
	@PreAuthorize("hasPermission('/bbs/invitation',  'bbs:invitation:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用论坛帖子表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody InvitationEditParam invitationEditParam) {
		return invitationRepository.changeStatus(invitationEditParam);
	}

}
