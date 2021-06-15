package com.drive.bbs.controller;

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
import com.drive.bbs.pojo.entity.*;
import com.drive.bbs.pojo.vo.*;
import com.drive.bbs.pojo.dto.*;
import com.drive.bbs.service.mapstruct.*;
import com.drive.bbs.service.UserLikeService;
import com.drive.bbs.repository.UserLikeRepository;


/**
 * 用户点赞表管理
 *
 * @author xiaoguo
 */
@Api(tags = "用户点赞表管理")
@Slf4j
@RestController
@RequestMapping("/userLike")
public class UserLikeController extends BaseController<UserLikePageQueryParam, UserLikeEntity> {

	// 用户点赞表 服务
	@Autowired
	private UserLikeService userLikeService;
	// 用户点赞表 业务服务
	@Autowired
	private UserLikeRepository userLikeRepository;
	// 用户点赞表 DO-DTO转化
	@Autowired
	private UserLikeMapStruct userLikeMapStruct;

	/**
	* 用户点赞表 分页列表
	*/
	@ApiOperation("用户点赞表分页列表")
	@PreAuthorize("hasPermission('/bbs/userLike',  'bbs:userLike:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody UserLikePageQueryParam param) {
		return userLikeRepository.pageList(param);
	}
	/**
	* 用户点赞表 列表
	*/
	@ApiOperation("用户点赞表列表")
	@PreAuthorize("hasPermission('/bbs/userLike',  'bbs:userLike:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody UserLikePageQueryParam param) {
		return userLikeRepository.findList(param);
	}

	/**
	* 获取用户点赞表
	*/
	@ApiOperation("获取用户点赞表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/bbs/userLike',  'bbs:userLike:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return userLikeRepository.getById(id);
	}

	/**
	 * 条件查询获取用户点赞表
	 */
	@ApiOperation("条件查询获取用户点赞表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/bbs/userLike',  'bbs:userLike:query')")
	@PostMapping("/getInfo")
	public ResObject getInfo(@RequestBody UserLikePageQueryParam param) {
		return userLikeRepository.getInfo(param);
	}

	/**
	* 新增用户点赞表
	 * author:xiaoguo
	*/
	@ApiOperation("新增用户点赞表")
	@ApiImplicitParam(name = "UserLikeEditParam ", value = "新增用户点赞表", dataType = "UserLikeEditParam")
	@PreAuthorize("hasPermission('/bbs/userLike',  'bbs:userLike:add')")
	@EventLog(message = "新增用户点赞表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody UserLikeInstallParam userLikeInstallParam) {
		return userLikeRepository.save(userLikeInstallParam);
	}

	/**
	* 修改用户点赞表
	*/
	@ApiOperation("修改用户点赞表")
	@ApiImplicitParam(name = "UserLikeEditParam ", value = "修改用户点赞表", dataType = "UserLikeEditParam")
	@PreAuthorize("hasPermission('/bbs/userLike',  'bbs:userLike:edit')")
	@EventLog(message = "修改用户点赞表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody UserLikeEditParam userLikeEditParam) {
		return userLikeRepository.update(userLikeEditParam);
	}

	/**
	* 删除用户点赞表
	*/
	@ApiOperation("删除用户点赞表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/bbs/userLike',  'bbs:userLike:delete')")
	@EventLog(message = "删除用户点赞表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable String[] ids) {
		return userLikeRepository.deleteByIds(ids);
	}

	/**
	* 通过主键删除用户点赞表
	*/
	@ApiOperation("通过主键删除用户点赞表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delete')")
	@EventLog(message = "通过主键删除用户点赞表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return userLikeRepository.deleteById(id);
	}

	/**
	* 导出用户点赞表
	*/
	@ApiOperation("导出用户点赞表")
	@PreAuthorize("hasPermission('/bbs/userLike',  'bbs:userLike:export')")
	@SneakyThrows
	@EventLog(message = "导出用户点赞表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody UserLikePageQueryParam param, HttpServletResponse response) {
		userLikeRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用用户点赞表")
	@PreAuthorize("hasPermission('/bbs/userLike',  'bbs:userLike:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用用户点赞表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody UserLikeEditParam userLikeEditParam) {
		return userLikeRepository.changeStatus(userLikeEditParam);
	}

}
