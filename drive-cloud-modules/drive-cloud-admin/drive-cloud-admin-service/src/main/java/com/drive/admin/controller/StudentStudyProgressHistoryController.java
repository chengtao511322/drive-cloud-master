package com.drive.admin.controller;

import com.drive.admin.pojo.dto.StudentStudyProgressHistoryEditParam;
import com.drive.admin.pojo.dto.StudentStudyProgressHistoryInstallParam;
import com.drive.admin.pojo.dto.StudentStudyProgressHistoryPageQueryParam;
import com.drive.admin.pojo.entity.StudentStudyProgressHistoryEntity;
import com.drive.admin.repository.StudentStudyProgressHistoryRepository;
import com.drive.admin.service.StudentStudyProgressHistoryService;
import com.drive.admin.service.mapstruct.StudentStudyProgressHistoryMapStruct;
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
 * 学员学车报名单管理
 *
 * @author xiaoguo
 */
@Api(tags = "学员学车报名单管理")
@Slf4j
@RestController
@RequestMapping("/studentStudyProgressHistory")
public class StudentStudyProgressHistoryController extends BaseController<StudentStudyProgressHistoryPageQueryParam, StudentStudyProgressHistoryEntity> {

	// 学员学车报名单 服务
	@Autowired
	private StudentStudyProgressHistoryService studentStudyProgressHistoryService;
	// 学员学车报名单 业务服务
	@Autowired
	private StudentStudyProgressHistoryRepository studentStudyProgressHistoryRepository;
	// 学员学车报名单 DO-DTO转化
	@Autowired
	private StudentStudyProgressHistoryMapStruct studentStudyProgressHistoryMapStruct;

	/**
	* 学员学车报名单 分页列表
	*/
	@ApiOperation("学员学车报名单分页列表")
	//@PreAuthorize("hasPermission('/admin/studentStudyProgressHistory',  'admin:studentStudyProgressHistory:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody StudentStudyProgressHistoryPageQueryParam param) {
		return studentStudyProgressHistoryRepository.pageList(param);
	}
	/**
	* 学员学车报名单 列表
	*/
	@ApiOperation("学员学车报名单列表")
	//@PreAuthorize("hasPermission('/admin/studentStudyProgressHistory',  'admin:studentStudyProgressHistory:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody StudentStudyProgressHistoryPageQueryParam param) {
		return studentStudyProgressHistoryRepository.findList(param);
	}

	/**
	* 获取学员学车报名单
	*/
	@ApiOperation("获取学员学车报名单")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/studentStudyProgressHistory',  'admin:studentStudyProgressHistory:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return studentStudyProgressHistoryRepository.getById(id);
	}

	/**
	 * 条件查询获取学员学车报名单
	 */
	@ApiOperation("条件查询获取学员学车报名单")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/studentStudyProgressHistory',  'admin:studentStudyProgressHistory:query')")
	@GetMapping("/getInfo")
	public ResObject getInfo(@PathVariable StudentStudyProgressHistoryPageQueryParam param) {
		return studentStudyProgressHistoryRepository.getInfo(param);
	}

	/**
	* 新增学员学车报名单
	*/
	@ApiOperation("新增学员学车报名单")
	@ApiImplicitParam(name = "StudentStudyProgressHistoryEditParam ", value = "新增学员学车报名单", dataType = "StudentStudyProgressHistoryEditParam")
	//@PreAuthorize("hasPermission('/admin/studentStudyProgressHistory',  'admin:studentStudyProgressHistory:add')")
	@EventLog(message = "新增学员学车报名单", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody StudentStudyProgressHistoryInstallParam studentStudyProgressHistoryInstallParam) {
		return studentStudyProgressHistoryRepository.save(studentStudyProgressHistoryInstallParam);
	}

	/**
	* 修改学员学车报名单
	*/
	@ApiOperation("修改学员学车报名单")
	@ApiImplicitParam(name = "StudentStudyProgressHistoryEditParam ", value = "修改学员学车报名单", dataType = "StudentStudyProgressHistoryEditParam")
	//@PreAuthorize("hasPermission('/admin/studentStudyProgressHistory',  'admin:studentStudyProgressHistory:edit')")
	@EventLog(message = "修改学员学车报名单", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody StudentStudyProgressHistoryEditParam studentStudyProgressHistoryEditParam) {
		return studentStudyProgressHistoryRepository.update(studentStudyProgressHistoryEditParam);
	}

	/**
	* 删除学员学车报名单
	*/
	@ApiOperation("删除学员学车报名单")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/studentStudyProgressHistory',  'admin:studentStudyProgressHistory:delete')")
	@EventLog(message = "删除学员学车报名单", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(studentStudyProgressHistoryService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除学员学车报名单
	*/
	@ApiOperation("通过主键删除学员学车报名单")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除学员学车报名单", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return studentStudyProgressHistoryRepository.deleteById(id);
	}

	/**
	* 导出学员学车报名单
	*/
	@ApiOperation("导出学员学车报名单")
	//@PreAuthorize("hasPermission('/admin/studentStudyProgressHistory',  'admin:studentStudyProgressHistory:export')")
	@SneakyThrows
	@EventLog(message = "导出学员学车报名单", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody StudentStudyProgressHistoryPageQueryParam param, HttpServletResponse response) {
		studentStudyProgressHistoryRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用学员学车报名单")
	//@PreAuthorize("hasPermission('/admin/studentStudyProgressHistory',  'admin:studentStudyProgressHistory:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用学员学车报名单", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody StudentStudyProgressHistoryEditParam studentStudyProgressHistoryEditParam) {
		return studentStudyProgressHistoryRepository.changeStatus(studentStudyProgressHistoryEditParam);
	}

}
