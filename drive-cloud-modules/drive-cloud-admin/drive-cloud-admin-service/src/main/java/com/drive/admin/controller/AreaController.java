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
import com.drive.admin.service.AreaService;
import com.drive.admin.repository.AreaRepository;


/**
 * 管理
 *
 * @author xiaoguo
 */
@Api(tags = "管理")
@Slf4j
@RestController
@RequestMapping("/area")
public class AreaController extends BaseController<AreaPageQueryParam, AreaEntity> {

	@Autowired
	private AreaService areaService;
	@Autowired
	private AreaRepository areaRepository;
	@Autowired
	private AreaMapStruct areaMapStruct;

	/**
	*  分页列表
	*/
	@ApiOperation("分页列表")
	@PreAuthorize("hasPermission('/admin/area',  'admin:area:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid AreaPageQueryParam param) {
		return areaRepository.pageList(param);
	}
	/**
	*  列表
	*/
	@ApiOperation("列表")
	@PreAuthorize("hasPermission('/admin/area',  'admin:area:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid AreaPageQueryParam param) {
		return areaRepository.findList(param);
	}

	/**
	 * 部门列表
	 */
	@ApiOperation("运营商列表")
	@PreAuthorize("hasPermission('/admin/area',  'admin:area:allList')")
	@GetMapping(value = "/allList")
	public ResObject allList() {
		return areaRepository.allList();
	}

	/**
	* 获取
	*/
	@ApiOperation("获取")
	@ApiImplicitParam(name = "baCode", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/area',  'admin:area:query')")
	@GetMapping("/{baCode}")
	public ResObject get(@PathVariable String baCode) {
		return areaRepository.getInfo(baCode);
	}

	/**
	* 新增
	*/
	@ApiOperation("新增")
	@ApiImplicitParam(name = "AreaEditParam ", value = "新增", dataType = "AreaEditParam")
	@PreAuthorize("hasPermission('/admin/area',  'admin:area:add')")
	@EventLog(message = "新增", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody AreaEditParam areaEditParam) {
		return areaRepository.save(areaEditParam);
	}

	/**
	* 修改
	*/
	@ApiOperation("修改")
	@ApiImplicitParam(name = "AreaEditParam ", value = "修改", dataType = "AreaEditParam")
	@PreAuthorize("hasPermission('/admin/area',  'admin:area:edit')")
	@EventLog(message = "修改", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody AreaEditParam areaEditParam) {
		return areaRepository.update(areaEditParam);
	}

	/**
	* 删除
	*/
	@ApiOperation("删除")
	@ApiImplicitParam(name = "baCode", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/area',  'admin:area:delete')")
	@EventLog(message = "删除", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{baCodes}")
	public ResObject delete(@PathVariable Long[] baCodes) {
		return R.toRes(areaService.removeByIds(Arrays.asList(baCodes)));
	}

	/**
	* 通过主键删除
	*/
	@ApiOperation("通过主键删除")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return areaRepository.deleteById(id);
	}

	/**
	* 导出
	*/
	@ApiOperation("导出")
	@PreAuthorize("hasPermission('/admin/area',  'admin:area:export')")
	@SneakyThrows
	@EventLog(message = "导出", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(AreaPageQueryParam param, HttpServletResponse response) {
		areaRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用")
	@PreAuthorize("hasPermission('/admin/area',  'admin:area:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody AreaEditParam areaEditParam) {
		return areaRepository.changeStatus(areaEditParam);
	}

}
