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
import com.drive.admin.service.OperatorRecruitService;
import com.drive.admin.repository.OperatorRecruitRepository;


/**
 * 运营商加盟申请管理
 *
 * @author xiaoguo
 */
@Api(tags = "运营商加盟申请管理")
@Slf4j
@RestController
@RequestMapping("/operatorRecruit")
public class OperatorRecruitController extends BaseController<OperatorRecruitPageQueryParam, OperatorRecruitEntity> {

	// 运营商加盟申请 服务
	@Autowired
	private OperatorRecruitService operatorRecruitService;
	// 运营商加盟申请 业务服务
	@Autowired
	private OperatorRecruitRepository operatorRecruitRepository;
	// 运营商加盟申请 DO-DTO转化
	@Autowired
	private OperatorRecruitMapStruct operatorRecruitMapStruct;

	/**
	* 运营商加盟申请 分页列表
	*/
	@ApiOperation("运营商加盟申请分页列表")
	//@PreAuthorize("hasPermission('/admin/operatorRecruit',  'admin:operatorRecruit:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody OperatorRecruitPageQueryParam param) {
		return operatorRecruitRepository.pageList(param);
	}
	/**
	* 运营商加盟申请 列表
	*/
	@ApiOperation("运营商加盟申请列表")
	//@PreAuthorize("hasPermission('/admin/operatorRecruit',  'admin:operatorRecruit:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody OperatorRecruitPageQueryParam param) {
		return operatorRecruitRepository.findList(param);
	}

	/**
	* 获取运营商加盟申请
	*/
	@ApiOperation("获取运营商加盟申请")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/operatorRecruit',  'admin:operatorRecruit:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return operatorRecruitRepository.getById(id);
	}

	/**
	 * 条件查询获取运营商加盟申请
	 */
	@ApiOperation("条件查询获取运营商加盟申请")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/operatorRecruit',  'admin:operatorRecruit:query')")
	@PostMapping("/getInfo")
	public ResObject getInfo(@RequestBody OperatorRecruitPageQueryParam param) {
		return operatorRecruitRepository.getInfo(param);
	}

	/**
	* 新增运营商加盟申请
	*/
	@ApiOperation("新增运营商加盟申请")
	@ApiImplicitParam(name = "OperatorRecruitEditParam ", value = "新增运营商加盟申请", dataType = "OperatorRecruitEditParam")
	//@PreAuthorize("hasPermission('/admin/operatorRecruit',  'admin:operatorRecruit:add')")
	@EventLog(message = "新增运营商加盟申请", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody OperatorRecruitInstallParam operatorRecruitInstallParam) {
		return operatorRecruitRepository.save(operatorRecruitInstallParam);
	}

	/**
	* 修改运营商加盟申请
	*/
	@ApiOperation("修改运营商加盟申请")
	@ApiImplicitParam(name = "OperatorRecruitEditParam ", value = "修改运营商加盟申请", dataType = "OperatorRecruitEditParam")
	//@PreAuthorize("hasPermission('/admin/operatorRecruit',  'admin:operatorRecruit:edit')")
	@EventLog(message = "修改运营商加盟申请", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody OperatorRecruitEditParam operatorRecruitEditParam) {
		return operatorRecruitRepository.update(operatorRecruitEditParam);
	}

	/**
	* 删除运营商加盟申请
	*/
	@ApiOperation("删除运营商加盟申请")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/operatorRecruit',  'admin:operatorRecruit:delete')")
	@EventLog(message = "删除运营商加盟申请", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable String[] ids) {
		return R.toRes(operatorRecruitService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除运营商加盟申请
	*/
	@ApiOperation("通过主键删除运营商加盟申请")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除运营商加盟申请", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return operatorRecruitRepository.deleteById(id);
	}

	/**
	* 导出运营商加盟申请
	*/
	@ApiOperation("导出运营商加盟申请")
	//@PreAuthorize("hasPermission('/admin/operatorRecruit',  'admin:operatorRecruit:export')")
	@SneakyThrows
	@EventLog(message = "导出运营商加盟申请", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody OperatorRecruitPageQueryParam param, HttpServletResponse response) {
		operatorRecruitRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用运营商加盟申请")
	//@PreAuthorize("hasPermission('/admin/operatorRecruit',  'admin:operatorRecruit:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用运营商加盟申请", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody OperatorRecruitEditParam operatorRecruitEditParam) {
		return operatorRecruitRepository.changeStatus(operatorRecruitEditParam);
	}

}
