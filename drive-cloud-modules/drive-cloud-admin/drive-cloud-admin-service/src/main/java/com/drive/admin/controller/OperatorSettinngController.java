package com.drive.admin.controller;

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
import com.drive.admin.pojo.entity.*;
import com.drive.admin.pojo.vo.*;
import com.drive.admin.pojo.dto.*;
import com.drive.admin.service.mapstruct.*;
import com.drive.admin.service.OperatorSettinngService;
import com.drive.admin.repository.OperatorSettinngRepository;


/**
 * 运营商参数设置表管理
 *
 * @author xiaoguo
 */
@Api(tags = "运营商参数设置表管理")
@Slf4j
@RestController
@RequestMapping("/operatorSettinng")
public class OperatorSettinngController extends BaseController<OperatorSettinngPageQueryParam, OperatorSettinngEntity> {

	// 运营商参数设置表 服务
	@Autowired
	private OperatorSettinngService operatorSettinngService;
	// 运营商参数设置表 业务服务
	@Autowired
	private OperatorSettinngRepository operatorSettinngRepository;
	// 运营商参数设置表 DO-DTO转化
	@Autowired
	private OperatorSettinngMapStruct operatorSettinngMapStruct;

	/**
	* 运营商参数设置表 分页列表
	*/
	@ApiOperation("运营商参数设置表分页列表")
	//@PreAuthorize("hasPermission('/admin/operatorSettinng',  'admin:operatorSettinng:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody OperatorSettinngPageQueryParam param) {
		return operatorSettinngRepository.pageList(param);
	}
	/**
	* 运营商参数设置表 列表
	*/
	@ApiOperation("运营商参数设置表列表")
	//@PreAuthorize("hasPermission('/admin/operatorSettinng',  'admin:operatorSettinng:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody OperatorSettinngPageQueryParam param) {
		return operatorSettinngRepository.findList(param);
	}

	/**
	* 获取运营商参数设置表
	*/
	@ApiOperation("获取运营商参数设置表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/operatorSettinng',  'admin:operatorSettinng:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return operatorSettinngRepository.getById(id);
	}

	/**
	 * 条件查询获取运营商参数设置表
	 */
	@ApiOperation("条件查询获取运营商参数设置表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/operatorSettinng',  'admin:operatorSettinng:query')")
	@PostMapping("/getInfo")
	public ResObject getInfo(@RequestBody OperatorSettinngPageQueryParam param) {
		return operatorSettinngRepository.getInfo(param);
	}


	/**
	* 新增运营商参数设置表
	*/
	@ApiOperation("新增运营商参数设置表")
	@ApiImplicitParam(name = "OperatorSettinngEditParam ", value = "新增运营商参数设置表", dataType = "OperatorSettinngEditParam")
	//@PreAuthorize("hasPermission('/admin/operatorSettinng',  'admin:operatorSettinng:add')")
	@EventLog(message = "新增运营商参数设置表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody OperatorSettinngInstallParam operatorSettinngInstallParam) {
		return operatorSettinngRepository.save(operatorSettinngInstallParam);
	}

	/**
	* 修改运营商参数设置表
	*/
	@ApiOperation("修改运营商参数设置表")
	@ApiImplicitParam(name = "OperatorSettinngEditParam ", value = "修改运营商参数设置表", dataType = "OperatorSettinngEditParam")
	//@PreAuthorize("hasPermission('/admin/operatorSettinng',  'admin:operatorSettinng:edit')")
	@EventLog(message = "修改运营商参数设置表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody OperatorSettinngEditParam operatorSettinngEditParam) {
		return operatorSettinngRepository.update(operatorSettinngEditParam);
	}

	/**
	* 删除运营商参数设置表
	*/
	@ApiOperation("删除运营商参数设置表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/operatorSettinng',  'admin:operatorSettinng:delete')")
	@EventLog(message = "删除运营商参数设置表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable String[] ids) {
		return R.toRes(operatorSettinngService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除运营商参数设置表
	*/
	@ApiOperation("通过主键删除运营商参数设置表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除运营商参数设置表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return operatorSettinngRepository.deleteById(id);
	}

	/**
	* 导出运营商参数设置表
	*/
	@ApiOperation("导出运营商参数设置表")
	//@PreAuthorize("hasPermission('/admin/operatorSettinng',  'admin:operatorSettinng:export')")
	@SneakyThrows
	@EventLog(message = "导出运营商参数设置表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody OperatorSettinngPageQueryParam param, HttpServletResponse response) {
		operatorSettinngRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用运营商参数设置表")
	//@PreAuthorize("hasPermission('/admin/operatorSettinng',  'admin:operatorSettinng:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用运营商参数设置表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody OperatorSettinngEditParam operatorSettinngEditParam) {
		return operatorSettinngRepository.changeStatus(operatorSettinngEditParam);
	}

}
