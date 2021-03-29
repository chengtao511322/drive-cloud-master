package com.drive.admin.controller;

import com.drive.admin.pojo.dto.DriveSchoolEditParam;
import com.drive.admin.pojo.dto.DriveSchoolPageQueryParam;
import com.drive.admin.pojo.entity.DriveSchoolEntity;
import com.drive.admin.repository.DriveSchoolRepository;
import com.drive.admin.service.DriveSchoolService;
import com.drive.admin.service.mapstruct.DriveSchoolMapStruct;
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
 * 平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。管理
 *
 * @author xiaoguo
 */
@Api(tags = "平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。管理")
@Slf4j
@RestController
@RequestMapping("/driveSchool")
public class DriveSchoolController extends BaseController<DriveSchoolPageQueryParam, DriveSchoolEntity> {

	// 平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。 服务
	@Autowired
	private DriveSchoolService driveSchoolService;
	// 平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。 业务服务
	@Autowired
	private DriveSchoolRepository driveSchoolRepository;
	// 平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。 DO-DTO转化
	@Autowired
	private DriveSchoolMapStruct driveSchoolMapStruct;

	/**
	* 平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。 分页列表
	*/
	@ApiOperation("平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。分页列表")
	@PreAuthorize("hasPermission('/admin/driveSchool',  'admin:driveSchool:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid DriveSchoolPageQueryParam param) {
		return driveSchoolRepository.pageList(param);
	}
	/**
	* 平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。 列表
	*/
	@ApiOperation("平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。列表")
	@PreAuthorize("hasPermission('/admin/driveSchool',  'admin:driveSchool:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid DriveSchoolPageQueryParam param) {
		return driveSchoolRepository.findList(param);
	}

	/**
	* 获取平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。
	*/
	@ApiOperation("获取平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/driveSchool',  'admin:driveSchool:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return driveSchoolRepository.getById(id);
	}

	/**
	* 新增平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。
	*/
	@ApiOperation("新增平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。")
	@ApiImplicitParam(name = "DriveSchoolEditParam ", value = "新增平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。", dataType = "DriveSchoolEditParam")
	@PreAuthorize("hasPermission('/admin/driveSchool',  'admin:driveSchool:add')")
	@EventLog(message = "新增平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody DriveSchoolEditParam driveSchoolEditParam) {
		return driveSchoolRepository.save(driveSchoolEditParam);
	}

	/**
	* 修改平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。
	*/
	@ApiOperation("修改平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。")
	@ApiImplicitParam(name = "DriveSchoolEditParam ", value = "修改平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。", dataType = "DriveSchoolEditParam")
	@PreAuthorize("hasPermission('/admin/driveSchool',  'admin:driveSchool:edit')")
	@EventLog(message = "修改平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody DriveSchoolEditParam driveSchoolEditParam) {
		return driveSchoolRepository.update(driveSchoolEditParam);
	}

	/**
	* 删除平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。
	*/
	@ApiOperation("删除平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/driveSchool',  'admin:driveSchool:delete')")
	@EventLog(message = "删除平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(driveSchoolService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。
	*/
	@ApiOperation("通过主键删除平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return driveSchoolRepository.deleteById(id);
	}

	/**
	* 导出平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。
	*/
	@ApiOperation("导出平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。")
	@PreAuthorize("hasPermission('/admin/driveSchool',  'admin:driveSchool:export')")
	@SneakyThrows
	@EventLog(message = "导出平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(DriveSchoolPageQueryParam param, HttpServletResponse response) {
		driveSchoolRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。")
	@PreAuthorize("hasPermission('/admin/driveSchool',  'admin:driveSchool:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody DriveSchoolEditParam driveSchoolEditParam) {
		return driveSchoolRepository.changeStatus(driveSchoolEditParam);
	}

}
