package com.drive.admin.controller;

import com.drive.admin.pojo.dto.OperatorEditParam;
import com.drive.admin.pojo.dto.OperatorInstallParam;
import com.drive.admin.pojo.dto.OperatorPageQueryParam;
import com.drive.admin.pojo.entity.OperatorEntity;
import com.drive.admin.repository.OperatorRepository;
import com.drive.admin.service.OperatorService;
import com.drive.admin.service.mapstruct.OperatorMapStruct;
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
 * 运营商基础信息管理
 *
 * @author xiaoguo
 */
@Api(tags = "运营商基础信息管理")
@Slf4j
@RestController
@RequestMapping("/operator")
public class OperatorController extends BaseController<OperatorPageQueryParam, OperatorEntity> {

	// 运营商基础信息 服务
	@Autowired
	private OperatorService operatorService;
	// 运营商基础信息 业务服务
	@Autowired
	private OperatorRepository operatorRepository;
	// 运营商基础信息 DO-DTO转化
	@Autowired
	private OperatorMapStruct operatorMapStruct;

	/**
	* 运营商基础信息 分页列表
	*/
	@ApiOperation("运营商基础信息分页列表")
	//@PreAuthorize("hasPermission('/admin/operator',  'admin:operator:query')")
	@GetMapping(value = "/pageList")
	public ResObject pageList(@Valid OperatorPageQueryParam param) {
		return operatorRepository.pageList(param);
	}
	/**
	* 运营商基础信息 列表
	*/
	@ApiOperation("运营商基础信息列表")
	//@PreAuthorize("hasPermission('/admin/operator',  'admin:operator:query')")
	@GetMapping(value = "/findList")
	public ResObject findList(@Valid OperatorPageQueryParam param) {
		return operatorRepository.findList(param);
	}

	/**
	* 获取运营商基础信息
	*/
	@ApiOperation("获取运营商基础信息")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/operator',  'admin:operator:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return operatorRepository.getById(id);
	}

	/**
	 * 条件查询获取运营商基础信息
	 */
	@ApiOperation("条件查询获取运营商基础信息")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/operator',  'admin:operator:query')")
	@GetMapping("/getInfo")
	public ResObject getInfo(@PathVariable OperatorPageQueryParam param) {
		return operatorRepository.getInfo(param);
	}

	/**
	* 新增运营商基础信息
	*/
	@ApiOperation("新增运营商基础信息")
	@ApiImplicitParam(name = "OperatorEditParam ", value = "新增运营商基础信息", dataType = "OperatorEditParam")
	//@PreAuthorize("hasPermission('/admin/operator',  'admin:operator:add')")
	@EventLog(message = "新增运营商基础信息", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody OperatorInstallParam operatorInstallParam) {
		return operatorRepository.save(operatorInstallParam);
	}

	/**
	* 修改运营商基础信息
	*/
	@ApiOperation("修改运营商基础信息")
	@ApiImplicitParam(name = "OperatorEditParam ", value = "修改运营商基础信息", dataType = "OperatorEditParam")
	//@PreAuthorize("hasPermission('/admin/operator',  'admin:operator:edit')")
	@EventLog(message = "修改运营商基础信息", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody OperatorEditParam operatorEditParam) {
		return operatorRepository.update(operatorEditParam);
	}

	/**
	* 删除运营商基础信息
	*/
	@ApiOperation("删除运营商基础信息")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/operator',  'admin:operator:delete')")
	@EventLog(message = "删除运营商基础信息", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(operatorService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除运营商基础信息
	*/
	@ApiOperation("通过主键删除运营商基础信息")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除运营商基础信息", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return operatorRepository.deleteById(id);
	}

	/**
	* 导出运营商基础信息
	*/
	@ApiOperation("导出运营商基础信息")
	//@PreAuthorize("hasPermission('/admin/operator',  'admin:operator:export')")
	@SneakyThrows
	@EventLog(message = "导出运营商基础信息", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(OperatorPageQueryParam param, HttpServletResponse response) {
		operatorRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用运营商基础信息")
	//@PreAuthorize("hasPermission('/admin/operator',  'admin:operator:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用运营商基础信息", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody OperatorEditParam operatorEditParam) {
		return operatorRepository.changeStatus(operatorEditParam);
	}

}
