package com.drive.admin.controller;

import cn.hutool.core.util.StrUtil;
import com.drive.admin.pojo.dto.EvaluateTagAppraiseEditParam;
import com.drive.admin.pojo.dto.EvaluateTagAppraiseInstallParam;
import com.drive.admin.pojo.dto.EvaluateTagAppraisePageQueryParam;
import com.drive.admin.pojo.entity.EvaluateTagAppraiseEntity;
import com.drive.admin.repository.EvaluateTagAppraiseRepository;
import com.drive.admin.service.EvaluateTagAppraiseService;
import com.drive.admin.service.mapstruct.EvaluateTagAppraiseMapStruct;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
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
 * 学员教练评价明细表管理
 *
 * @author xiaoguo
 */
@Api(tags = "学员教练评价明细表管理")
@Slf4j
@RestController
@RequestMapping("/evaluateTagAppraise")
public class EvaluateTagAppraiseController extends BaseController<EvaluateTagAppraisePageQueryParam, EvaluateTagAppraiseEntity> {

	// 学员教练评价明细表 服务
	@Autowired
	private EvaluateTagAppraiseService evaluateTagAppraiseService;
	// 学员教练评价明细表 业务服务
	@Autowired
	private EvaluateTagAppraiseRepository evaluateTagAppraiseRepository;
	// 学员教练评价明细表 DO-DTO转化
	@Autowired
	private EvaluateTagAppraiseMapStruct evaluateTagAppraiseMapStruct;

	/**
	* 学员教练评价明细表 分页列表
	*/
	@ApiOperation("学员教练评价明细表分页列表")
	//@PreAuthorize("hasPermission('/admin/evaluateTagAppraise',  'admin:evaluateTagAppraise:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody EvaluateTagAppraisePageQueryParam param) {
		return evaluateTagAppraiseRepository.pageList(param);
	}
	@ApiOperation("通过评论ID获取学员教练评价明细表列表")
	//@PreAuthorize("hasPermission('/admin/evaluateTagAppraise',  'admin:evaluateTagAppraise:query')")
	@GetMapping(value = "/findEvaluateTagAById/{commentId}")
	public ResObject findEvaluateTagAById(@PathVariable String commentId) {
		if (StrUtil.isEmpty(commentId)){
			return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
		}
		EvaluateTagAppraisePageQueryParam pageQueryParam = new EvaluateTagAppraisePageQueryParam();
		pageQueryParam.setStudentCoachAppraiseId(commentId);
		return evaluateTagAppraiseRepository.findList(pageQueryParam);
	}
	/**
	* 学员教练评价明细表 列表
	*/
	@ApiOperation("学员教练评价明细表列表")
	//@PreAuthorize("hasPermission('/admin/evaluateTagAppraise',  'admin:evaluateTagAppraise:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody EvaluateTagAppraisePageQueryParam param) {
		return evaluateTagAppraiseRepository.findList(param);
	}

	/**
	* 获取学员教练评价明细表
	*/
	@ApiOperation("获取学员教练评价明细表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/evaluateTagAppraise',  'admin:evaluateTagAppraise:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return evaluateTagAppraiseRepository.getById(id);
	}

	/**
	 * 条件查询获取学员教练评价明细表
	 */
	@ApiOperation("条件查询获取学员教练评价明细表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/evaluateTagAppraise',  'admin:evaluateTagAppraise:query')")
	@PostMapping("/getInfo")
	public ResObject getInfo(@PathVariable @RequestBody EvaluateTagAppraisePageQueryParam param) {
		return evaluateTagAppraiseRepository.getInfo(param);
	}

	/**
	* 新增学员教练评价明细表
	*/
	@ApiOperation("新增学员教练评价明细表")
	@ApiImplicitParam(name = "EvaluateTagAppraiseEditParam ", value = "新增学员教练评价明细表", dataType = "EvaluateTagAppraiseEditParam")
	//@PreAuthorize("hasPermission('/admin/evaluateTagAppraise',  'admin:evaluateTagAppraise:add')")
	@EventLog(message = "新增学员教练评价明细表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody EvaluateTagAppraiseInstallParam evaluateTagAppraiseInstallParam) {
		return evaluateTagAppraiseRepository.save(evaluateTagAppraiseInstallParam);
	}

	/**
	* 修改学员教练评价明细表
	*/
	@ApiOperation("修改学员教练评价明细表")
	@ApiImplicitParam(name = "EvaluateTagAppraiseEditParam ", value = "修改学员教练评价明细表", dataType = "EvaluateTagAppraiseEditParam")
	//@PreAuthorize("hasPermission('/admin/evaluateTagAppraise',  'admin:evaluateTagAppraise:edit')")
	@EventLog(message = "修改学员教练评价明细表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody EvaluateTagAppraiseEditParam evaluateTagAppraiseEditParam) {
		return evaluateTagAppraiseRepository.update(evaluateTagAppraiseEditParam);
	}

	/**
	* 删除学员教练评价明细表
	*/
	@ApiOperation("删除学员教练评价明细表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/evaluateTagAppraise',  'admin:evaluateTagAppraise:delete')")
	@EventLog(message = "删除学员教练评价明细表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable String[] ids) {
		return R.toRes(evaluateTagAppraiseService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除学员教练评价明细表
	*/
	@ApiOperation("通过主键删除学员教练评价明细表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除学员教练评价明细表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return evaluateTagAppraiseRepository.deleteById(id);
	}

	/**
	* 导出学员教练评价明细表
	*/
	@ApiOperation("导出学员教练评价明细表")
	//@PreAuthorize("hasPermission('/admin/evaluateTagAppraise',  'admin:evaluateTagAppraise:export')")
	@SneakyThrows
	@EventLog(message = "导出学员教练评价明细表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody EvaluateTagAppraisePageQueryParam param, HttpServletResponse response) {
		evaluateTagAppraiseRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用学员教练评价明细表")
	//@PreAuthorize("hasPermission('/admin/evaluateTagAppraise',  'admin:evaluateTagAppraise:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用学员教练评价明细表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody EvaluateTagAppraiseEditParam evaluateTagAppraiseEditParam) {
		return evaluateTagAppraiseRepository.changeStatus(evaluateTagAppraiseEditParam);
	}

}
