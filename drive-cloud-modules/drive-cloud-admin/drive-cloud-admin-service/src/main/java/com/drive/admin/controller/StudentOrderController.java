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
import com.drive.admin.service.StudentOrderService;
import com.drive.admin.repository.StudentOrderRepository;


/**
 * 学员订单表管理
 *
 * @author xiaoguo
 */
@Api(tags = "学员订单表管理")
@Slf4j
@RestController
@RequestMapping("/studentOrder")
public class StudentOrderController extends BaseController<StudentOrderPageQueryParam, StudentOrderEntity> {

	// 学员订单表 服务
	@Autowired
	private StudentOrderService studentOrderService;
	// 学员订单表 业务服务
	@Autowired
	private StudentOrderRepository studentOrderRepository;
	// 学员订单表 DO-DTO转化
	@Autowired
	private StudentOrderMapStruct studentOrderMapStruct;

	/**
	* 学员订单表 分页列表
	*/
	@ApiOperation("学员订单表分页列表")
	@PreAuthorize("hasPermission('/admin/studentOrder',  'admin:studentOrder:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid StudentOrderPageQueryParam param) {
		return studentOrderRepository.pageList(param);
	}
	/**
	* 学员订单表 列表
	*/
	@ApiOperation("学员订单表列表")
	@PreAuthorize("hasPermission('/admin/studentOrder',  'admin:studentOrder:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid StudentOrderPageQueryParam param) {
		return studentOrderRepository.findList(param);
	}

	/**
	* 获取学员订单表
	*/
	@ApiOperation("获取学员订单表")
	@ApiImplicitParam(name = "orderNo", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/studentOrder',  'admin:studentOrder:query')")
	@GetMapping("/{orderNo}")
	public ResObject get(@PathVariable String orderNo) {
		return studentOrderRepository.getById(orderNo);
	}

	/**
	* 新增学员订单表
	*/
	@ApiOperation("新增学员订单表")
	@ApiImplicitParam(name = "StudentOrderEditParam ", value = "新增学员订单表", dataType = "StudentOrderEditParam")
	@PreAuthorize("hasPermission('/admin/studentOrder',  'admin:studentOrder:add')")
	@EventLog(message = "新增学员订单表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody StudentOrderEditParam studentOrderEditParam) {
		return studentOrderRepository.save(studentOrderEditParam);
	}

	/**
	* 修改学员订单表
	*/
	@ApiOperation("修改学员订单表")
	@ApiImplicitParam(name = "StudentOrderEditParam ", value = "修改学员订单表", dataType = "StudentOrderEditParam")
	@PreAuthorize("hasPermission('/admin/studentOrder',  'admin:studentOrder:edit')")
	@EventLog(message = "修改学员订单表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody StudentOrderEditParam studentOrderEditParam) {
		return studentOrderRepository.update(studentOrderEditParam);
	}

	/**
	* 删除学员订单表
	*/
	@ApiOperation("删除学员订单表")
	@ApiImplicitParam(name = "orderNo", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/studentOrder',  'admin:studentOrder:delete')")
	@EventLog(message = "删除学员订单表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{orderNos}")
	public ResObject delete(@PathVariable Long[] orderNos) {
		return R.toRes(studentOrderService.removeByIds(Arrays.asList(orderNos)));
	}

	/**
	* 通过主键删除学员订单表
	*/
	@ApiOperation("通过主键删除学员订单表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除学员订单表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return studentOrderRepository.deleteById(id);
	}

	/**
	* 导出学员订单表
	*/
	@ApiOperation("导出学员订单表")
	@PreAuthorize("hasPermission('/admin/studentOrder',  'admin:studentOrder:export')")
	@SneakyThrows
	@EventLog(message = "导出学员订单表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(StudentOrderPageQueryParam param, HttpServletResponse response) {
		studentOrderRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用学员订单表")
	@PreAuthorize("hasPermission('/admin/studentOrder',  'admin:studentOrder:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用学员订单表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody StudentOrderEditParam studentOrderEditParam) {
		return studentOrderRepository.changeStatus(studentOrderEditParam);
	}

}
