package com.drive.basics.controller;

import com.drive.basics.pojo.dto.OperatorAreaEditParam;
import com.drive.basics.pojo.dto.OperatorAreaInstallParam;
import com.drive.basics.pojo.dto.OperatorAreaPageQueryParam;
import com.drive.basics.pojo.entity.OperatorAreaEntity;
import com.drive.basics.repository.OperatorAreaRepository;
import com.drive.basics.service.OperatorAreaService;
import com.drive.basics.service.mapstruct.OperatorAreaMapStruct;
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
 * 运营商代理区域管理
 *
 * @author xiaoguo
 */
@Api(tags = "运营商代理区域管理")
@Slf4j
@RestController
@RequestMapping("/operatorArea")
public class OperatorAreaController extends BaseController<OperatorAreaPageQueryParam, OperatorAreaEntity> {

	// 运营商代理区域 服务
	@Autowired
	private OperatorAreaService operatorAreaService;
	// 运营商代理区域 业务服务
	@Autowired
	private OperatorAreaRepository operatorAreaRepository;
	// 运营商代理区域 DO-DTO转化
	@Autowired
	private OperatorAreaMapStruct operatorAreaMapStruct;

	/**
	* 运营商代理区域 分页列表
	*/
	@ApiOperation("运营商代理区域分页列表")
	@PreAuthorize("hasPermission('/basics/operatorArea',  'basics:operatorArea:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody OperatorAreaPageQueryParam param) {
		return operatorAreaRepository.pageList(param);
	}
	/**
	* 运营商代理区域 列表
	*/
	@ApiOperation("运营商代理区域列表")
	@PreAuthorize("hasPermission('/basics/operatorArea',  'basics:operatorArea:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody OperatorAreaPageQueryParam param) {
		return operatorAreaRepository.findList(param);
	}

	/**
	* 获取运营商代理区域
	*/
	@ApiOperation("获取运营商代理区域")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/basics/operatorArea',  'basics:operatorArea:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return operatorAreaRepository.getById(id);
	}

	/**
	 * 条件查询获取运营商代理区域
	 */
	@ApiOperation("条件查询获取运营商代理区域")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/basics/operatorArea',  'basics:operatorArea:query')")
	@PostMapping("/getInfo")
	public ResObject getInfo(@RequestBody OperatorAreaPageQueryParam param) {
		return operatorAreaRepository.getInfo(param);
	}

	/**
	* 新增运营商代理区域
	*/
	@ApiOperation("新增运营商代理区域")
	@ApiImplicitParam(name = "OperatorAreaEditParam ", value = "新增运营商代理区域", dataType = "OperatorAreaEditParam")
	@PreAuthorize("hasPermission('/basics/operatorArea',  'basics:operatorArea:add')")
	@EventLog(message = "新增运营商代理区域", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody OperatorAreaInstallParam operatorAreaInstallParam) {
		return operatorAreaRepository.save(operatorAreaInstallParam);
	}

	/**
	* 修改运营商代理区域
	*/
	@ApiOperation("修改运营商代理区域")
	@ApiImplicitParam(name = "OperatorAreaEditParam ", value = "修改运营商代理区域", dataType = "OperatorAreaEditParam")
	@PreAuthorize("hasPermission('/basics/operatorArea',  'basics:operatorArea:edit')")
	@EventLog(message = "修改运营商代理区域", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody OperatorAreaEditParam operatorAreaEditParam) {
		return operatorAreaRepository.update(operatorAreaEditParam);
	}

	/**
	* 删除运营商代理区域
	*/
	@ApiOperation("删除运营商代理区域")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/basics/operatorArea',  'basics:operatorArea:delete')")
	@EventLog(message = "删除运营商代理区域", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(operatorAreaService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除运营商代理区域
	*/
	@ApiOperation("通过主键删除运营商代理区域")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除运营商代理区域", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return operatorAreaRepository.deleteById(id);
	}

	/**
	* 导出运营商代理区域
	*/
	@ApiOperation("导出运营商代理区域")
	@PreAuthorize("hasPermission('/basics/operatorArea',  'basics:operatorArea:export')")
	@SneakyThrows
	@EventLog(message = "导出运营商代理区域", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody OperatorAreaPageQueryParam param, HttpServletResponse response) {
		operatorAreaRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用运营商代理区域")
	@PreAuthorize("hasPermission('/basics/operatorArea',  'basics:operatorArea:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用运营商代理区域", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody OperatorAreaEditParam operatorAreaEditParam) {
		return operatorAreaRepository.changeStatus(operatorAreaEditParam);
	}

}
