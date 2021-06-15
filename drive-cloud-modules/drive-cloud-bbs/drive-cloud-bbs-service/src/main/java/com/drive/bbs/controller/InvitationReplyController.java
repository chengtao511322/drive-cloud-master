package com.drive.bbs.controller;

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
import com.drive.bbs.pojo.entity.*;
import com.drive.bbs.pojo.vo.*;
import com.drive.bbs.pojo.dto.*;
import com.drive.bbs.service.mapstruct.*;
import com.drive.bbs.service.InvitationReplyService;
import com.drive.bbs.repository.InvitationReplyRepository;


/**
 * 论坛帖子回复表管理
 *
 * @author xiaoguo
 */
@Api(tags = "论坛帖子回复表管理")
@Slf4j
@RestController
@RequestMapping("/invitationReply")
public class InvitationReplyController extends BaseController<InvitationReplyPageQueryParam, InvitationReplyEntity> {

	// 论坛帖子回复表 服务
	@Autowired
	private InvitationReplyService invitationReplyService;
	// 论坛帖子回复表 业务服务
	@Autowired
	private InvitationReplyRepository invitationReplyRepository;
	// 论坛帖子回复表 DO-DTO转化
	@Autowired
	private InvitationReplyMapStruct invitationReplyMapStruct;

	/**
	* 论坛帖子回复表 分页列表
	*/
	@ApiOperation("论坛帖子回复表分页列表")
	@PreAuthorize("hasPermission('/bbs/invitationReply',  'bbs:invitationReply:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody InvitationReplyPageQueryParam param) {
		return invitationReplyRepository.pageList(param);
	}
	/**
	* 论坛帖子回复表 列表
	*/
	@ApiOperation("论坛帖子回复表列表")
	@PreAuthorize("hasPermission('/bbs/invitationReply',  'bbs:invitationReply:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody InvitationReplyPageQueryParam param) {
		return invitationReplyRepository.findList(param);
	}

	/**
	* 获取论坛帖子回复表
	*/
	@ApiOperation("获取论坛帖子回复表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/bbs/invitationReply',  'bbs:invitationReply:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return invitationReplyRepository.getById(id);
	}

	/**
	 * 条件查询获取论坛帖子回复表
	 */
	@ApiOperation("条件查询获取论坛帖子回复表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/bbs/invitationReply',  'bbs:invitationReply:query')")
	@PostMapping("/getInfo")
	public ResObject getInfo(@RequestBody InvitationReplyPageQueryParam param) {
		return invitationReplyRepository.getInfo(param);
	}

	/**
	* 新增论坛帖子回复表
	 * author:xiaoguo
	*/
	@ApiOperation("新增论坛帖子回复表")
	@ApiImplicitParam(name = "InvitationReplyEditParam ", value = "新增论坛帖子回复表", dataType = "InvitationReplyEditParam")
	@PreAuthorize("hasPermission('/bbs/invitationReply',  'bbs:invitationReply:add')")
	@EventLog(message = "新增论坛帖子回复表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody InvitationReplyInstallParam invitationReplyInstallParam) {
		return invitationReplyRepository.save(invitationReplyInstallParam);
	}

	/**
	* 修改论坛帖子回复表
	*/
	@ApiOperation("修改论坛帖子回复表")
	@ApiImplicitParam(name = "InvitationReplyEditParam ", value = "修改论坛帖子回复表", dataType = "InvitationReplyEditParam")
	@PreAuthorize("hasPermission('/bbs/invitationReply',  'bbs:invitationReply:edit')")
	@EventLog(message = "修改论坛帖子回复表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody InvitationReplyEditParam invitationReplyEditParam) {
		return invitationReplyRepository.update(invitationReplyEditParam);
	}

	/**
	* 删除论坛帖子回复表
	*/
	@ApiOperation("删除论坛帖子回复表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/bbs/invitationReply',  'bbs:invitationReply:delete')")
	@EventLog(message = "删除论坛帖子回复表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable String[] ids) {
		return invitationReplyRepository.deleteByIds(ids);
	}

	/**
	* 通过主键删除论坛帖子回复表
	*/
	@ApiOperation("通过主键删除论坛帖子回复表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delete')")
	@EventLog(message = "通过主键删除论坛帖子回复表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return invitationReplyRepository.deleteById(id);
	}

	/**
	* 导出论坛帖子回复表
	*/
	@ApiOperation("导出论坛帖子回复表")
	@PreAuthorize("hasPermission('/bbs/invitationReply',  'bbs:invitationReply:export')")
	@SneakyThrows
	@EventLog(message = "导出论坛帖子回复表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody InvitationReplyPageQueryParam param, HttpServletResponse response) {
		invitationReplyRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用论坛帖子回复表")
	@PreAuthorize("hasPermission('/bbs/invitationReply',  'bbs:invitationReply:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用论坛帖子回复表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody InvitationReplyEditParam invitationReplyEditParam) {
		return invitationReplyRepository.changeStatus(invitationReplyEditParam);
	}

}
