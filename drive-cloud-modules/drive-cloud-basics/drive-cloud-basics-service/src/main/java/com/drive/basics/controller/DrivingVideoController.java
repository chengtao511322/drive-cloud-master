package com.drive.basics.controller;

import com.drive.basics.pojo.dto.DrivingVideoEditParam;
import com.drive.basics.pojo.dto.DrivingVideoPageQueryParam;
import com.drive.basics.pojo.entity.DrivingVideoEntity;
import com.drive.basics.repository.DrivingVideoRepository;
import com.drive.basics.service.DrivingVideoService;
import com.drive.basics.service.mapstruct.DrivingVideoMapStruct;
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
 * 学车视频表管理
 *
 * @author xiaoguo
 */
@Api(tags = "学车视频表管理")
@Slf4j
@RestController
@RequestMapping("/drivingVideo")
public class DrivingVideoController extends BaseController<DrivingVideoPageQueryParam, DrivingVideoEntity> {

	@Autowired
	private DrivingVideoService drivingVideoService;
	@Autowired
	private DrivingVideoRepository drivingVideoRepository;
	@Autowired
	private DrivingVideoMapStruct drivingVideoMapStruct;

	/**
	* 学车视频表 分页列表
	*/
	@ApiOperation("学车视频表分页列表")
	//@PreAuthorize("hasPermission('/basics/drivingVideo',  'basics:drivingVideo:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody DrivingVideoPageQueryParam param) {
		return drivingVideoRepository.pageList(param);
	}
	/**
	* 学车视频表 分页列表
	*/
	@ApiOperation("学车视频表列表")
	//@PreAuthorize("hasPermission('/basics/drivingVideo',  'basics:drivingVideo:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody DrivingVideoPageQueryParam param) {
		return drivingVideoRepository.findList(param);
	}

	/**
	* 获取学车视频表
	*/
	@ApiOperation("获取学车视频表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/basics/drivingVideo',  'basics:drivingVideo:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return drivingVideoRepository.getById(id);
	}

	/**
	* 新增学车视频表
	*/
	@ApiOperation("新增学车视频表")
	@ApiImplicitParam(name = "DrivingVideoEditParam ", value = "新增学车视频表", dataType = "DrivingVideoEditParam")
	//@PreAuthorize("hasPermission('/basics/drivingVideo',  'basics:drivingVideo:add')")
	@EventLog(message = "新增学车视频表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody DrivingVideoEditParam drivingVideoEditParam) {
		return drivingVideoRepository.save(drivingVideoEditParam);
	}

	/**
	* 修改学车视频表
	*/
	@ApiOperation("修改学车视频表")
	@ApiImplicitParam(name = "DrivingVideoEditParam ", value = "修改学车视频表", dataType = "DrivingVideoEditParam")
	//@PreAuthorize("hasPermission('/basics/drivingVideo',  'basics:drivingVideo:edit')")
	@EventLog(message = "修改学车视频表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody DrivingVideoEditParam drivingVideoEditParam) {
		return drivingVideoRepository.update(drivingVideoEditParam);
	}

	/**
	* 删除学车视频表
	*/
	@ApiOperation("删除学车视频表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/basics/drivingVideo',  'basics:drivingVideo:delete')")
	@EventLog(message = "删除学车视频表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable String[] ids) {
		return R.toRes(drivingVideoService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除学车视频表
	*/
	@ApiOperation("通过主键删除学车视频表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除学车视频表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return drivingVideoRepository.deleteById(id);
	}

	/**
	* 导出学车视频表
	*/
	@ApiOperation("导出学车视频表")
	//@PreAuthorize("hasPermission('/basics/drivingVideo',  'basics:drivingVideo:export')")
	@SneakyThrows
	@EventLog(message = "导出学车视频表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody DrivingVideoPageQueryParam param, HttpServletResponse response) {
		drivingVideoRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用学车视频表")
	//@PreAuthorize("hasPermission('/basics/drivingVideo',  'basics:drivingVideo:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用学车视频表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody DrivingVideoEditParam drivingVideoEditParam) {
		return drivingVideoRepository.changeStatus(drivingVideoEditParam);
	}

}
