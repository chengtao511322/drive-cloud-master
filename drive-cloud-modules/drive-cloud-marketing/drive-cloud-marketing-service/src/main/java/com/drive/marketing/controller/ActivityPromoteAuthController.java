package com.drive.marketing.controller;

import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.log.annotation.EventLog;
import com.drive.marketing.pojo.dto.ActivityPromoteAuthEditParam;
import com.drive.marketing.pojo.dto.ActivityPromoteAuthPageQueryParam;
import com.drive.marketing.pojo.entity.ActivityPromoteAuthEntity;
import com.drive.marketing.repository.ActivityPromoteAuthRepository;
import com.drive.marketing.service.ActivityPromoteAuthService;
import com.drive.marketing.service.mapstruct.ActivityPromoteAuthMapStruct;
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
 * 活动推广权限配置管理
 *
 * @author xiaoguo
 */
@Api(tags = "活动推广权限配置管理")
@Slf4j
@RestController
@RequestMapping("/activityPromoteAuth")
public class ActivityPromoteAuthController extends BaseController<ActivityPromoteAuthPageQueryParam, ActivityPromoteAuthEntity> {

	@Autowired
	private ActivityPromoteAuthService activityPromoteAuthService;
	@Autowired
	private ActivityPromoteAuthRepository activityPromoteAuthRepository;
	@Autowired
	private ActivityPromoteAuthMapStruct activityPromoteAuthMapStruct;

	/**
	* 活动推广权限配置 分页列表
	*/
	@ApiOperation("活动推广权限配置分页列表")
	//@PreAuthorize("hasPermission('/marketing/activityPromoteAuth',  'marketing:activityPromoteAuth:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid ActivityPromoteAuthPageQueryParam param) {
		return activityPromoteAuthRepository.pageList(param);
	}
	/**
	* 活动推广权限配置 分页列表
	*/
	@ApiOperation("活动推广权限配置列表")
	//@PreAuthorize("hasPermission('/marketing/activityPromoteAuth',  'marketing:activityPromoteAuth:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid ActivityPromoteAuthPageQueryParam param) {
		return activityPromoteAuthRepository.findList(param);
	}

	/**
	* 获取活动推广权限配置
	*/
	@ApiOperation("获取活动推广权限配置")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/marketing/activityPromoteAuth',  'marketing:activityPromoteAuth:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return activityPromoteAuthRepository.getById(id);
	}

	/**
	* 新增活动推广权限配置
	*/
	@ApiOperation("新增活动推广权限配置")
	@ApiImplicitParam(name = "ActivityPromoteAuthEditParam ", value = "新增活动推广权限配置", dataType = "ActivityPromoteAuthEditParam")
	//@PreAuthorize("hasPermission('/marketing/activityPromoteAuth',  'marketing:activityPromoteAuth:add')")
	@EventLog(message = "新增活动推广权限配置", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody ActivityPromoteAuthEditParam activityPromoteAuthEditParam) {
		return activityPromoteAuthRepository.save(activityPromoteAuthEditParam);
	}

	/**
	* 修改活动推广权限配置
	*/
	@ApiOperation("修改活动推广权限配置")
	@ApiImplicitParam(name = "ActivityPromoteAuthEditParam ", value = "修改活动推广权限配置", dataType = "ActivityPromoteAuthEditParam")
	//@PreAuthorize("hasPermission('/marketing/activityPromoteAuth',  'marketing:activityPromoteAuth:edit')")
	@EventLog(message = "修改活动推广权限配置", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody ActivityPromoteAuthEditParam activityPromoteAuthEditParam) {
		return activityPromoteAuthRepository.update(activityPromoteAuthEditParam);
	}

	/**
	* 删除活动推广权限配置
	*/
	@ApiOperation("删除活动推广权限配置")
	@ApiImplicitParam(name = "ids", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/marketing/activityPromoteAuth',  'marketing:activityPromoteAuth:delete')")
	@EventLog(message = "删除活动推广权限配置", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable String[] ids) {
		return R.toRes(activityPromoteAuthService.removeByIds(Arrays.asList(ids)));
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
		return activityPromoteAuthRepository.deleteById(id);
	}

	/**
	* 导出活动推广权限配置
	*/
	@ApiOperation("导出活动推广权限配置")
	//@PreAuthorize("hasPermission('/marketing/activityPromoteAuth',  'marketing:activityPromoteAuth:export')")
	@SneakyThrows
	@EventLog(message = "导出活动推广权限配置", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(ActivityPromoteAuthPageQueryParam param, HttpServletResponse response) {
			activityPromoteAuthRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用活动推广权限配置")
	//@PreAuthorize("hasPermission('/marketing/activityPromoteAuth',  'marketing:activityPromoteAuth:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用活动推广权限配置", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody ActivityPromoteAuthEditParam activityPromoteAuthEditParam) {
		return activityPromoteAuthRepository.changeStatus(activityPromoteAuthEditParam);
	}

}
