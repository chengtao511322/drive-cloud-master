package com.drive.basics.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.basics.pojo.dto.PlatformChannelEditParam;
import com.drive.basics.pojo.dto.PlatformChannelPageQueryParam;
import com.drive.basics.pojo.entity.PlatformChannelEntity;
import com.drive.basics.pojo.vo.PlatformChannelVo;
import com.drive.basics.service.PlatformChannelService;
import com.drive.basics.service.mapstruct.PlatformChannelMapStruct;
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
 * 平台渠道表管理
 *
 * @author xiaoguo
 */
@Api(tags = "平台渠道表管理")
@Slf4j
@RestController
@RequestMapping("/platformChannel")
public class PlatformChannelController extends BaseController<PlatformChannelPageQueryParam, PlatformChannelEntity> {

	@Autowired
	private PlatformChannelService platformChannelService;
	@Autowired
	private PlatformChannelMapStruct platformChannelMapStruct;

	/**
	* 平台渠道表 分页列表
	*/
	@ApiOperation("平台渠道表分页列表")
	@PreAuthorize("hasPermission('/basics/platformChannel',  'basics:platformChannel:query')")
	@GetMapping(value = "/pageList")
	public ResObject pageList(@Valid PlatformChannelPageQueryParam param) {

		Page<PlatformChannelEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
		IPage<PlatformChannelEntity> pageList = platformChannelService.page(page, this.getQueryWrapper(platformChannelMapStruct, param));
		Page<PlatformChannelVo> platformChannelVoPage = platformChannelMapStruct.toVoList(pageList);
		return R.success(platformChannelVoPage);
	}

	/**
	* 获取平台渠道表
	*/
	@ApiOperation("获取平台渠道表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/basics/platformChannel',  'basics:platformChannel:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		PlatformChannelEntity platformChannel = platformChannelService.getById(id);
		return R.success(platformChannelMapStruct.toVo(platformChannel));
	}

	/**
	* 新增平台渠道表
	*/
	@ApiOperation("新增平台渠道表")
	@ApiImplicitParam(name = "PlatformChannelEditParam ", value = "新增平台渠道表", dataType = "PlatformChannelEditParam")
	@PreAuthorize("hasPermission('/basics/platformChannel',  'basics:platformChannel:add')")
	@EventLog(message = "新增平台渠道表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody PlatformChannelEditParam platformChannelEditParam) {
		PlatformChannelEntity platformChannel = platformChannelMapStruct.toEntity(platformChannelEditParam);
		return R.toRes(platformChannelService.save(platformChannel));
	}

	/**
	* 修改平台渠道表
	*/
	@ApiOperation("修改平台渠道表")
	@ApiImplicitParam(name = "PlatformChannelEditParam ", value = "修改平台渠道表", dataType = "PlatformChannelEditParam")
	@PreAuthorize("hasPermission('/basics/platformChannel',  'basics:platformChannel:edit')")
	@EventLog(message = "修改平台渠道表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody PlatformChannelEditParam platformChannelEditParam) {
		PlatformChannelEntity platformChannel = platformChannelMapStruct.toEntity(platformChannelEditParam);
		return R.toRes(platformChannelService.updateById(platformChannel));
	}

	/**
	* 删除平台渠道表
	*/
	@ApiOperation("删除平台渠道表")
	@ApiImplicitParam(name = "ids", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/basics/platformChannel',  'basics:platformChannel:delete')")
	@EventLog(message = "删除平台渠道表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(platformChannelService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 导出平台渠道表
	*/
	@ApiOperation("导出平台渠道表")
	@PreAuthorize("hasPermission('/basics/platformChannel',  'basics:platformChannel:export')")
	@SneakyThrows
	@EventLog(message = "导出平台渠道表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(PlatformChannelPageQueryParam param, HttpServletResponse response) {
		List<PlatformChannelEntity> list = platformChannelService.list(this.getQueryWrapper(platformChannelMapStruct, param));
		List<PlatformChannelVo> platformChannelVoList = platformChannelMapStruct.toVoList(list);
		ExcelUtils.exportExcel(platformChannelVoList, PlatformChannelVo.class, "平台渠道表", new ExportParams(), response);
	}

}
