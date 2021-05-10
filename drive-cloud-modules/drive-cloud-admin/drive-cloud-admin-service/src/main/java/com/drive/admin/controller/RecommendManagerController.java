package com.drive.admin.controller;

import com.drive.admin.pojo.dto.RecommendManagerEditParam;
import com.drive.admin.pojo.dto.RecommendManagerPageQueryParam;
import com.drive.admin.pojo.entity.RecommendManagerEntity;
import com.drive.admin.repository.RecommendManagerRepository;
import com.drive.admin.service.RecommendManagerService;
import com.drive.admin.service.mapstruct.RecommendManagerMapStruct;
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


/**
 * 推广渠道经理管理
 *
 * @author xiaoguo
 */
@Api(tags = "推广渠道经理管理")
@Slf4j
@RestController
@RequestMapping("/recommendManager")
public class RecommendManagerController extends BaseController<RecommendManagerPageQueryParam, RecommendManagerEntity> {

	@Autowired
	private RecommendManagerService recommendManagerService;
	@Autowired
	private RecommendManagerRepository recommendManagerRepository;
	@Autowired
	private RecommendManagerMapStruct recommendManagerMapStruct;

	/**
	* 推广渠道经理 分页列表
	*/
	@ApiOperation("推广渠道经理分页列表")
	//@PreAuthorize("hasPermission('/admin/recommendManager',  'admin:recommendManager:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody RecommendManagerPageQueryParam param) {
		return recommendManagerRepository.pageList(param);
	}
	/**
	* 推广渠道经理 分页列表
	*/
	@ApiOperation("推广渠道经理列表")
	//@PreAuthorize("hasPermission('/admin/recommendManager',  'admin:recommendManager:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody RecommendManagerPageQueryParam param) {
		return recommendManagerRepository.findList(param);
	}

	/**
	* 获取推广渠道经理
	*/
	@ApiOperation("获取推广渠道经理")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/recommendManager',  'admin:recommendManager:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return recommendManagerRepository.getById(id);
	}

	/**
	* 新增推广渠道经理
	*/
	@ApiOperation("新增推广渠道经理")
	@ApiImplicitParam(name = "RecommendManagerEditParam ", value = "新增推广渠道经理", dataType = "RecommendManagerEditParam")
	//@PreAuthorize("hasPermission('/admin/recommendManager',  'admin:recommendManager:add')")
	@EventLog(message = "新增推广渠道经理", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody RecommendManagerEditParam recommendManagerEditParam) {
		return recommendManagerRepository.save(recommendManagerEditParam);
	}

	/**
	* 修改推广渠道经理
	*/
	@ApiOperation("修改推广渠道经理")
	@ApiImplicitParam(name = "RecommendManagerEditParam ", value = "修改推广渠道经理", dataType = "RecommendManagerEditParam")
	//@PreAuthorize("hasPermission('/admin/recommendManager',  'admin:recommendManager:edit')")
	@EventLog(message = "修改推广渠道经理", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody RecommendManagerEditParam recommendManagerEditParam) {
		return recommendManagerRepository.update(recommendManagerEditParam);
	}

	/**
	* 删除推广渠道经理
	*/
	@ApiOperation("删除推广渠道经理")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/recommendManager',  'admin:recommendManager:delete')")
	@EventLog(message = "删除推广渠道经理", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(recommendManagerService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除教练信息表
	*/
	@ApiOperation("删除教练信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delete')")
	@EventLog(message = "删除教练信息表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return recommendManagerRepository.deleteById(id);
	}

	/**
	* 导出推广渠道经理
	*/
	@ApiOperation("导出推广渠道经理")
	//@PreAuthorize("hasPermission('/admin/recommendManager',  'admin:recommendManager:export')")
	@SneakyThrows
	@EventLog(message = "导出推广渠道经理", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(RecommendManagerPageQueryParam param, HttpServletResponse response) {
			recommendManagerRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用推广渠道经理")
	//@PreAuthorize("hasPermission('/admin/recommendManager',  'admin:recommendManager:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用推广渠道经理", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody RecommendManagerEditParam recommendManagerEditParam) {
		return recommendManagerRepository.changeStatus(recommendManagerEditParam);
	}

}
