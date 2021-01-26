package com.drive.basics.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.basics.pojo.dto.AppVersionEditParam;
import com.drive.basics.pojo.dto.AppVersionPageQueryParam;
import com.drive.basics.pojo.entity.AppVersionEntity;
import com.drive.basics.pojo.vo.AppVersionVo;
import com.drive.basics.service.AppVersionService;
import com.drive.basics.service.mapstruct.AppVersionMapStruct;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


/**
 * app版本控制信息表管理
 *
 * @author xiaoguo
 */
@Api(tags = "app版本控制信息表管理")
@Slf4j
@RestController
@RequestMapping("/appVersion")
public class AppVersionController extends BaseController<AppVersionPageQueryParam, AppVersionEntity> {

	@Autowired
	private AppVersionService appVersionService;
	@Autowired
	private AppVersionMapStruct appVersionMapStruct;

	/**
	* app版本控制信息表 分页列表
	*/
	@ApiOperation("app版本控制信息表分页列表")
	@PreAuthorize("hasPermission('/basics/appVersion',  'basics:appVersion:query')")
	@GetMapping(value = "/pageList")
	public ResObject pageList(@Valid AppVersionPageQueryParam param) {

		Page<AppVersionEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
		IPage<AppVersionEntity> pageList = appVersionService.page(page, this.getQueryWrapper(appVersionMapStruct, param));
		Page<AppVersionVo> appVersionVoPage = appVersionMapStruct.toVoList(pageList);
		return R.success(appVersionVoPage);
	}

	/**
	* 获取app版本控制信息表
	*/
	@ApiOperation("获取app版本控制信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/basics/appVersion',  'basics:appVersion:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable Long id) {
		AppVersionEntity appVersion = appVersionService.getById(id);
		return R.success(appVersionMapStruct.toVo(appVersion));
	}

	/**
	* 新增app版本控制信息表
	*/
	@ApiOperation("新增app版本控制信息表")
	@ApiImplicitParam(name = "AppVersionEditParam ", value = "新增app版本控制信息表", dataType = "AppVersionEditParam")
	@PreAuthorize("hasPermission('/basics/appVersion',  'basics:appVersion:add')")
	@EventLog(message = "新增app版本控制信息表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody AppVersionEditParam appVersionEditParam) {
		AppVersionEntity appVersion = appVersionMapStruct.toEntity(appVersionEditParam);
		return R.toRes(appVersionService.save(appVersion));
	}

	/**
	* 修改app版本控制信息表
	*/
	@ApiOperation("修改app版本控制信息表")
	@ApiImplicitParam(name = "AppVersionEditParam ", value = "修改app版本控制信息表", dataType = "AppVersionEditParam")
	@PreAuthorize("hasPermission('/basics/appVersion',  'basics:appVersion:edit')")
	@EventLog(message = "修改app版本控制信息表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody AppVersionEditParam appVersionEditParam) {
		AppVersionEntity appVersion = appVersionMapStruct.toEntity(appVersionEditParam);
		return R.toRes(appVersionService.updateById(appVersion));
	}

	/**
	* 删除app版本控制信息表
	*/
	@ApiOperation("删除app版本控制信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/basics/appVersion',  'basics:appVersion:delete')")
	@EventLog(message = "删除app版本控制信息表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(appVersionService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 导出app版本控制信息表
	*/
	@ApiOperation("导出app版本控制信息表")
	@PreAuthorize("hasPermission('/basics/appVersion',  'basics:appVersion:export')")
	@SneakyThrows
	@EventLog(message = "导出app版本控制信息表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(AppVersionPageQueryParam param, HttpServletResponse response) {
		List<AppVersionEntity> list = appVersionService.list(this.getQueryWrapper(appVersionMapStruct, param));
		List<AppVersionVo> appVersionVoList = appVersionMapStruct.toVoList(list);
		ExcelUtils.exportExcel(appVersionVoList, AppVersionVo.class, "app版本控制信息表", new ExportParams(), response);
	}

}
