package com.drive.basics.controller;

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
import com.drive.basics.pojo.entity.*;
import com.drive.basics.pojo.vo.*;
import com.drive.basics.pojo.dto.*;
import com.drive.basics.service.mapstruct.*;
import com.drive.basics.service.ChannelAuthService;
import com.drive.basics.repository.ChannelAuthRepository;


/**
 * 菜单 按钮 用户拥有权限管理管理
 *
 * @author xiaoguo
 */
@Api(tags = "菜单 按钮 用户拥有权限管理管理")
@Slf4j
@RestController
@RequestMapping("/channelAuth")
public class ChannelAuthController extends BaseController<ChannelAuthPageQueryParam, ChannelAuthEntity> {

	// 菜单 按钮 用户拥有权限管理 服务
	@Autowired
	private ChannelAuthService channelAuthService;
	// 菜单 按钮 用户拥有权限管理 业务服务
	@Autowired
	private ChannelAuthRepository channelAuthRepository;
	// 菜单 按钮 用户拥有权限管理 DO-DTO转化
	@Autowired
	private ChannelAuthMapStruct channelAuthMapStruct;

	/**
	* 菜单 按钮 用户拥有权限管理 分页列表
	*/
	@ApiOperation("菜单 按钮 用户拥有权限管理分页列表")
	@PreAuthorize("hasPermission('/basics/channelAuth',  'basics:channelAuth:query')")
	@GetMapping(value = "/pageList")
	public ResObject pageList(@Valid ChannelAuthPageQueryParam param) {
		return channelAuthRepository.pageList(param);
	}
	/**
	* 菜单 按钮 用户拥有权限管理 列表
	*/
	@ApiOperation("菜单 按钮 用户拥有权限管理列表")
	@PreAuthorize("hasPermission('/basics/channelAuth',  'basics:channelAuth:query')")
	@GetMapping(value = "/findList")
	public ResObject findList(@Valid ChannelAuthPageQueryParam param) {
		return channelAuthRepository.findList(param);
	}

	/**
	* 获取菜单 按钮 用户拥有权限管理
	*/
	@ApiOperation("获取菜单 按钮 用户拥有权限管理")
	@ApiImplicitParam(name = "channelId", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/basics/channelAuth',  'basics:channelAuth:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return channelAuthRepository.getById(id);
	}

	/**
	 * 条件查询获取菜单 按钮 用户拥有权限管理
	 */
	@ApiOperation("条件查询获取菜单 按钮 用户拥有权限管理")
	@ApiImplicitParam(name = "channelId", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/basics/channelAuth',  'basics:channelAuth:query')")
	@GetMapping("/getInfo")
	public ResObject getInfo(@PathVariable ChannelAuthPageQueryParam param) {
		return channelAuthRepository.getInfo(param);
	}

	@ApiOperation("复制用户权限")
	@ApiImplicitParam(name = "channelId", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/basics/channelAuth',  'basics:channelAuth:copyChannelAuth')")
	@PostMapping("/copyChannelAuth")
	ResObject copyChannelAuth(@RequestBody ChannelAuthEditParam channelAuthEditParam){
		return channelAuthRepository.copyChannelAuth(channelAuthEditParam);
	}

	/**
	* 新增菜单 按钮 用户拥有权限管理
	*/
	@ApiOperation("新增菜单 按钮 用户拥有权限管理")
	@ApiImplicitParam(name = "ChannelAuthEditParam ", value = "新增菜单 按钮 用户拥有权限管理", dataType = "ChannelAuthEditParam")
	@PreAuthorize("hasPermission('/basics/channelAuth',  'basics:channelAuth:add')")
	@EventLog(message = "新增菜单 按钮 用户拥有权限管理", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody ChannelAuthInstallParam channelAuthInstallParam) {
		return channelAuthRepository.save(channelAuthInstallParam);
	}

	/**
	* 修改菜单 按钮 用户拥有权限管理
	*/
	@ApiOperation("修改菜单 按钮 用户拥有权限管理")
	@ApiImplicitParam(name = "ChannelAuthEditParam ", value = "修改菜单 按钮 用户拥有权限管理", dataType = "ChannelAuthEditParam")
	@PreAuthorize("hasPermission('/basics/channelAuth',  'basics:channelAuth:edit')")
	@EventLog(message = "修改菜单 按钮 用户拥有权限管理", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody ChannelAuthEditParam channelAuthEditParam) {
		return channelAuthRepository.update(channelAuthEditParam);
	}


	@ApiOperation("修改菜单按钮用户拥有权限管理")
	@ApiImplicitParam(name = "ChannelAuthEditParam ", value = "修改菜单 按钮 用户拥有权限管理", dataType = "ChannelAuthEditParam")
	@PreAuthorize("hasPermission('/basics/channelAuth',  'basics:channelAuth:updateChannelAuth')")
	@EventLog(message = "修改菜单 按钮 用户拥有权限管理", businessType = EventLogEnum.UPDATE)
	@PutMapping("updateChannelAuth")
	public ResObject updateChannelAuth(@Valid @RequestBody ChannelAuthEditParam channelAuthEditParam) {
		return channelAuthRepository.updateChannelAuth(channelAuthEditParam);
	}

	/**
	* 删除菜单 按钮 用户拥有权限管理
	*/
	@ApiOperation("删除菜单 按钮 用户拥有权限管理")
	@ApiImplicitParam(name = "channelId", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/basics/channelAuth',  'basics:channelAuth:delete')")
	@EventLog(message = "删除菜单 按钮 用户拥有权限管理", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{channelIds}")
	public ResObject delete(@PathVariable Long[] channelIds) {
		return R.toRes(channelAuthService.removeByIds(Arrays.asList(channelIds)));
	}

	/**
	* 通过主键删除菜单 按钮 用户拥有权限管理
	*/
	@ApiOperation("通过主键删除菜单 按钮 用户拥有权限管理")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除菜单 按钮 用户拥有权限管理", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return channelAuthRepository.deleteById(id);
	}

	/**
	* 导出菜单 按钮 用户拥有权限管理
	*/
	@ApiOperation("导出菜单 按钮 用户拥有权限管理")
	@PreAuthorize("hasPermission('/basics/channelAuth',  'basics:channelAuth:export')")
	@SneakyThrows
	@EventLog(message = "导出菜单 按钮 用户拥有权限管理", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(ChannelAuthPageQueryParam param, HttpServletResponse response) {
		channelAuthRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用菜单 按钮 用户拥有权限管理")
	@PreAuthorize("hasPermission('/basics/channelAuth',  'basics:channelAuth:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用菜单 按钮 用户拥有权限管理", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody ChannelAuthEditParam channelAuthEditParam) {
		return channelAuthRepository.changeStatus(channelAuthEditParam);
	}

}
