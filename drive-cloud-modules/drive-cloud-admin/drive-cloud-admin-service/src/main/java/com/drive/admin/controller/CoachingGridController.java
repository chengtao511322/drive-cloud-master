package com.drive.admin.controller;

import com.drive.admin.pojo.dto.CoachingGridEditParam;
import com.drive.admin.pojo.dto.CoachingGridInstallParam;
import com.drive.admin.pojo.dto.CoachingGridPageQueryParam;
import com.drive.admin.pojo.entity.CoachingGridEntity;
import com.drive.admin.repository.CoachingGridRepository;
import com.drive.admin.service.CoachingGridService;
import com.drive.admin.service.mapstruct.CoachingGridMapStruct;
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
 * 平台训练场地表管理
 *
 * @author xiaoguo
 */
@Api(tags = "平台训练场地表管理")
@Slf4j
@RestController
@RequestMapping("/coachingGrid")
public class CoachingGridController extends BaseController<CoachingGridPageQueryParam, CoachingGridEntity> {

	// 平台训练场地表 服务
	@Autowired
	private CoachingGridService coachingGridService;
	// 平台训练场地表 业务服务
	@Autowired
	private CoachingGridRepository coachingGridRepository;
	// 平台训练场地表 DO-DTO转化
	@Autowired
	private CoachingGridMapStruct coachingGridMapStruct;

	/**
	* 平台训练场地表 分页列表
	*/
	@ApiOperation("平台训练场地表分页列表")
	//@PreAuthorize("hasPermission('/admin/coachingGrid',  'admin:coachingGrid:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid CoachingGridPageQueryParam param) {
		return coachingGridRepository.pageList(param);
	}
	/**
	* 平台训练场地表 列表
	*/
	@ApiOperation("平台训练场地表列表")
	//@PreAuthorize("hasPermission('/admin/coachingGrid',  'admin:coachingGrid:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid CoachingGridPageQueryParam param) {
		return coachingGridRepository.findList(param);
	}

	/**
	* 获取平台训练场地表
	*/
	@ApiOperation("获取平台训练场地表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachingGrid',  'admin:coachingGrid:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return coachingGridRepository.getById(id);
	}

	/**
	* 新增平台训练场地表
	*/
	@ApiOperation("新增平台训练场地表")
	@ApiImplicitParam(name = "CoachingGridEditParam ", value = "新增平台训练场地表", dataType = "CoachingGridEditParam")
	//@PreAuthorize("hasPermission('/admin/coachingGrid',  'admin:coachingGrid:add')")
	@EventLog(message = "新增平台训练场地表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody CoachingGridInstallParam coachingGridInstallParam) {
		return coachingGridRepository.save(coachingGridInstallParam);
	}

	/**
	* 修改平台训练场地表
	*/
	@ApiOperation("修改平台训练场地表")
	@ApiImplicitParam(name = "CoachingGridEditParam ", value = "修改平台训练场地表", dataType = "CoachingGridEditParam")
	//@PreAuthorize("hasPermission('/admin/coachingGrid',  'admin:coachingGrid:edit')")
	@EventLog(message = "修改平台训练场地表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody CoachingGridEditParam coachingGridEditParam) {
		return coachingGridRepository.update(coachingGridEditParam);
	}

	/**
	* 删除平台训练场地表
	*/
	@ApiOperation("删除平台训练场地表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachingGrid',  'admin:coachingGrid:delete')")
	@EventLog(message = "删除平台训练场地表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(coachingGridService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除平台训练场地表
	*/
	@ApiOperation("通过主键删除平台训练场地表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除平台训练场地表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return coachingGridRepository.deleteById(id);
	}

	/**
	* 导出平台训练场地表
	*/
	@ApiOperation("导出平台训练场地表")
	//@PreAuthorize("hasPermission('/admin/coachingGrid',  'admin:coachingGrid:export')")
	@SneakyThrows
	@EventLog(message = "导出平台训练场地表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(CoachingGridPageQueryParam param, HttpServletResponse response) {
		coachingGridRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用平台训练场地表")
	//@PreAuthorize("hasPermission('/admin/coachingGrid',  'admin:coachingGrid:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用平台训练场地表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody CoachingGridEditParam coachingGridEditParam) {
		return coachingGridRepository.changeStatus(coachingGridEditParam);
	}

}
