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
import com.drive.admin.service.MessageService;
import com.drive.admin.repository.MessageRepository;


/**
 * 平台发送短信表管理
 *
 * @author xiaoguo
 */
@Api(tags = "平台发送短信表管理")
@Slf4j
@RestController
@RequestMapping("/message")
public class MessageController extends BaseController<MessagePageQueryParam, MessageEntity> {

	// 平台发送短信表 服务
	@Autowired
	private MessageService messageService;
	// 平台发送短信表 业务服务
	@Autowired
	private MessageRepository messageRepository;
	// 平台发送短信表 DO-DTO转化
	@Autowired
	private MessageMapStruct messageMapStruct;

	/**
	* 平台发送短信表 分页列表
	*/
	@ApiOperation("平台发送短信表分页列表")
	//@PreAuthorize("hasPermission('/admin/message',  'admin:message:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody MessagePageQueryParam param) {
		return messageRepository.pageList(param);
	}
	/**
	* 平台发送短信表 列表
	*/
	@ApiOperation("平台发送短信表列表")
	//@PreAuthorize("hasPermission('/admin/message',  'admin:message:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody MessagePageQueryParam param) {
		return messageRepository.findList(param);
	}

	/**
	* 获取平台发送短信表
	*/
	@ApiOperation("获取平台发送短信表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/message',  'admin:message:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return messageRepository.getById(id);
	}

	/**
	 * 条件查询获取平台发送短信表
	 */
	@ApiOperation("条件查询获取平台发送短信表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/message',  'admin:message:query')")
	@PostMapping("/getInfo")
	public ResObject getInfo(@RequestBody MessagePageQueryParam param) {
		return messageRepository.getInfo(param);
	}

	/**
	* 新增平台发送短信表
	 * author:xiaoguo
	*/
	@ApiOperation("新增平台发送短信表")
	@ApiImplicitParam(name = "MessageEditParam ", value = "新增平台发送短信表", dataType = "MessageEditParam")
	//@PreAuthorize("hasPermission('/admin/message',  'admin:message:add')")
	@EventLog(message = "新增平台发送短信表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody MessageInstallParam messageInstallParam) {
		return messageRepository.save(messageInstallParam);
	}

	/**
	* 修改平台发送短信表
	*/
	@ApiOperation("修改平台发送短信表")
	@ApiImplicitParam(name = "MessageEditParam ", value = "修改平台发送短信表", dataType = "MessageEditParam")
	//@PreAuthorize("hasPermission('/admin/message',  'admin:message:edit')")
	@EventLog(message = "修改平台发送短信表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody MessageEditParam messageEditParam) {
		return messageRepository.update(messageEditParam);
	}

	/**
	* 删除平台发送短信表
	*/
	@ApiOperation("删除平台发送短信表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/message',  'admin:message:delete')")
	@EventLog(message = "删除平台发送短信表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable String[] ids) {
		return R.toRes(messageService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除平台发送短信表
	*/
	@ApiOperation("通过主键删除平台发送短信表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delete')")
	@EventLog(message = "通过主键删除平台发送短信表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return messageRepository.deleteById(id);
	}

	/**
	* 导出平台发送短信表
	*/
	@ApiOperation("导出平台发送短信表")
	//@PreAuthorize("hasPermission('/admin/message',  'admin:message:export')")
	@SneakyThrows
	@EventLog(message = "导出平台发送短信表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody MessagePageQueryParam param, HttpServletResponse response) {
		messageRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用平台发送短信表")
	//@PreAuthorize("hasPermission('/admin/message',  'admin:message:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用平台发送短信表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody MessageEditParam messageEditParam) {
		return messageRepository.changeStatus(messageEditParam);
	}

}
