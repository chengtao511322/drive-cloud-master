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
import com.drive.admin.service.OneFeeSystemVipCoachService;
import com.drive.admin.repository.OneFeeSystemVipCoachRepository;


/**
 * 一费制vip教练管理
 * @author xiaoguo
 */
@Api(tags = "一费制vip教练管理")
@Slf4j
@RestController
@RequestMapping("/oneFeeSystemVipCoach")
public class OneFeeSystemVipCoachController extends BaseController<OneFeeSystemVipCoachPageQueryParam, OneFeeSystemVipCoachEntity> {

	// 一费制vip教练 服务
	@Autowired
	private OneFeeSystemVipCoachService oneFeeSystemVipCoachService;
	// 一费制vip教练 业务服务
	@Autowired
	private OneFeeSystemVipCoachRepository oneFeeSystemVipCoachRepository;
	// 一费制vip教练 DO-DTO转化
	@Autowired
	private OneFeeSystemVipCoachMapStruct oneFeeSystemVipCoachMapStruct;

	/**
	* 一费制vip教练 分页列表
	*/
	@ApiOperation("一费制vip教练分页列表")
	@PreAuthorize("hasPermission('/admin/oneFeeSystemVipCoach',  'admin:oneFeeSystemVipCoach:query')")
	@GetMapping(value = "/pageList")
	public ResObject pageList(@Valid OneFeeSystemVipCoachPageQueryParam param) {
		return oneFeeSystemVipCoachRepository.pageList(param);
	}
	/**
	* 一费制vip教练 列表
	*/
	@ApiOperation("一费制vip教练列表")
	@PreAuthorize("hasPermission('/admin/oneFeeSystemVipCoach',  'admin:oneFeeSystemVipCoach:query')")
	@GetMapping(value = "/findList")
	public ResObject findList(@Valid OneFeeSystemVipCoachPageQueryParam param) {
		return oneFeeSystemVipCoachRepository.findList(param);
	}

	/**
	* 获取一费制vip教练
	*/
	@ApiOperation("获取一费制vip教练")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/oneFeeSystemVipCoach',  'admin:oneFeeSystemVipCoach:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return oneFeeSystemVipCoachRepository.getById(id);
	}

	/**
	 * 条件查询获取一费制vip教练
	 */
	@ApiOperation("条件查询获取一费制vip教练")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/oneFeeSystemVipCoach',  'admin:oneFeeSystemVipCoach:query')")
	@GetMapping("/getInfo")
	public ResObject getInfo(@PathVariable OneFeeSystemVipCoachPageQueryParam param) {
		return oneFeeSystemVipCoachRepository.getInfo(param);
	}

	/**
	* 新增一费制vip教练
	*/
	@ApiOperation("新增一费制vip教练")
	@ApiImplicitParam(name = "OneFeeSystemVipCoachEditParam ", value = "新增一费制vip教练", dataType = "OneFeeSystemVipCoachEditParam")
	@PreAuthorize("hasPermission('/admin/oneFeeSystemVipCoach',  'admin:oneFeeSystemVipCoach:add')")
	@EventLog(message = "新增一费制vip教练", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody OneFeeSystemVipCoachInstallParam oneFeeSystemVipCoachInstallParam) {
		return oneFeeSystemVipCoachRepository.save(oneFeeSystemVipCoachInstallParam);
	}

	/**
	* 修改一费制vip教练
	*/
	@ApiOperation("修改一费制vip教练")
	@ApiImplicitParam(name = "OneFeeSystemVipCoachEditParam ", value = "修改一费制vip教练", dataType = "OneFeeSystemVipCoachEditParam")
	@PreAuthorize("hasPermission('/admin/oneFeeSystemVipCoach',  'admin:oneFeeSystemVipCoach:edit')")
	@EventLog(message = "修改一费制vip教练", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody OneFeeSystemVipCoachEditParam oneFeeSystemVipCoachEditParam) {
		return oneFeeSystemVipCoachRepository.update(oneFeeSystemVipCoachEditParam);
	}

	/**
	* 删除一费制vip教练
	*/
	@ApiOperation("删除一费制vip教练")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/oneFeeSystemVipCoach',  'admin:oneFeeSystemVipCoach:delete')")
	@EventLog(message = "删除一费制vip教练", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(oneFeeSystemVipCoachService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除一费制vip教练
	*/
	@ApiOperation("通过主键删除一费制vip教练")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除一费制vip教练", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return oneFeeSystemVipCoachRepository.deleteById(id);
	}

	/**
	* 导出一费制vip教练
	*/
	@ApiOperation("导出一费制vip教练")
	@PreAuthorize("hasPermission('/admin/oneFeeSystemVipCoach',  'admin:oneFeeSystemVipCoach:export')")
	@SneakyThrows
	@EventLog(message = "导出一费制vip教练", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(OneFeeSystemVipCoachPageQueryParam param, HttpServletResponse response) {
		oneFeeSystemVipCoachRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用一费制vip教练")
	@PreAuthorize("hasPermission('/admin/oneFeeSystemVipCoach',  'admin:oneFeeSystemVipCoach:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用一费制vip教练", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody OneFeeSystemVipCoachEditParam oneFeeSystemVipCoachEditParam) {
		return oneFeeSystemVipCoachRepository.changeStatus(oneFeeSystemVipCoachEditParam);
	}

}
