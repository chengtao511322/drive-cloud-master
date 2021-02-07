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
import com.drive.admin.service.OneFeeSystemCoachStudentService;
import com.drive.admin.repository.OneFeeSystemCoachStudentRepository;


/**
 * 一费制学员教练关联表管理
 *
 * @author xiaoguo
 */
@Api(tags = "一费制学员教练关联表管理")
@Slf4j
@RestController
@RequestMapping("/oneFeeSystemCoachStudent")
public class OneFeeSystemCoachStudentController extends BaseController<OneFeeSystemCoachStudentPageQueryParam, OneFeeSystemCoachStudentEntity> {

	@Autowired
	private OneFeeSystemCoachStudentService oneFeeSystemCoachStudentService;
	@Autowired
	private OneFeeSystemCoachStudentRepository oneFeeSystemCoachStudentRepository;
	@Autowired
	private OneFeeSystemCoachStudentMapStruct oneFeeSystemCoachStudentMapStruct;

	/**
	* 一费制学员教练关联表 分页列表
	*/
	@ApiOperation("一费制学员教练关联表分页列表")
	@PreAuthorize("hasPermission('/admin/oneFeeSystemCoachStudent',  'admin:oneFeeSystemCoachStudent:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid OneFeeSystemCoachStudentPageQueryParam param) {
		return oneFeeSystemCoachStudentRepository.pageList(param);
	}
	/**
	* 一费制学员教练关联表 列表
	*/
	@ApiOperation("一费制学员教练关联表列表")
	@PreAuthorize("hasPermission('/admin/oneFeeSystemCoachStudent',  'admin:oneFeeSystemCoachStudent:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid OneFeeSystemCoachStudentPageQueryParam param) {
		return oneFeeSystemCoachStudentRepository.findList(param);
	}

	/**
	* 获取一费制学员教练关联表
	*/
	@ApiOperation("获取一费制学员教练关联表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/oneFeeSystemCoachStudent',  'admin:oneFeeSystemCoachStudent:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return oneFeeSystemCoachStudentRepository.getById(id);
	}

	/**
	* 新增一费制学员教练关联表
	*/
	@ApiOperation("新增一费制学员教练关联表")
	@ApiImplicitParam(name = "OneFeeSystemCoachStudentEditParam ", value = "新增一费制学员教练关联表", dataType = "OneFeeSystemCoachStudentEditParam")
	@PreAuthorize("hasPermission('/admin/oneFeeSystemCoachStudent',  'admin:oneFeeSystemCoachStudent:add')")
	@EventLog(message = "新增一费制学员教练关联表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody OneFeeSystemCoachStudentEditParam oneFeeSystemCoachStudentEditParam) {
		return oneFeeSystemCoachStudentRepository.save(oneFeeSystemCoachStudentEditParam);
	}

	/**
	* 修改一费制学员教练关联表
	*/
	@ApiOperation("修改一费制学员教练关联表")
	@ApiImplicitParam(name = "OneFeeSystemCoachStudentEditParam ", value = "修改一费制学员教练关联表", dataType = "OneFeeSystemCoachStudentEditParam")
	@PreAuthorize("hasPermission('/admin/oneFeeSystemCoachStudent',  'admin:oneFeeSystemCoachStudent:edit')")
	@EventLog(message = "修改一费制学员教练关联表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody OneFeeSystemCoachStudentEditParam oneFeeSystemCoachStudentEditParam) {
		return oneFeeSystemCoachStudentRepository.update(oneFeeSystemCoachStudentEditParam);
	}

	/**
	* 删除一费制学员教练关联表
	*/
	@ApiOperation("删除一费制学员教练关联表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/oneFeeSystemCoachStudent',  'admin:oneFeeSystemCoachStudent:delete')")
	@EventLog(message = "删除一费制学员教练关联表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(oneFeeSystemCoachStudentService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除一费制学员教练关联表
	*/
	@ApiOperation("通过主键删除一费制学员教练关联表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除一费制学员教练关联表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return oneFeeSystemCoachStudentRepository.deleteById(id);
	}

	/**
	* 导出一费制学员教练关联表
	*/
	@ApiOperation("导出一费制学员教练关联表")
	@PreAuthorize("hasPermission('/admin/oneFeeSystemCoachStudent',  'admin:oneFeeSystemCoachStudent:export')")
	@SneakyThrows
	@EventLog(message = "导出一费制学员教练关联表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(OneFeeSystemCoachStudentPageQueryParam param, HttpServletResponse response) {
		oneFeeSystemCoachStudentRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用一费制学员教练关联表")
	@PreAuthorize("hasPermission('/admin/oneFeeSystemCoachStudent',  'admin:oneFeeSystemCoachStudent:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用一费制学员教练关联表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody OneFeeSystemCoachStudentEditParam oneFeeSystemCoachStudentEditParam) {
		return oneFeeSystemCoachStudentRepository.changeStatus(oneFeeSystemCoachStudentEditParam);
	}

}
