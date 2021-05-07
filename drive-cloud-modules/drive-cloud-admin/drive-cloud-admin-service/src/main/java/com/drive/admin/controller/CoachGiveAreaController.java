package com.drive.admin.controller;

import com.drive.admin.pojo.dto.CoachGiveAreaEditParam;
import com.drive.admin.pojo.dto.CoachGiveAreaPageQueryParam;
import com.drive.admin.pojo.entity.CoachGiveAreaEntity;
import com.drive.admin.repository.CoachGiveAreaRepository;
import com.drive.admin.service.CoachGiveAreaService;
import com.drive.admin.service.mapstruct.CoachGiveAreaMapStruct;
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
 * 教练授课区域表管理
 *
 * @author xiaoguo
 */
@Api(tags = "教练授课区域表管理")
@Slf4j
@RestController
@RequestMapping("/coachGiveArea")
public class CoachGiveAreaController extends BaseController<CoachGiveAreaPageQueryParam, CoachGiveAreaEntity> {

	// 教练授课区域表 服务
	@Autowired
	private CoachGiveAreaService coachGiveAreaService;
	// 教练授课区域表 业务服务
	@Autowired
	private CoachGiveAreaRepository coachGiveAreaRepository;
	// 教练授课区域表 DO-DTO转化
	@Autowired
	private CoachGiveAreaMapStruct coachGiveAreaMapStruct;

	/**
	* 教练授课区域表 分页列表
	*/
	@ApiOperation("教练授课区域表分页列表")
	//@PreAuthorize("hasPermission('/admin/coachGiveArea',  'admin:coachGiveArea:query')")
	@GetMapping(value = "/pageList")
	public ResObject pageList(@Valid CoachGiveAreaPageQueryParam param) {
		return coachGiveAreaRepository.pageList(param);
	}
	/**
	* 教练授课区域表 列表
	*/
	@ApiOperation("教练授课区域表列表")
	//@PreAuthorize("hasPermission('/admin/coachGiveArea',  'admin:coachGiveArea:query')")
	@GetMapping(value = "/findList")
	public ResObject findList(@Valid CoachGiveAreaPageQueryParam param) {
		return coachGiveAreaRepository.findList(param);
	}

	/**
	* 获取教练授课区域表
	*/
	@ApiOperation("获取教练授课区域表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachGiveArea',  'admin:coachGiveArea:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return coachGiveAreaRepository.getById(id);
	}

	/**
	 * 条件查询获取教练授课区域表
	 */
	@ApiOperation("条件查询获取教练授课区域表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachGiveArea',  'admin:coachGiveArea:query')")
	@GetMapping("/getInfo")
	public ResObject getInfo(@PathVariable CoachGiveAreaPageQueryParam param) {
		return coachGiveAreaRepository.getInfo(param);
	}

	/**
	* 新增教练授课区域表
	*/
	@ApiOperation("新增教练授课区域表")
	@ApiImplicitParam(name = "CoachGiveAreaEditParam ", value = "新增教练授课区域表", dataType = "CoachGiveAreaEditParam")
	//@PreAuthorize("hasPermission('/admin/coachGiveArea',  'admin:coachGiveArea:add')")
	@EventLog(message = "新增教练授课区域表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody CoachGiveAreaEditParam coachGiveAreaEditParam) {
		return coachGiveAreaRepository.save(coachGiveAreaEditParam);
	}

	/**
	* 修改教练授课区域表
	*/
	@ApiOperation("修改教练授课区域表")
	@ApiImplicitParam(name = "CoachGiveAreaEditParam ", value = "修改教练授课区域表", dataType = "CoachGiveAreaEditParam")
	//@PreAuthorize("hasPermission('/admin/coachGiveArea',  'admin:coachGiveArea:edit')")
	@EventLog(message = "修改教练授课区域表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody CoachGiveAreaEditParam coachGiveAreaEditParam) {
		return coachGiveAreaRepository.update(coachGiveAreaEditParam);
	}

	/**
	* 删除教练授课区域表
	*/
	@ApiOperation("删除教练授课区域表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachGiveArea',  'admin:coachGiveArea:delete')")
	@EventLog(message = "删除教练授课区域表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(coachGiveAreaService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除教练授课区域表
	*/
	@ApiOperation("通过主键删除教练授课区域表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除教练授课区域表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return coachGiveAreaRepository.deleteById(id);
	}

	/**
	* 导出教练授课区域表
	*/
	@ApiOperation("导出教练授课区域表")
	//@PreAuthorize("hasPermission('/admin/coachGiveArea',  'admin:coachGiveArea:export')")
	@SneakyThrows
	@EventLog(message = "导出教练授课区域表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(CoachGiveAreaPageQueryParam param, HttpServletResponse response) {
		coachGiveAreaRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用教练授课区域表")
	//@PreAuthorize("hasPermission('/admin/coachGiveArea',  'admin:coachGiveArea:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用教练授课区域表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody CoachGiveAreaEditParam coachGiveAreaEditParam) {
		return coachGiveAreaRepository.changeStatus(coachGiveAreaEditParam);
	}

}
