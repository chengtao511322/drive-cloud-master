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
import com.drive.admin.service.StudentCoachAppraiseService;
import com.drive.admin.repository.StudentCoachAppraiseRepository;


/**
 * 学员教练互评表管理
 *
 * @author xiaoguo
 */
@Api(tags = "学员教练互评表管理")
@Slf4j
@RestController
@RequestMapping("/studentCoachAppraise")
public class StudentCoachAppraiseController extends BaseController<StudentCoachAppraisePageQueryParam, StudentCoachAppraiseEntity> {

	@Autowired
	private StudentCoachAppraiseService studentCoachAppraiseService;
	@Autowired
	private StudentCoachAppraiseRepository studentCoachAppraiseRepository;
	@Autowired
	private StudentCoachAppraiseMapStruct studentCoachAppraiseMapStruct;

	/**
	* 学员教练互评表 分页列表
	*/
	@ApiOperation("学员教练互评表分页列表")
	@PreAuthorize("hasPermission('/admin/studentCoachAppraise',  'admin:studentCoachAppraise:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid StudentCoachAppraisePageQueryParam param) {
		return studentCoachAppraiseRepository.pageList(param);
	}
	/**
	* 学员教练互评表 列表
	*/
	@ApiOperation("学员教练互评表列表")
	@PreAuthorize("hasPermission('/admin/studentCoachAppraise',  'admin:studentCoachAppraise:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid StudentCoachAppraisePageQueryParam param) {
		return studentCoachAppraiseRepository.findList(param);
	}

	/**
	* 获取学员教练互评表
	*/
	@ApiOperation("获取学员教练互评表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/studentCoachAppraise',  'admin:studentCoachAppraise:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return studentCoachAppraiseRepository.getInfo(id);
	}

	/**
	* 新增学员教练互评表
	*/
	@ApiOperation("新增学员教练互评表")
	@ApiImplicitParam(name = "StudentCoachAppraiseEditParam ", value = "新增学员教练互评表", dataType = "StudentCoachAppraiseEditParam")
	@PreAuthorize("hasPermission('/admin/studentCoachAppraise',  'admin:studentCoachAppraise:add')")
	@EventLog(message = "新增学员教练互评表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody StudentCoachAppraiseEditParam studentCoachAppraiseEditParam) {
		return studentCoachAppraiseRepository.save(studentCoachAppraiseEditParam);
	}

	/**
	* 修改学员教练互评表
	*/
	@ApiOperation("修改学员教练互评表")
	@ApiImplicitParam(name = "StudentCoachAppraiseEditParam ", value = "修改学员教练互评表", dataType = "StudentCoachAppraiseEditParam")
	@PreAuthorize("hasPermission('/admin/studentCoachAppraise',  'admin:studentCoachAppraise:edit')")
	@EventLog(message = "修改学员教练互评表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody StudentCoachAppraiseEditParam studentCoachAppraiseEditParam) {
		return studentCoachAppraiseRepository.update(studentCoachAppraiseEditParam);
	}

	/**
	* 删除学员教练互评表
	*/
	@ApiOperation("删除学员教练互评表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/studentCoachAppraise',  'admin:studentCoachAppraise:delete')")
	@EventLog(message = "删除学员教练互评表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(studentCoachAppraiseService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除学员教练互评表
	*/
	@ApiOperation("通过主键删除学员教练互评表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除学员教练互评表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return studentCoachAppraiseRepository.deleteById(id);
	}

	/**
	* 导出学员教练互评表
	*/
	@ApiOperation("导出学员教练互评表")
	@PreAuthorize("hasPermission('/admin/studentCoachAppraise',  'admin:studentCoachAppraise:export')")
	@SneakyThrows
	@EventLog(message = "导出学员教练互评表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(StudentCoachAppraisePageQueryParam param, HttpServletResponse response) {
		studentCoachAppraiseRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用学员教练互评表")
	@PreAuthorize("hasPermission('/admin/studentCoachAppraise',  'admin:studentCoachAppraise:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用学员教练互评表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody StudentCoachAppraiseEditParam studentCoachAppraiseEditParam) {
		return studentCoachAppraiseRepository.changeStatus(studentCoachAppraiseEditParam);
	}

}
