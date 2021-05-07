package com.drive.admin.controller;

import com.drive.admin.pojo.dto.RecommendUserEditParam;
import com.drive.admin.pojo.dto.RecommendUserPageQueryParam;
import com.drive.admin.pojo.entity.RecommendUserEntity;
import com.drive.admin.pojo.vo.RecommendUserVo;
import com.drive.admin.repository.RecommendUserRepository;
import com.drive.admin.service.RecommendUserService;
import com.drive.admin.service.mapstruct.RecommendUserMapStruct;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


/**
 * 推广人员信息表管理
 *
 * @author xiaoguo
 */
@Api(tags = "推广人员信息表管理")
@Slf4j
@RestController
@RequestMapping("/recommendUser")
public class RecommendUserController extends BaseController<RecommendUserPageQueryParam, RecommendUserEntity> {

	@Autowired
	private RecommendUserService recommendUserService;
	@Autowired
	private RecommendUserRepository recommendUserRepository;
	@Autowired
	private RecommendUserMapStruct recommendUserMapStruct;

	/**
	* 推广人员信息表 分页列表
	*/
	@ApiOperation("推广人员信息表分页列表")
	//@PreAuthorize("hasPermission('/admin/recommendUser',  'admin:recommendUser:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid RecommendUserPageQueryParam param) {
		return recommendUserRepository.pageList(param);
	}
	/**
	* 推广人员信息表 列表
	*/
	@ApiOperation("推广人员信息表列表")
	//@PreAuthorize("hasPermission('/admin/recommendUser',  'admin:recommendUser:query')")
	@PostMapping(value = "/findList")
	public<RecommendUserVo> ResObject findList(@Valid RecommendUserPageQueryParam param) {
		return recommendUserRepository.findList(param);
	}
	@ApiOperation("通过渠道经理ID获取推广人员信息表列表")
	//@PreAuthorize("hasPermission('/admin/recommendUser',  'admin:recommendUser:getRecommendUserByChannelManagerId')")
	@PostMapping(value = "/getRecommendUserByChannelManagerId/{channelManagerId}")
	ResObject<List<RecommendUserVo>> getRecommendUserByChannelManagerId(@PathVariable(value = "channelManagerId") String channelManagerId) {
		RecommendUserPageQueryParam param = new RecommendUserPageQueryParam();
		param.setManagerId(channelManagerId);
		return recommendUserRepository.findList(param);
	}

	/**
	* 获取推广人员信息表
	*/
	@ApiOperation("获取推广人员信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/recommendUser',  'admin:recommendUser:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return recommendUserRepository.getById(id);
	}

	/**
	* 新增推广人员信息表
	*/
	@ApiOperation("新增推广人员信息表")
	@ApiImplicitParam(name = "RecommendUserEditParam ", value = "新增推广人员信息表", dataType = "RecommendUserEditParam")
	//@PreAuthorize("hasPermission('/admin/recommendUser',  'admin:recommendUser:add')")
	@EventLog(message = "新增推广人员信息表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody RecommendUserEditParam recommendUserEditParam) {
		return recommendUserRepository.save(recommendUserEditParam);
	}

	/**
	* 修改推广人员信息表
	*/
	@ApiOperation("修改推广人员信息表")
	@ApiImplicitParam(name = "RecommendUserEditParam ", value = "修改推广人员信息表", dataType = "RecommendUserEditParam")
	//@PreAuthorize("hasPermission('/admin/recommendUser',  'admin:recommendUser:edit')")
	@EventLog(message = "修改推广人员信息表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody RecommendUserEditParam recommendUserEditParam) {
		return recommendUserRepository.update(recommendUserEditParam);
	}

	/**
	* 删除推广人员信息表
	*/
	@ApiOperation("删除推广人员信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/recommendUser',  'admin:recommendUser:delete')")
	@EventLog(message = "删除推广人员信息表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(recommendUserService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除推广人员信息表
	*/
	@ApiOperation("通过主键删除推广人员信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除推广人员信息表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return recommendUserRepository.deleteById(id);
	}

	/**
	* 导出推广人员信息表
	*/
	@ApiOperation("导出推广人员信息表")
	//@PreAuthorize("hasPermission('/admin/recommendUser',  'admin:recommendUser:export')")
	@SneakyThrows
	@EventLog(message = "导出推广人员信息表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(RecommendUserPageQueryParam param, HttpServletResponse response) {
		recommendUserRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用推广人员信息表")
	//@PreAuthorize("hasPermission('/admin/recommendUser',  'admin:recommendUser:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用推广人员信息表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody RecommendUserEditParam recommendUserEditParam) {
		return recommendUserRepository.changeStatus(recommendUserEditParam);
	}

}
