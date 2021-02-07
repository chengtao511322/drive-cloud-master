package com.drive.marketing.controller;

import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.log.annotation.EventLog;
import com.drive.marketing.pojo.dto.ActivityApplyEditParam;
import com.drive.marketing.pojo.dto.ActivityApplyPageQueryParam;
import com.drive.marketing.pojo.entity.ActivityApplyEntity;
import com.drive.marketing.repository.ActivityApplyRepository;
import com.drive.marketing.service.ActivityApplyService;
import com.drive.marketing.service.mapstruct.ActivityApplyMapStruct;
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


/**
 * 活动参加记录表管理
 *
 * @author xiaoguo
 */
@Api(tags = "活动参加记录表管理")
@Slf4j
@RestController
@RequestMapping("/activityApply")
public class ActivityApplyController extends BaseController<ActivityApplyPageQueryParam, ActivityApplyEntity> {

	@Autowired
	private ActivityApplyService activityApplyService;
	@Autowired
	private ActivityApplyRepository activityApplyRepository;
	@Autowired
	private ActivityApplyMapStruct activityApplyMapStruct;

	/**
	* 活动参加记录表 分页列表
	*/
	@ApiOperation("活动参加记录表分页列表")
	@PreAuthorize("hasPermission('/marketing/activityApply',  'marketing:activityApply:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid ActivityApplyPageQueryParam param) {
		return activityApplyRepository.pageList(param);
	}
	/**
	* 活动参加记录表 分页列表
	*/
	@ApiOperation("活动参加记录表列表")
	@PreAuthorize("hasPermission('/marketing/activityApply',  'marketing:activityApply:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid ActivityApplyPageQueryParam param) {
		return activityApplyRepository.findList(param);
	}

	/**
	* 获取活动参加记录表
	*/
	@ApiOperation("获取活动参加记录表")
	@ApiImplicitParam(name = "${pkColumn.propertyName}", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/marketing/activityApply',  'marketing:activityApply:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return activityApplyRepository.getById(id);
	}

	/**
	* 新增活动参加记录表
	*/
	@ApiOperation("新增活动参加记录表")
	@ApiImplicitParam(name = "ActivityApplyEditParam ", value = "新增活动参加记录表", dataType = "ActivityApplyEditParam")
	@PreAuthorize("hasPermission('/marketing/activityApply',  'marketing:activityApply:add')")
	@EventLog(message = "新增活动参加记录表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody ActivityApplyEditParam activityApplyEditParam) {
		return activityApplyRepository.save(activityApplyEditParam);
	}

	/**
	* 修改活动参加记录表
	*/
	@ApiOperation("修改活动参加记录表")
	@ApiImplicitParam(name = "ActivityApplyEditParam ", value = "修改活动参加记录表", dataType = "ActivityApplyEditParam")
	@PreAuthorize("hasPermission('/marketing/activityApply',  'marketing:activityApply:edit')")
	@EventLog(message = "修改活动参加记录表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody ActivityApplyEditParam activityApplyEditParam) {
		return activityApplyRepository.update(activityApplyEditParam);
	}

	/**
	* 删除活动参加记录表
	*/
	@ApiOperation("删除活动参加记录表")
	@ApiImplicitParam(name = "${ids}", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/marketing/activityApply',  'marketing:activityApply:delete')")
	@EventLog(message = "删除活动参加记录表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable String[] ids) {
		return R.toRes(activityApplyService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除教练信息表
	*/
	@ApiOperation("删除教练信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/marketing/delById',  'admin:marketing:delById')")
	@EventLog(message = "删除教练信息表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return activityApplyRepository.deleteById(id);
	}

	/**
	* 导出活动参加记录表
	*/
	@ApiOperation("导出活动参加记录表")
	@PreAuthorize("hasPermission('/marketing/activityApply',  'marketing:activityApply:export')")
	@SneakyThrows
	@EventLog(message = "导出活动参加记录表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(ActivityApplyPageQueryParam param, HttpServletResponse response) {
			activityApplyRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用活动参加记录表")
	@PreAuthorize("hasPermission('/marketing/activityApply',  'marketing:activityApply:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用活动参加记录表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody ActivityApplyEditParam activityApplyEditParam) {
		return activityApplyRepository.changeStatus(activityApplyEditParam);
	}

}
