package com.drive.admin.controller;

import com.drive.admin.pojo.dto.SchoolUserEditParam;
import com.drive.admin.pojo.dto.SchoolUserInstallParam;
import com.drive.admin.pojo.dto.SchoolUserPageQueryParam;
import com.drive.admin.pojo.entity.SchoolUserEntity;
import com.drive.admin.repository.SchoolUserRepository;
import com.drive.admin.service.SchoolUserService;
import com.drive.admin.service.mapstruct.SchoolUserMapStruct;
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
 * 合作驾校用户管理
 *
 * @author xiaoguo
 */
@Api(tags = "合作驾校用户管理")
@Slf4j
@RestController
@RequestMapping("/schoolUser")
public class SchoolUserController extends BaseController<SchoolUserPageQueryParam, SchoolUserEntity> {

	// 合作驾校用户 服务
	@Autowired
	private SchoolUserService schoolUserService;
	// 合作驾校用户 业务服务
	@Autowired
	private SchoolUserRepository schoolUserRepository;
	// 合作驾校用户 DO-DTO转化
	@Autowired
	private SchoolUserMapStruct schoolUserMapStruct;

	/**
	* 合作驾校用户 分页列表
	*/
	@ApiOperation("合作驾校用户分页列表")
	//@PreAuthorize("hasPermission('/admin/schoolUser',  'admin:schoolUser:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody SchoolUserPageQueryParam param) {
		return schoolUserRepository.pageList(param);
	}
	/**
	* 合作驾校用户 列表
	*/
	@ApiOperation("合作驾校用户列表")
	//@PreAuthorize("hasPermission('/admin/schoolUser',  'admin:schoolUser:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody SchoolUserPageQueryParam param) {
		return schoolUserRepository.findList(param);
	}

	/**
	* 获取合作驾校用户
	*/
	@ApiOperation("获取合作驾校用户")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/schoolUser',  'admin:schoolUser:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return schoolUserRepository.getById(id);
	}

	/**
	 * 条件查询获取合作驾校用户
	 */
	@ApiOperation("条件查询获取合作驾校用户")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/schoolUser',  'admin:schoolUser:query')")
	@PostMapping("/getInfo")
	public ResObject getInfo(@PathVariable @RequestBody SchoolUserPageQueryParam param) {
		return schoolUserRepository.getInfo(param);
	}

	/**
	* 新增合作驾校用户
	*/
	@ApiOperation("新增合作驾校用户")
	@ApiImplicitParam(name = "SchoolUserEditParam ", value = "新增合作驾校用户", dataType = "SchoolUserEditParam")
	//@PreAuthorize("hasPermission('/admin/schoolUser',  'admin:schoolUser:add')")
	@EventLog(message = "新增合作驾校用户", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody SchoolUserInstallParam schoolUserInstallParam) {
		return schoolUserRepository.save(schoolUserInstallParam);
	}

	/**
	* 修改合作驾校用户
	*/
	@ApiOperation("修改合作驾校用户")
	@ApiImplicitParam(name = "SchoolUserEditParam ", value = "修改合作驾校用户", dataType = "SchoolUserEditParam")
	//@PreAuthorize("hasPermission('/admin/schoolUser',  'admin:schoolUser:edit')")
	@EventLog(message = "修改合作驾校用户", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody SchoolUserEditParam schoolUserEditParam) {
		return schoolUserRepository.update(schoolUserEditParam);
	}

	/**
	* 删除合作驾校用户
	*/
	@ApiOperation("删除合作驾校用户")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/schoolUser',  'admin:schoolUser:delete')")
	@EventLog(message = "删除合作驾校用户", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(schoolUserService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除合作驾校用户
	*/
	@ApiOperation("通过主键删除合作驾校用户")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除合作驾校用户", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return schoolUserRepository.deleteById(id);
	}

	/**
	* 导出合作驾校用户
	*/
	@ApiOperation("导出合作驾校用户")
	//@PreAuthorize("hasPermission('/admin/schoolUser',  'admin:schoolUser:export')")
	@SneakyThrows
	@EventLog(message = "导出合作驾校用户", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody SchoolUserPageQueryParam param, HttpServletResponse response) {
		schoolUserRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用合作驾校用户")
	//@PreAuthorize("hasPermission('/admin/schoolUser',  'admin:schoolUser:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用合作驾校用户", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody SchoolUserEditParam schoolUserEditParam) {
		return schoolUserRepository.changeStatus(schoolUserEditParam);
	}

}
