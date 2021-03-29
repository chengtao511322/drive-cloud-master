package com.drive.basics.controller;

import com.drive.basics.pojo.dto.ChannelEditParam;
import com.drive.basics.pojo.dto.ChannelPageQueryParam;
import com.drive.basics.pojo.entity.ChannelEntity;
import com.drive.basics.repository.ChannelRepository;
import com.drive.basics.service.ChannelService;
import com.drive.basics.service.mapstruct.ChannelMapStruct;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.enums.EventLogEnum;
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


/**
 * 栏目管理
 *
 * @author xiaoguo
 */
@Api(tags = "栏目管理")
@Slf4j
@RestController
@RequestMapping("/channel")
public class ChannelController extends BaseController<ChannelPageQueryParam, ChannelEntity> {

	@Autowired
	private ChannelService channelService;
	@Autowired
	private ChannelRepository channelRepository;
	@Autowired
	private ChannelMapStruct channelMapStruct;

	/**
	* 栏目 分页列表
	*/
	@ApiOperation("栏目分页列表")
	@PreAuthorize("hasPermission('/basics/channel',  'basics:channel:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid ChannelPageQueryParam param) {
		return channelRepository.pageList(param);
	}
	/**
	* 栏目 分页列表
	*/
	@ApiOperation("栏目列表")
	@PreAuthorize("hasPermission('/basics/channel',  'basics:channel:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid ChannelPageQueryParam param) {
		return channelRepository.findList(param);
	}

	/**
	* 获取栏目
	*/
	@ApiOperation("获取栏目")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/basics/channel',  'basics:channel:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return channelRepository.getById(id);
	}


	/**
	 * 通过 parentId 查询项目
	 * @param parentId
	 * @return
	 */
	@ApiOperation("ParentId获取栏目")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/basics/channel',  'basics:channel:query')")
	@GetMapping("/getChannelByParentId/{parentId}")
	ResObject getChannelByParentId(@PathVariable String parentId){
		return channelRepository.getChannelByParentId(parentId);
	}
	/**
	* 新增栏目
	*/
	@ApiOperation("新增栏目")
	@ApiImplicitParam(name = "ChannelEditParam ", value = "新增栏目", dataType = "ChannelEditParam")
	@PreAuthorize("hasPermission('/basics/channel',  'basics:channel:add')")
	@EventLog(message = "新增栏目", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody ChannelEditParam channelEditParam) {
		return channelRepository.save(channelEditParam);
	}

	/**
	* 修改栏目
	*/
	@ApiOperation("修改栏目")
	@ApiImplicitParam(name = "ChannelEditParam ", value = "修改栏目", dataType = "ChannelEditParam")
	@PreAuthorize("hasPermission('/basics/channel',  'basics:channel:edit')")
	@EventLog(message = "修改栏目", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@RequestBody ChannelEditParam channelEditParam) {
		return channelRepository.update(channelEditParam);
	}
	/**
	* 修改栏目
	*/
	@ApiOperation("修改栏目")
	@ApiImplicitParam(name = "updateChannel ", value = "修改栏目", dataType = "updateChannel")
	//@PreAuthorize("hasPermission('/basics/channel',  'basics:channel:edit')")
	@EventLog(message = "修改栏目", businessType = EventLogEnum.UPDATE)
	@PostMapping("/updateChannel")
	public ResObject updateChannel(@RequestBody ChannelEditParam channelEditParam) {
		return channelRepository.updateChannel(channelEditParam);
	}
	@ApiOperation("栏目移动制定")
	@ApiImplicitParam(name = "updateChannel ", value = "修改栏目", dataType = "updateChannel")
	//@PreAuthorize("hasPermission('/basics/channel',  'basics:channel:edit')")
	@EventLog(message = "栏目移动制定", businessType = EventLogEnum.UPDATE)
	@PostMapping("/move")
	ResObject move(@RequestBody  ChannelEditParam channelEditParam)  {
		return channelRepository.move(channelEditParam);
	}

	/**
	* 删除栏目
	*/
	@ApiOperation("删除栏目")
	@ApiImplicitParam(name = "ids", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/basics/channel',  'basics:channel:delete')")
	@EventLog(message = "删除栏目", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable String[] ids) {
		return R.toRes(channelService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除教练信息表
	*/
	@ApiOperation("删除教练信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delete')")
	@EventLog(message = "删除教练信息表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return channelRepository.deleteById(id);
	}

	/**
	* 导出栏目
	*/
	@ApiOperation("导出栏目")
	@PreAuthorize("hasPermission('/basics/channel',  'basics:channel:export')")
	@SneakyThrows
	@EventLog(message = "导出栏目", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(ChannelPageQueryParam param, HttpServletResponse response) {
			channelRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用栏目")
	@PreAuthorize("hasPermission('/basics/channel',  'basics:channel:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用栏目", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody ChannelEditParam channelEditParam) {
		return channelRepository.changeStatus(channelEditParam);
	}


	/**
	 * 部门列表
	 */
	@ApiOperation("运营商列表")
	@PreAuthorize("hasPermission('/operator',  'basics:operator:allList')")
	@GetMapping(value = "/allList")
	public ResObject allList(ChannelEditParam channelEditParam) {
		return channelRepository.allList(channelEditParam);
	}


	@ApiOperation("活动父项目")
	@PreAuthorize("hasPermission('/operator',  'basics:operator:allList')")
	@GetMapping(value = "/getParentList")
	public ResObject getParentList() {
		return channelRepository.getParentList();
	}

}
