package com.drive.basics.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.basics.pojo.dto.OperatorEditParam;
import com.drive.basics.pojo.dto.OperatorPageQueryParam;
import com.drive.basics.pojo.entity.OperatorEntity;
import com.drive.basics.pojo.vo.OperatorVo;
import com.drive.basics.repository.OperatorRepository;
import com.drive.basics.service.OperatorService;
import com.drive.basics.service.mapstruct.OperatorMapStruct;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.data.utils.ExcelUtils;
import com.drive.common.log.annotation.EventLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


/**
 * 运营商基础信息管理
 *
 * @author xiaoguo
 */
@Api(tags = "运营商基础信息管理")
@Slf4j
@RestController
@RequestMapping("/operator")
public class OperatorController extends BaseController<OperatorPageQueryParam, OperatorEntity> {

	@Autowired
	private OperatorService operatorService;
	@Autowired
	private OperatorRepository operatorRepository;
	@Autowired
	private OperatorMapStruct operatorMapStruct;

	/**
	* 运营商基础信息 分页列表
	*/
	@ApiOperation("运营商基础信息分页列表")
	//@PreAuthorize("hasPermission('/basics/operator',  'basics:operator:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody OperatorPageQueryParam param) {

		Page<OperatorEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
		IPage<OperatorEntity> pageList = operatorService.page(page, this.getQueryWrapper(operatorMapStruct, param));
		Page<OperatorVo> operatorVoPage = operatorMapStruct.toVoList(pageList);
		return R.success(operatorVoPage);
	}

	/**
	 * 部门列表
	 */
	@ApiOperation("运营商列表")
	//@PreAuthorize("hasPermission('/operator',  'basics:operator:allList')")
	@GetMapping(value = "/allList")
	public ResObject allList() {
		List<OperatorEntity> allList = operatorService.list();
		List<OperatorVo> allVoList = operatorMapStruct.toVoList(allList);
		return R.success(allVoList);
	}

	@ApiOperation("运营商列表")
	//@PreAuthorize("hasPermission('/operator',  'basics:operator:findAllList')")
	@GetMapping(value = "/findAllList")
	public ResObject findAllList() {
		return R.success(operatorService.findAllList());
	}

	/**
	* 获取运营商基础信息
	*/
	@ApiOperation("获取运营商基础信息")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/basics/operator',  'basics:operator:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		OperatorEntity operator = operatorService.getById(id);
		return R.success(operatorMapStruct.toVo(operator));
	}

	/**
	* 新增运营商基础信息
	*/
	@ApiOperation("新增运营商基础信息")
	@ApiImplicitParam(name = "OperatorEditParam ", value = "新增运营商基础信息", dataType = "OperatorEditParam")
	//@PreAuthorize("hasPermission('/basics/operator',  'basics:operator:add')")
	@EventLog(message = "新增运营商基础信息", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody OperatorEditParam operatorEditParam) {
		OperatorEntity operator = operatorMapStruct.toEntity(operatorEditParam);
		return R.toRes(operatorService.save(operator));
	}
	@ApiOperation("新增运营商基础信息")
	@ApiImplicitParam(name = "OperatorEditParam ", value = "新增运营商基础信息", dataType = "OperatorEditParam")
	//@PreAuthorize("hasPermission('/basics/operator',  'basics:operator:add')")
	@EventLog(message = "新增运营商基础信息", businessType = EventLogEnum.CREATE)
	@PostMapping("/saveOperator")
	public ResObject saveOperator(@Valid @RequestBody OperatorEditParam operatorEditParam) {
		return operatorRepository.saveOperator(operatorEditParam);
	}

	/**
	* 修改运营商基础信息
	*/
	@ApiOperation("修改运营商基础信息")
	@ApiImplicitParam(name = "OperatorEditParam ", value = "修改运营商基础信息", dataType = "OperatorEditParam")
	//@PreAuthorize("hasPermission('/basics/operator',  'basics:operator:edit')")
	@EventLog(message = "修改运营商基础信息", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody OperatorEditParam operatorEditParam) {
		OperatorEntity operator = operatorMapStruct.toEntity(operatorEditParam);
		return R.toRes(operatorService.updateById(operator));
	}

	/**
	* 删除运营商基础信息
	*/
	@ApiOperation("删除运营商基础信息")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/basics/operator',  'basics:operator:delete')")
	@EventLog(message = "删除运营商基础信息", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(operatorService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 导出运营商基础信息
	*/
	@ApiOperation("导出运营商基础信息")
	//@PreAuthorize("hasPermission('/basics/operator',  'basics:operator:export')")
	@SneakyThrows
	@EventLog(message = "导出运营商基础信息", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody OperatorPageQueryParam param, HttpServletResponse response) {
		List<OperatorEntity> list = operatorService.list(this.getQueryWrapper(operatorMapStruct, param));
		List<OperatorVo> operatorVoList = operatorMapStruct.toVoList(list);
		ExcelUtils.exportExcel(operatorVoList, OperatorVo.class, "运营商基础信息", new ExportParams(), response);
	}

}
