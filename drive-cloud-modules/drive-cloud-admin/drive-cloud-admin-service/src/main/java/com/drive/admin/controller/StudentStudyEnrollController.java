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
import com.drive.admin.service.StudentStudyEnrollService;
import com.drive.admin.repository.StudentStudyEnrollRepository;


/**
 * 学员学车报名单管理
 *
 * @author xiaoguo
 */
@Api(tags = "学员学车报名单管理")
@Slf4j
@RestController
@RequestMapping("/studentStudyEnroll")
public class StudentStudyEnrollController extends BaseController<StudentStudyEnrollPageQueryParam, StudentStudyEnrollEntity> {

	@Autowired
	private StudentStudyEnrollService studentStudyEnrollService;
	@Autowired
	private StudentStudyEnrollRepository studentStudyEnrollRepository;
	@Autowired
	private StudentStudyEnrollMapStruct studentStudyEnrollMapStruct;

	/**
	* 学员学车报名单 分页列表
	*/
	@ApiOperation("学员学车报名单分页列表")
	@PreAuthorize("hasPermission('/admin/studentStudyEnroll',  'admin:studentStudyEnroll:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid StudentStudyEnrollPageQueryParam param) {
		return studentStudyEnrollRepository.pageList(param);
	}
	/**
	* 学员学车报名单 列表
	*/
	@ApiOperation("学员学车报名单列表")
	@PreAuthorize("hasPermission('/admin/studentStudyEnroll',  'admin:studentStudyEnroll:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid StudentStudyEnrollPageQueryParam param) {
		return studentStudyEnrollRepository.findList(param);
	}

	/**
	* 获取学员学车报名单
	*/
	@ApiOperation("获取学员学车报名单")
	@ApiImplicitParam(name = "studyEnrollNo", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/studentStudyEnroll',  'admin:studentStudyEnroll:query')")
	@GetMapping("/{studyEnrollNo}")
	public ResObject get(@PathVariable String studyEnrollNo) {
		return studentStudyEnrollRepository.getById(studyEnrollNo);
	}
	@ApiOperation("获取学员学车报名单（条件查询一条数据）")
	@ApiImplicitParam(name = "studyEnrollNo", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/studentStudyEnroll',  'admin:studentStudyEnroll:query')")
	@GetMapping("/getStudentStudyEnrollInfo")
	public ResObject getStudentStudyEnrollInfo(@Valid StudentStudyEnrollPageQueryParam studentStudyEnrollPageQueryParam) {
		return studentStudyEnrollRepository.getStudentStudyEnrollInfo(studentStudyEnrollPageQueryParam);
	}

	/**
	* 新增学员学车报名单
	*/
	@ApiOperation("新增学员学车报名单")
	@ApiImplicitParam(name = "StudentStudyEnrollEditParam ", value = "新增学员学车报名单", dataType = "StudentStudyEnrollEditParam")
	@PreAuthorize("hasPermission('/admin/studentStudyEnroll',  'admin:studentStudyEnroll:add')")
	@EventLog(message = "新增学员学车报名单", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody StudentStudyEnrollEditParam studentStudyEnrollEditParam) {
		return studentStudyEnrollRepository.save(studentStudyEnrollEditParam);
	}

	/**
	* 修改学员学车报名单
	*/
	@ApiOperation("修改学员学车报名单")
	@ApiImplicitParam(name = "StudentStudyEnrollEditParam ", value = "修改学员学车报名单", dataType = "StudentStudyEnrollEditParam")
	@PreAuthorize("hasPermission('/admin/studentStudyEnroll',  'admin:studentStudyEnroll:edit')")
	@EventLog(message = "修改学员学车报名单", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody StudentStudyEnrollEditParam studentStudyEnrollEditParam) {
		return studentStudyEnrollRepository.update(studentStudyEnrollEditParam);
	}

	/**
	* 删除学员学车报名单
	*/
	@ApiOperation("删除学员学车报名单")
	@ApiImplicitParam(name = "studyEnrollNo", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/studentStudyEnroll',  'admin:studentStudyEnroll:delete')")
	@EventLog(message = "删除学员学车报名单", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{studyEnrollNos}")
	public ResObject delete(@PathVariable Long[] studyEnrollNos) {
		return R.toRes(studentStudyEnrollService.removeByIds(Arrays.asList(studyEnrollNos)));
	}

	/**
	* 通过主键删除学员学车报名单
	*/
	@ApiOperation("通过主键删除学员学车报名单")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除学员学车报名单", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return studentStudyEnrollRepository.deleteById(id);
	}

	/**
	* 导出学员学车报名单
	*/
	@ApiOperation("导出学员学车报名单")
	@PreAuthorize("hasPermission('/admin/studentStudyEnroll',  'admin:studentStudyEnroll:export')")
	@SneakyThrows
	@EventLog(message = "导出学员学车报名单", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(StudentStudyEnrollPageQueryParam param, HttpServletResponse response) {
		studentStudyEnrollRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用学员学车报名单")
	@PreAuthorize("hasPermission('/admin/studentStudyEnroll',  'admin:studentStudyEnroll:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用学员学车报名单", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody StudentStudyEnrollEditParam studentStudyEnrollEditParam) {
		return studentStudyEnrollRepository.changeStatus(studentStudyEnrollEditParam);
	}

}
