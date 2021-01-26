package com.drive.member.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.data.utils.ExcelUtils;
import com.drive.common.log.annotation.EventLog;
import com.drive.member.pojo.dto.StudentStudyEnrollEditParam;
import com.drive.member.pojo.dto.StudentStudyEnrollPageQueryParam;
import com.drive.member.pojo.entity.StudentStudyEnrollEntity;
import com.drive.member.pojo.vo.StudentStudyEnrollVo;
import com.drive.member.service.StudentStudyEnrollService;
import com.drive.member.service.mapstruct.StudentStudyEnrollMapStruct;
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
import java.util.List;


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
	private StudentStudyEnrollMapStruct studentStudyEnrollMapStruct;

	/**
	* 学员学车报名单 分页列表
	*/
	@ApiOperation("学员学车报名单分页列表")
	@PreAuthorize("hasPermission('/member/studentStudyEnroll',  'member:studentStudyEnroll:query')")
	@GetMapping(value = "/pageList")
	public ResObject pageList(@Valid StudentStudyEnrollPageQueryParam param) {

		Page<StudentStudyEnrollEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
		IPage<StudentStudyEnrollEntity> pageList = studentStudyEnrollService.page(page, this.getQueryWrapper(studentStudyEnrollMapStruct, param));
		Page<StudentStudyEnrollVo> studentStudyEnrollVoPage = studentStudyEnrollMapStruct.toVoList(pageList);
		return R.success(studentStudyEnrollVoPage);
	}

	/**
	* 获取学员学车报名单
	*/
	@ApiOperation("获取学员学车报名单")
	@ApiImplicitParam(name = "studyEnrollNo", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/member/studentStudyEnroll',  'member:studentStudyEnroll:query')")
	@GetMapping("/{studyEnrollNo}")
	public ResObject get(@PathVariable Long studyEnrollNo) {
		StudentStudyEnrollEntity studentStudyEnroll = studentStudyEnrollService.getById(studyEnrollNo);
		return R.success(studentStudyEnrollMapStruct.toVo(studentStudyEnroll));
	}

	/**
	* 新增学员学车报名单
	*/
	@ApiOperation("新增学员学车报名单")
	@ApiImplicitParam(name = "StudentStudyEnrollEditParam ", value = "新增学员学车报名单", dataType = "StudentStudyEnrollEditParam")
	@PreAuthorize("hasPermission('/member/studentStudyEnroll',  'member:studentStudyEnroll:add')")
	@EventLog(message = "新增学员学车报名单", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody StudentStudyEnrollEditParam studentStudyEnrollEditParam) {
		StudentStudyEnrollEntity studentStudyEnroll = studentStudyEnrollMapStruct.toEntity(studentStudyEnrollEditParam);
		return R.toRes(studentStudyEnrollService.save(studentStudyEnroll));
	}

	/**
	* 修改学员学车报名单
	*/
	@ApiOperation("修改学员学车报名单")
	@ApiImplicitParam(name = "StudentStudyEnrollEditParam ", value = "修改学员学车报名单", dataType = "StudentStudyEnrollEditParam")
	@PreAuthorize("hasPermission('/member/studentStudyEnroll',  'member:studentStudyEnroll:edit')")
	@EventLog(message = "修改学员学车报名单", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody StudentStudyEnrollEditParam studentStudyEnrollEditParam) {
		StudentStudyEnrollEntity studentStudyEnroll = studentStudyEnrollMapStruct.toEntity(studentStudyEnrollEditParam);
		return R.toRes(studentStudyEnrollService.updateById(studentStudyEnroll));
	}

	/**
	* 删除学员学车报名单
	*/
	@ApiOperation("删除学员学车报名单")
	@ApiImplicitParam(name = "studyEnrollNo", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/member/studentStudyEnroll',  'member:studentStudyEnroll:delete')")
	@EventLog(message = "删除学员学车报名单", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{studyEnrollNos}")
	public ResObject delete(@PathVariable Long[] studyEnrollNos) {
		return R.toRes(studentStudyEnrollService.removeByIds(Arrays.asList(studyEnrollNos)));
	}

	/**
	* 导出学员学车报名单
	*/
	@ApiOperation("导出学员学车报名单")
	@PreAuthorize("hasPermission('/member/studentStudyEnroll',  'member:studentStudyEnroll:export')")
	@SneakyThrows
	@EventLog(message = "导出学员学车报名单", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(StudentStudyEnrollPageQueryParam param, HttpServletResponse response) {
		List<StudentStudyEnrollEntity> list = studentStudyEnrollService.list(this.getQueryWrapper(studentStudyEnrollMapStruct, param));
		List<StudentStudyEnrollVo> studentStudyEnrollVoList = studentStudyEnrollMapStruct.toVoList(list);
		ExcelUtils.exportExcel(studentStudyEnrollVoList, StudentStudyEnrollVo.class, "学员学车报名单", new ExportParams(), response);
	}

}
