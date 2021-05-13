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
import com.drive.admin.service.TestTrainPriceService;
import com.drive.admin.repository.TestTrainPriceRepository;


/**
 * 平台报名考试练车单价表管理
 *
 * @author xiaoguo
 */
@Api(tags = "平台报名考试练车单价表管理")
@Slf4j
@RestController
@RequestMapping("/testTrainPrice")
public class TestTrainPriceController extends BaseController<TestTrainPricePageQueryParam, TestTrainPriceEntity> {

	// 平台报名考试练车单价表 服务
	@Autowired
	private TestTrainPriceService testTrainPriceService;
	// 平台报名考试练车单价表 业务服务
	@Autowired
	private TestTrainPriceRepository testTrainPriceRepository;
	// 平台报名考试练车单价表 DO-DTO转化
	@Autowired
	private TestTrainPriceMapStruct testTrainPriceMapStruct;

	/**
	* 平台报名考试练车单价表 分页列表
	*/
	@ApiOperation("平台报名考试练车单价表分页列表")
	//@PreAuthorize("hasPermission('/admin/testTrainPrice',  'admin:testTrainPrice:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody TestTrainPricePageQueryParam param) {
		return testTrainPriceRepository.pageList(param);
	}
	/**
	* 平台报名考试练车单价表 列表
	*/
	@ApiOperation("平台报名考试练车单价表列表")
	//@PreAuthorize("hasPermission('/admin/testTrainPrice',  'admin:testTrainPrice:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody TestTrainPricePageQueryParam param) {
		return testTrainPriceRepository.findList(param);
	}

	/**
	* 获取平台报名考试练车单价表
	*/
	@ApiOperation("获取平台报名考试练车单价表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/testTrainPrice',  'admin:testTrainPrice:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return testTrainPriceRepository.getById(id);
	}

	/**
	 * 条件查询获取平台报名考试练车单价表
	 */
	@ApiOperation("条件查询获取平台报名考试练车单价表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/testTrainPrice',  'admin:testTrainPrice:query')")
	@PostMapping("/getInfo")
	public ResObject getInfo(@Valid @RequestBody TestTrainPricePageQueryParam param) {
		return testTrainPriceRepository.getInfo(param);
	}

	/**
	* 新增平台报名考试练车单价表
	*/
	@ApiOperation("新增平台报名考试练车单价表")
	@ApiImplicitParam(name = "TestTrainPriceEditParam ", value = "新增平台报名考试练车单价表", dataType = "TestTrainPriceEditParam")
	//@PreAuthorize("hasPermission('/admin/testTrainPrice',  'admin:testTrainPrice:add')")
	@EventLog(message = "新增平台报名考试练车单价表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody TestTrainPriceInstallParam testTrainPriceInstallParam) {
		return testTrainPriceRepository.save(testTrainPriceInstallParam);
	}

	/**
	* 修改平台报名考试练车单价表
	*/
	@ApiOperation("修改平台报名考试练车单价表")
	@ApiImplicitParam(name = "TestTrainPriceEditParam ", value = "修改平台报名考试练车单价表", dataType = "TestTrainPriceEditParam")
	//@PreAuthorize("hasPermission('/admin/testTrainPrice',  'admin:testTrainPrice:edit')")
	@EventLog(message = "修改平台报名考试练车单价表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody TestTrainPriceEditParam testTrainPriceEditParam) {
		return testTrainPriceRepository.update(testTrainPriceEditParam);
	}

	/**
	* 删除平台报名考试练车单价表
	*/
	@ApiOperation("删除平台报名考试练车单价表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/testTrainPrice',  'admin:testTrainPrice:delete')")
	@EventLog(message = "删除平台报名考试练车单价表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(testTrainPriceService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除平台报名考试练车单价表
	*/
	@ApiOperation("通过主键删除平台报名考试练车单价表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除平台报名考试练车单价表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return testTrainPriceRepository.deleteById(id);
	}

	/**
	* 导出平台报名考试练车单价表
	*/
	@ApiOperation("导出平台报名考试练车单价表")
	//@PreAuthorize("hasPermission('/admin/testTrainPrice',  'admin:testTrainPrice:export')")
	@SneakyThrows
	@EventLog(message = "导出平台报名考试练车单价表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(TestTrainPricePageQueryParam param, HttpServletResponse response) {
		testTrainPriceRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用平台报名考试练车单价表")
	//@PreAuthorize("hasPermission('/admin/testTrainPrice',  'admin:testTrainPrice:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用平台报名考试练车单价表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody TestTrainPriceEditParam testTrainPriceEditParam) {
		return testTrainPriceRepository.changeStatus(testTrainPriceEditParam);
	}

}
