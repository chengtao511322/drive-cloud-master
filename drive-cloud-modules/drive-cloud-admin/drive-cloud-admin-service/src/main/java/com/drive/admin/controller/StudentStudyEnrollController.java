package com.drive.admin.controller;

import cn.hutool.core.util.StrUtil;
import com.drive.admin.enums.EnrollStatusEnum;
import com.drive.admin.enums.StudyEnrollEnum;
import com.drive.admin.pojo.dto.CompleteStudyEnrollParam;
import com.drive.admin.pojo.dto.StudentStudyEnrollEditParam;
import com.drive.admin.pojo.dto.StudentStudyEnrollPageQueryParam;
import com.drive.admin.pojo.entity.StudentStudyEnrollEntity;
import com.drive.admin.repository.StudentStudyEnrollRepository;
import com.drive.admin.service.StudentStudyEnrollService;
import com.drive.admin.service.mapstruct.StudentStudyEnrollMapStruct;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
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


	@ApiOperation("学员学车报名单分页列表")
	@PreAuthorize("hasPermission('/admin/studentStudyEnroll',  'admin:studentStudyEnroll:query')")
	@PostMapping(value = "/studyEnrollPageList")
	public ResObject studyEnrollPageList(@Valid @RequestBody StudentStudyEnrollPageQueryParam param) {
		return studentStudyEnrollRepository.studyEnrollPageList(param);
	}
	@ApiOperation("统计学员学车报名单分页列表")
	@PreAuthorize("hasPermission('/admin/studentStudyEnroll',  'admin:studentStudyEnroll:query')")
	@PostMapping(value = "/statisticsStudentDataPageList")
	public ResObject statisticsStudentDataPageList(@Valid @RequestBody StudentStudyEnrollPageQueryParam param) {
		return studentStudyEnrollRepository.statisticsStudentDataPageList(param);
	}
	@ApiOperation("统计学员学车报名单分页列表")
	@PreAuthorize("hasPermission('/admin/studentStudyEnroll',  'admin:studentStudyEnroll:query')")
	@PostMapping(value = "/drivingStudentDataPageList")
	public ResObject drivingStudentDataPageList(@Valid @RequestBody StudentStudyEnrollPageQueryParam param) {
		return studentStudyEnrollRepository.drivingStudentDataPageList(param);
	}


	@ApiOperation("待支付转化分页列表")
	@PreAuthorize("hasPermission('/admin/studentStudyEnroll',  'admin:studentStudyEnroll:query')")
	@PostMapping(value = "/stayPayChangePageList")
	public ResObject stayPayChangePageList(@Valid @RequestBody StudentStudyEnrollPageQueryParam param) {
		// 状态值
		String[] arr = new String[]
				{
						EnrollStatusEnum.PAY_WAIT_PUT.getCode(),
						EnrollStatusEnum.ENROLL_SUCCESS.getCode(),
						EnrollStatusEnum.PUT_WAIT_AUDIT.getCode(),
						EnrollStatusEnum.PASSWORD_SUBMIT_WAIT_AUDIT.getCode()
				};
		param.setOrderStatusArr(arr);
		return studentStudyEnrollRepository.stayPayChangePageList(param);
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

	@ApiOperation("获取学员学车报名单")
	@ApiImplicitParam(name = "studyEnrollNo", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/studentStudyEnroll',  'admin:studentStudyEnroll:query')")
	@GetMapping("/getByStudentId/{studentId}")
	public ResObject getByStudentId(@PathVariable String studentId) {
		if (StrUtil.isEmpty(studentId)){
			return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
		}
		String[] arr = {
				StudyEnrollEnum.ENROLL_STATUS_PAY_SUCCESS.getCode(),
				StudyEnrollEnum.ENROLL_STATUS_PREPARE_STAY_EXAMINE.getCode(),
				StudyEnrollEnum.ENROLL_STATUS_PASSWORD_EXAMINE.getCode(),
				StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode(),
		};
		StudentStudyEnrollPageQueryParam param = new StudentStudyEnrollPageQueryParam();
		param.setStudentId(studentId);
		param.setOrderStatusArr(arr);
		// param.setEnrollStatus(StudyEnrollEnum.ENROLL_STATUS_ENROLL_COMPLETE.getCode());
		return studentStudyEnrollRepository.getInfo(param);
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
	@ApiOperation("完善学员学车报名单")
	@ApiImplicitParam(name = "StudentStudyEnrollEditParam ", value = "新增学员学车报名单", dataType = "StudentStudyEnrollEditParam")
	@PreAuthorize("hasPermission('/admin/studentStudyEnroll',  'admin:studentStudyEnroll:add')")
	@EventLog(message = "新增学员学车报名单", businessType = EventLogEnum.CREATE)
	@PutMapping("/completeStudyEnroll")
	public ResObject completeStudyEnroll(@Valid @RequestBody CompleteStudyEnrollParam completeStudyEnrollParam) {
		return studentStudyEnrollRepository.completeStudyEnroll(completeStudyEnrollParam);
	}
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
