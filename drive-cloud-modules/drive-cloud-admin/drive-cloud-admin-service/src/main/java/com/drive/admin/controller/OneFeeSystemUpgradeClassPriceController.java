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
import com.drive.admin.service.OneFeeSystemUpgradeClassPriceService;
import com.drive.admin.repository.OneFeeSystemUpgradeClassPriceRepository;


/**
 * 一费制学员升班价格控制表管理
 *
 * @author xiaoguo
 */
@Api(tags = "一费制学员升班价格控制表管理")
@Slf4j
@RestController
@RequestMapping("/oneFeeSystemUpgradeClassPrice")
public class OneFeeSystemUpgradeClassPriceController extends BaseController<OneFeeSystemUpgradeClassPricePageQueryParam, OneFeeSystemUpgradeClassPriceEntity> {

	// 一费制学员升班价格控制表 服务
	@Autowired
	private OneFeeSystemUpgradeClassPriceService oneFeeSystemUpgradeClassPriceService;
	// 一费制学员升班价格控制表 业务服务
	@Autowired
	private OneFeeSystemUpgradeClassPriceRepository oneFeeSystemUpgradeClassPriceRepository;
	// 一费制学员升班价格控制表 DO-DTO转化
	@Autowired
	private OneFeeSystemUpgradeClassPriceMapStruct oneFeeSystemUpgradeClassPriceMapStruct;

	/**
	* 一费制学员升班价格控制表 分页列表
	*/
	@ApiOperation("一费制学员升班价格控制表分页列表")
	//@PreAuthorize("hasPermission('/admin/oneFeeSystemUpgradeClassPrice',  'admin:oneFeeSystemUpgradeClassPrice:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody OneFeeSystemUpgradeClassPricePageQueryParam param) {
		return oneFeeSystemUpgradeClassPriceRepository.pageList(param);
	}
	/**
	* 一费制学员升班价格控制表 列表
	*/
	@ApiOperation("一费制学员升班价格控制表列表")
	//@PreAuthorize("hasPermission('/admin/oneFeeSystemUpgradeClassPrice',  'admin:oneFeeSystemUpgradeClassPrice:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody OneFeeSystemUpgradeClassPricePageQueryParam param) {
		return oneFeeSystemUpgradeClassPriceRepository.findList(param);
	}

	/**
	* 获取一费制学员升班价格控制表
	*/
	@ApiOperation("获取一费制学员升班价格控制表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/oneFeeSystemUpgradeClassPrice',  'admin:oneFeeSystemUpgradeClassPrice:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return oneFeeSystemUpgradeClassPriceRepository.getById(id);
	}

	/**
	 * 条件查询获取一费制学员升班价格控制表
	 */
	@ApiOperation("条件查询获取一费制学员升班价格控制表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/oneFeeSystemUpgradeClassPrice',  'admin:oneFeeSystemUpgradeClassPrice:query')")
	@PostMapping("/getInfo")
	public ResObject getInfo(@RequestBody OneFeeSystemUpgradeClassPricePageQueryParam param) {
		return oneFeeSystemUpgradeClassPriceRepository.getInfo(param);
	}

	/**
	* 新增一费制学员升班价格控制表
	*/
	@ApiOperation("新增一费制学员升班价格控制表")
	@ApiImplicitParam(name = "OneFeeSystemUpgradeClassPriceEditParam ", value = "新增一费制学员升班价格控制表", dataType = "OneFeeSystemUpgradeClassPriceEditParam")
	//@PreAuthorize("hasPermission('/admin/oneFeeSystemUpgradeClassPrice',  'admin:oneFeeSystemUpgradeClassPrice:add')")
	@EventLog(message = "新增一费制学员升班价格控制表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody OneFeeSystemUpgradeClassPriceInstallParam oneFeeSystemUpgradeClassPriceInstallParam) {
		return oneFeeSystemUpgradeClassPriceRepository.save(oneFeeSystemUpgradeClassPriceInstallParam);
	}

	/**
	* 修改一费制学员升班价格控制表
	*/
	@ApiOperation("修改一费制学员升班价格控制表")
	@ApiImplicitParam(name = "OneFeeSystemUpgradeClassPriceEditParam ", value = "修改一费制学员升班价格控制表", dataType = "OneFeeSystemUpgradeClassPriceEditParam")
	//@PreAuthorize("hasPermission('/admin/oneFeeSystemUpgradeClassPrice',  'admin:oneFeeSystemUpgradeClassPrice:edit')")
	@EventLog(message = "修改一费制学员升班价格控制表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody OneFeeSystemUpgradeClassPriceEditParam oneFeeSystemUpgradeClassPriceEditParam) {
		return oneFeeSystemUpgradeClassPriceRepository.update(oneFeeSystemUpgradeClassPriceEditParam);
	}

	/**
	* 删除一费制学员升班价格控制表
	*/
	@ApiOperation("删除一费制学员升班价格控制表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/oneFeeSystemUpgradeClassPrice',  'admin:oneFeeSystemUpgradeClassPrice:delete')")
	@EventLog(message = "删除一费制学员升班价格控制表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable String[] ids) {
		return R.toRes(oneFeeSystemUpgradeClassPriceService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除一费制学员升班价格控制表
	*/
	@ApiOperation("通过主键删除一费制学员升班价格控制表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除一费制学员升班价格控制表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return oneFeeSystemUpgradeClassPriceRepository.deleteById(id);
	}

	/**
	* 导出一费制学员升班价格控制表
	*/
	@ApiOperation("导出一费制学员升班价格控制表")
	//@PreAuthorize("hasPermission('/admin/oneFeeSystemUpgradeClassPrice',  'admin:oneFeeSystemUpgradeClassPrice:export')")
	@SneakyThrows
	@EventLog(message = "导出一费制学员升班价格控制表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody OneFeeSystemUpgradeClassPricePageQueryParam param, HttpServletResponse response) {
		oneFeeSystemUpgradeClassPriceRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用一费制学员升班价格控制表")
	//@PreAuthorize("hasPermission('/admin/oneFeeSystemUpgradeClassPrice',  'admin:oneFeeSystemUpgradeClassPrice:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用一费制学员升班价格控制表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody OneFeeSystemUpgradeClassPriceEditParam oneFeeSystemUpgradeClassPriceEditParam) {
		return oneFeeSystemUpgradeClassPriceRepository.changeStatus(oneFeeSystemUpgradeClassPriceEditParam);
	}

}
