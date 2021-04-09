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
import com.drive.admin.service.CoachTeachTimeService;
import com.drive.admin.repository.CoachTeachTimeRepository;


/**
 * 教练课程时间表管理
 *
 * @author guyi
 */
@Api(tags = "教练课程时间表管理")
@Slf4j
@RestController
@RequestMapping("/coachTeachTime")
public class CoachTeachTimeController extends BaseController<CoachTeachTimePageQueryParam, CoachTeachTimeEntity> {

	// 教练课程时间表 服务
	@Autowired
	private CoachTeachTimeService coachTeachTimeService;
	// 教练课程时间表 业务服务
	@Autowired
	private CoachTeachTimeRepository coachTeachTimeRepository;
	// 教练课程时间表 DO-DTO转化
	@Autowired
	private CoachTeachTimeMapStruct coachTeachTimeMapStruct;

	/**
	* 教练课程时间表 分页列表
	*/
	@ApiOperation("教练课程时间表分页列表")
	@PreAuthorize("hasPermission('/admin/coachTeachTime',  'admin:coachTeachTime:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody CoachTeachTimePageQueryParam param) {
		return coachTeachTimeRepository.pageList(param);
	}
	/**
	* 教练课程时间表 列表
	*/
	@ApiOperation("教练课程时间表列表")
	@PreAuthorize("hasPermission('/admin/coachTeachTime',  'admin:coachTeachTime:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody CoachTeachTimePageQueryParam param) {
		return coachTeachTimeRepository.findList(param);
	}

	/**
	* 获取教练课程时间表
	*/
	@ApiOperation("获取教练课程时间表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachTeachTime',  'admin:coachTeachTime:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return coachTeachTimeRepository.getById(id);
	}

	/**
	 * 条件查询获取教练课程时间表
	 */
	@ApiOperation("条件查询获取教练课程时间表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachTeachTime',  'admin:coachTeachTime:query')")
	@GetMapping("/getInfo")
	public ResObject getInfo(@PathVariable CoachTeachTimePageQueryParam param) {
		return coachTeachTimeRepository.getInfo(param);
	}

	/**
	* 新增教练课程时间表
	*/
	@ApiOperation("新增教练课程时间表")
	@ApiImplicitParam(name = "CoachTeachTimeEditParam ", value = "新增教练课程时间表", dataType = "CoachTeachTimeEditParam")
	@PreAuthorize("hasPermission('/admin/coachTeachTime',  'admin:coachTeachTime:add')")
	@EventLog(message = "新增教练课程时间表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody CoachTeachTimeInstallParam coachTeachTimeInstallParam) {
		return coachTeachTimeRepository.save(coachTeachTimeInstallParam);
	}

	/**
	* 修改教练课程时间表
	*/
	@ApiOperation("修改教练课程时间表")
	@ApiImplicitParam(name = "CoachTeachTimeEditParam ", value = "修改教练课程时间表", dataType = "CoachTeachTimeEditParam")
	@PreAuthorize("hasPermission('/admin/coachTeachTime',  'admin:coachTeachTime:edit')")
	@EventLog(message = "修改教练课程时间表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody CoachTeachTimeEditParam coachTeachTimeEditParam) {
		return coachTeachTimeRepository.update(coachTeachTimeEditParam);
	}

	/**
	* 删除教练课程时间表
	*/
	@ApiOperation("删除教练课程时间表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachTeachTime',  'admin:coachTeachTime:delete')")
	@EventLog(message = "删除教练课程时间表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(coachTeachTimeService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除教练课程时间表
	*/
	@ApiOperation("通过主键删除教练课程时间表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除教练课程时间表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return coachTeachTimeRepository.deleteById(id);
	}

	/**
	* 导出教练课程时间表
	*/
	@ApiOperation("导出教练课程时间表")
	@PreAuthorize("hasPermission('/admin/coachTeachTime',  'admin:coachTeachTime:export')")
	@SneakyThrows
	@EventLog(message = "导出教练课程时间表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(CoachTeachTimePageQueryParam param, HttpServletResponse response) {
		coachTeachTimeRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用教练课程时间表")
	@PreAuthorize("hasPermission('/admin/coachTeachTime',  'admin:coachTeachTime:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用教练课程时间表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody CoachTeachTimeEditParam coachTeachTimeEditParam) {
		return coachTeachTimeRepository.changeStatus(coachTeachTimeEditParam);
	}

}
