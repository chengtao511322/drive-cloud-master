package com.drive.basics.controller;

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
import com.drive.basics.pojo.entity.*;
import com.drive.basics.pojo.vo.*;
import com.drive.basics.pojo.dto.*;
import com.drive.basics.service.mapstruct.*;
import com.drive.basics.service.DriveFlowService;
import com.drive.basics.repository.DriveFlowRepository;


/**
 * 流程信息管理管理
 *
 * @author xiaoguo
 */
@Api(tags = "流程信息管理管理")
@Slf4j
@RestController
@RequestMapping("/driveFlow")
public class DriveFlowController extends BaseController<DriveFlowPageQueryParam, DriveFlowEntity> {

	// 流程信息管理 服务
	@Autowired
	private DriveFlowService driveFlowService;
	// 流程信息管理 业务服务
	@Autowired
	private DriveFlowRepository driveFlowRepository;
	// 流程信息管理 DO-DTO转化
	@Autowired
	private DriveFlowMapStruct driveFlowMapStruct;

	/**
	* 流程信息管理 分页列表
	*/
	@ApiOperation("流程信息管理分页列表")
	@PreAuthorize("hasPermission('/basics/driveFlow',  'basics:driveFlow:query')")
	@GetMapping(value = "/pageList")
	public ResObject pageList(@Valid DriveFlowPageQueryParam param) {
		return driveFlowRepository.pageList(param);
	}
	/**
	* 流程信息管理 列表
	*/
	@ApiOperation("流程信息管理列表")
	@PreAuthorize("hasPermission('/basics/driveFlow',  'basics:driveFlow:query')")
	@GetMapping(value = "/findList")
	public ResObject findList(@Valid DriveFlowPageQueryParam param) {
		return driveFlowRepository.findList(param);
	}

	/**
	* 获取流程信息管理
	*/
	@ApiOperation("获取流程信息管理")
	@ApiImplicitParam(name = "${pkColumn.propertyName}", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/basics/driveFlow',  'basics:driveFlow:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return driveFlowRepository.getById(id);
	}

	/**
	 * 条件查询获取流程信息管理
	 */
	@ApiOperation("条件查询获取流程信息管理")
	@ApiImplicitParam(name = "${pkColumn.propertyName}", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/basics/driveFlow',  'basics:driveFlow:query')")
	@GetMapping("/getInfo")
	public ResObject getInfo(@PathVariable DriveFlowPageQueryParam param) {
		return driveFlowRepository.getInfo(param);
	}

	/**
	* 新增流程信息管理
	*/
	@ApiOperation("新增流程信息管理")
	@ApiImplicitParam(name = "DriveFlowEditParam ", value = "新增流程信息管理", dataType = "DriveFlowEditParam")
	@PreAuthorize("hasPermission('/basics/driveFlow',  'basics:driveFlow:add')")
	@EventLog(message = "新增流程信息管理", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody DriveFlowInstallParam driveFlowInstallParam) {
		return driveFlowRepository.save(driveFlowInstallParam);
	}

	/**
	* 修改流程信息管理
	*/
	@ApiOperation("修改流程信息管理")
	@ApiImplicitParam(name = "DriveFlowEditParam ", value = "修改流程信息管理", dataType = "DriveFlowEditParam")
	@PreAuthorize("hasPermission('/basics/driveFlow',  'basics:driveFlow:edit')")
	@EventLog(message = "修改流程信息管理", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody DriveFlowEditParam driveFlowEditParam) {
		return driveFlowRepository.update(driveFlowEditParam);
	}

	/**
	* 删除流程信息管理
	*/
	@ApiOperation("删除流程信息管理")
	@ApiImplicitParam(name = "${pkColumn.propertyName}", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/basics/driveFlow',  'basics:driveFlow:delete')")
	@EventLog(message = "删除流程信息管理", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(driveFlowService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除流程信息管理
	*/
	@ApiOperation("通过主键删除流程信息管理")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除流程信息管理", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return driveFlowRepository.deleteById(id);
	}

	/**
	* 导出流程信息管理
	*/
	@ApiOperation("导出流程信息管理")
	@PreAuthorize("hasPermission('/basics/driveFlow',  'basics:driveFlow:export')")
	@SneakyThrows
	@EventLog(message = "导出流程信息管理", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(DriveFlowPageQueryParam param, HttpServletResponse response) {
		driveFlowRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用流程信息管理")
	@PreAuthorize("hasPermission('/basics/driveFlow',  'basics:driveFlow:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用流程信息管理", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody DriveFlowEditParam driveFlowEditParam) {
		return driveFlowRepository.changeStatus(driveFlowEditParam);
	}

}
