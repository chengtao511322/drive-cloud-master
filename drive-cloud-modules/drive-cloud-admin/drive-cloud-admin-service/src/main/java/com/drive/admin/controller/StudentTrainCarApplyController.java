package com.drive.admin.controller;

import com.drive.admin.pojo.dto.StudentTrainCarApplyEditParam;
import com.drive.admin.pojo.dto.StudentTrainCarApplyInstallParam;
import com.drive.admin.pojo.dto.StudentTrainCarApplyPageQueryParam;
import com.drive.admin.pojo.entity.StudentTrainCarApplyEntity;
import com.drive.admin.repository.StudentTrainCarApplyRepository;
import com.drive.admin.service.StudentTrainCarApplyService;
import com.drive.admin.service.mapstruct.StudentTrainCarApplyMapStruct;
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
 * 学员学车预约表管理
 *
 * @author xiaoguo
 */
@Api(tags = "学员学车预约表管理")
@Slf4j
@RestController
@RequestMapping("/studentTrainCarApply")
public class StudentTrainCarApplyController extends BaseController<StudentTrainCarApplyPageQueryParam, StudentTrainCarApplyEntity> {

	// 学员学车预约表 服务
	@Autowired
	private StudentTrainCarApplyService studentTrainCarApplyService;
	// 学员学车预约表 业务服务
	@Autowired
	private StudentTrainCarApplyRepository studentTrainCarApplyRepository;
	// 学员学车预约表 DO-DTO转化
	@Autowired
	private StudentTrainCarApplyMapStruct studentTrainCarApplyMapStruct;

	/**
	* 学员学车预约表 分页列表
	*/
	@ApiOperation("学员学车预约表分页列表")
	//@PreAuthorize("hasPermission('/admin/studentTrainCarApply',  'admin:studentTrainCarApply:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody StudentTrainCarApplyPageQueryParam param) {
		return studentTrainCarApplyRepository.pageList(param);
	}
	/**
	* 学员学车预约表 列表
	*/
	@ApiOperation("学员学车预约表列表")
	//@PreAuthorize("hasPermission('/admin/studentTrainCarApply',  'admin:studentTrainCarApply:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody StudentTrainCarApplyPageQueryParam param) {
		return studentTrainCarApplyRepository.findList(param);
	}

	/**
	* 获取学员学车预约表
	*/
	@ApiOperation("获取学员学车预约表")
	@ApiImplicitParam(name = "trainApplyNo", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/studentTrainCarApply',  'admin:studentTrainCarApply:query')")
	@GetMapping("/{trainApplyNo}")
	public ResObject get(@PathVariable String trainApplyNo) {
		return studentTrainCarApplyRepository.getById(trainApplyNo);
	}

	/**
	 * 条件查询获取学员学车预约表
	 */
	@ApiOperation("条件查询获取学员学车预约表")
	@ApiImplicitParam(name = "trainApplyNo", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/studentTrainCarApply',  'admin:studentTrainCarApply:query')")
	@GetMapping("/getInfo")
	public ResObject getInfo(@PathVariable StudentTrainCarApplyPageQueryParam param) {
		return studentTrainCarApplyRepository.getInfo(param);
	}

	/**
	* 新增学员学车预约表
	*/
	@ApiOperation("新增学员学车预约表")
	@ApiImplicitParam(name = "StudentTrainCarApplyEditParam ", value = "新增学员学车预约表", dataType = "StudentTrainCarApplyEditParam")
	//@PreAuthorize("hasPermission('/admin/studentTrainCarApply',  'admin:studentTrainCarApply:add')")
	@EventLog(message = "新增学员学车预约表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody StudentTrainCarApplyInstallParam studentTrainCarApplyInstallParam) {
		return studentTrainCarApplyRepository.save(studentTrainCarApplyInstallParam);
	}

	/**
	* 修改学员学车预约表
	*/
	@ApiOperation("修改学员学车预约表")
	@ApiImplicitParam(name = "StudentTrainCarApplyEditParam ", value = "修改学员学车预约表", dataType = "StudentTrainCarApplyEditParam")
	//@PreAuthorize("hasPermission('/admin/studentTrainCarApply',  'admin:studentTrainCarApply:edit')")
	@EventLog(message = "修改学员学车预约表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody StudentTrainCarApplyEditParam studentTrainCarApplyEditParam) {
		return studentTrainCarApplyRepository.update(studentTrainCarApplyEditParam);
	}

	/**
	* 删除学员学车预约表
	*/
	@ApiOperation("删除学员学车预约表")
	@ApiImplicitParam(name = "trainApplyNo", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/studentTrainCarApply',  'admin:studentTrainCarApply:delete')")
	@EventLog(message = "删除学员学车预约表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{trainApplyNos}")
	public ResObject delete(@PathVariable Long[] trainApplyNos) {
		return R.toRes(studentTrainCarApplyService.removeByIds(Arrays.asList(trainApplyNos)));
	}

	/**
	* 通过主键删除学员学车预约表
	*/
	@ApiOperation("通过主键删除学员学车预约表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除学员学车预约表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return studentTrainCarApplyRepository.deleteById(id);
	}

	/**
	* 导出学员学车预约表
	*/
	@ApiOperation("导出学员学车预约表")
	//@PreAuthorize("hasPermission('/admin/studentTrainCarApply',  'admin:studentTrainCarApply:export')")
	@SneakyThrows
	@EventLog(message = "导出学员学车预约表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(StudentTrainCarApplyPageQueryParam param, HttpServletResponse response) {
		studentTrainCarApplyRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用学员学车预约表")
	//@PreAuthorize("hasPermission('/admin/studentTrainCarApply',  'admin:studentTrainCarApply:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用学员学车预约表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody StudentTrainCarApplyEditParam studentTrainCarApplyEditParam) {
		return studentTrainCarApplyRepository.changeStatus(studentTrainCarApplyEditParam);
	}

}
