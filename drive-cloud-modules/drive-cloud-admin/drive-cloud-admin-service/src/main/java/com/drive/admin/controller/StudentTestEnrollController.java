package com.drive.admin.controller;

import com.drive.admin.pojo.dto.StudentTestEnrollEditParam;
import com.drive.admin.pojo.dto.StudentTestEnrollPageQueryParam;
import com.drive.admin.pojo.entity.StudentTestEnrollEntity;
import com.drive.admin.repository.StudentTestEnrollRepository;
import com.drive.admin.service.StudentTestEnrollService;
import com.drive.admin.service.mapstruct.StudentTestEnrollMapStruct;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;


/**
 * 学员考试报名表管理
 *
 * @author xiaoguo
 */
@Api(tags = "学员考试报名表管理")
@Slf4j
@RestController
@RequestMapping("/studentTestEnroll")
public class StudentTestEnrollController extends BaseController<StudentTestEnrollPageQueryParam, StudentTestEnrollEntity> {

	// 学员考试报名表 服务
	@Autowired
	private StudentTestEnrollService studentTestEnrollService;
	// 学员考试报名表 业务服务
	@Autowired
	private StudentTestEnrollRepository studentTestEnrollRepository;
	// 学员考试报名表 DO-DTO转化
	@Autowired
	private StudentTestEnrollMapStruct studentTestEnrollMapStruct;

	/**
	* 学员考试报名表 分页列表
	*/
	@ApiOperation("学员考试报名表分页列表")
	@PreAuthorize("hasPermission('/admin/studentTestEnroll',  'admin:studentTestEnroll:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid StudentTestEnrollPageQueryParam param) {
		return studentTestEnrollRepository.pageList(param);
	}
	/**
	* 学员考试报名表 列表
	*/
	@ApiOperation("学员考试报名表列表")
	@PreAuthorize("hasPermission('/admin/studentTestEnroll',  'admin:studentTestEnroll:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid StudentTestEnrollPageQueryParam param) {
		return studentTestEnrollRepository.findList(param);
	}

	/**
	* 获取学员考试报名表
	*/
	@ApiOperation("获取学员考试报名表")
	@ApiImplicitParam(name = "testEnrollNo", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/studentTestEnroll',  'admin:studentTestEnroll:query')")
	@GetMapping("/{testEnrollNo}")
	public ResObject get(@PathVariable String testEnrollNo) {
		return studentTestEnrollRepository.getById(testEnrollNo);
	}

	/**
	* 新增学员考试报名表
	*/
	@ApiOperation("新增学员考试报名表")
	@ApiImplicitParam(name = "StudentTestEnrollEditParam ", value = "新增学员考试报名表", dataType = "StudentTestEnrollEditParam")
	@PreAuthorize("hasPermission('/admin/studentTestEnroll',  'admin:studentTestEnroll:add')")
	@EventLog(message = "新增学员考试报名表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody StudentTestEnrollEditParam studentTestEnrollEditParam) {
		return studentTestEnrollRepository.save(studentTestEnrollEditParam);
	}

	/**
	* 修改学员考试报名表
	*/
	@ApiOperation("修改学员考试报名表")
	@ApiImplicitParam(name = "StudentTestEnrollEditParam ", value = "修改学员考试报名表", dataType = "StudentTestEnrollEditParam")
	@PreAuthorize("hasPermission('/admin/studentTestEnroll',  'admin:studentTestEnroll:edit')")
	@EventLog(message = "修改学员考试报名表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody StudentTestEnrollEditParam studentTestEnrollEditParam) {
		return studentTestEnrollRepository.update(studentTestEnrollEditParam);
	}

	/**
	* 删除学员考试报名表
	*/
	@ApiOperation("删除学员考试报名表")
	@ApiImplicitParam(name = "testEnrollNo", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/studentTestEnroll',  'admin:studentTestEnroll:delete')")
	@EventLog(message = "删除学员考试报名表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{testEnrollNos}")
	public ResObject delete(@PathVariable Long[] testEnrollNos) {
		return R.toRes(studentTestEnrollService.removeByIds(Arrays.asList(testEnrollNos)));
	}

	/**
	* 通过主键删除学员考试报名表
	*/
	@ApiOperation("通过主键删除学员考试报名表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除学员考试报名表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return studentTestEnrollRepository.deleteById(id);
	}

	/**
	* 导出学员考试报名表
	*/
	@ApiOperation("导出学员考试报名表")
	@PreAuthorize("hasPermission('/admin/studentTestEnroll',  'admin:studentTestEnroll:export')")
	@SneakyThrows
	@EventLog(message = "导出学员考试报名表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(StudentTestEnrollPageQueryParam param, HttpServletResponse response) {
		studentTestEnrollRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用学员考试报名表")
	@PreAuthorize("hasPermission('/admin/studentTestEnroll',  'admin:studentTestEnroll:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用学员考试报名表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody StudentTestEnrollEditParam studentTestEnrollEditParam) {
		return studentTestEnrollRepository.changeStatus(studentTestEnrollEditParam);
	}

}
