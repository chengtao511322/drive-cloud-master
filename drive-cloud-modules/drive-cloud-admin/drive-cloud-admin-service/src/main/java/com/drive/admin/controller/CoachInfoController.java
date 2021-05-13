package com.drive.admin.controller;

import com.drive.admin.pojo.dto.CoachInfoDataEditParam;
import com.drive.admin.pojo.dto.CoachInfoEditParam;
import com.drive.admin.pojo.dto.CoachInfoInstallParam;
import com.drive.admin.pojo.dto.CoachInfoPageQueryParam;
import com.drive.admin.pojo.entity.CoachInfoEntity;
import com.drive.admin.repository.CoachInfoRepository;
import com.drive.admin.service.CoachInfoService;
import com.drive.admin.service.mapstruct.CoachInfoMapStruct;
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
 * 教练信息表管理
 *
 * @author xiaoguo
 */
@Api(tags = "教练信息表管理")
@Slf4j
@RestController
@RequestMapping("/coachInfo")
public class CoachInfoController extends BaseController<CoachInfoPageQueryParam, CoachInfoEntity> {

	@Autowired
	private CoachInfoService coachInfoService;
	@Autowired
	private CoachInfoRepository coachInfoRepository;
	@Autowired
	private CoachInfoMapStruct coachInfoMapStruct;

	/**
	* 教练信息表 分页列表
	*/
	@ApiOperation("教练信息表分页列表")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody CoachInfoPageQueryParam param) {
		return coachInfoRepository.pageList(param);
	}

	/**
	* 获取教练信息表
	*/
	@ApiOperation("获取教练信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return coachInfoRepository.getById(id);
	}

	/**
	* 新增教练信息表
	*/
	@ApiOperation("新增教练信息表")
	@ApiImplicitParam(name = "CoachInfoEditParam ", value = "新增教练信息表", dataType = "CoachInfoEditParam")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:add')")
	@EventLog(message = "新增教练信息表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody CoachInfoEditParam coachInfoEditParam) {
		return coachInfoRepository.save(coachInfoEditParam);
	}

	/**
	* 修改教练信息表
	*/
	@ApiOperation("修改教练信息表")
	@ApiImplicitParam(name = "CoachInfoEditParam ", value = "修改教练信息表", dataType = "CoachInfoEditParam")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:edit')")
	@EventLog(message = "修改教练信息表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody CoachInfoEditParam coachInfoEditParam) {
		return coachInfoRepository.update(coachInfoEditParam);
	}
	/**
	* 修改教练信息表 并且修改教练授课区域
	*/
	@ApiOperation("修改教练信息表")
	@ApiImplicitParam(name = "CoachInfoDataEditParam ", value = "修改教练信息表 并且修改教练授课区域", dataType = "CoachInfoDataEditParam")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:updateCoachInfo')")
	@EventLog(message = "修改教练信息表 并且修改教练授课区域", businessType = EventLogEnum.UPDATE)
	@PutMapping("/updateCoachInfo")
	public ResObject updateCoachInfo(@Valid @RequestBody CoachInfoDataEditParam coachInfoEditParam) {
		return coachInfoRepository.updateCoachInfo(coachInfoEditParam);
	}
	@ApiOperation("驳回教练信息表")
	@ApiImplicitParam(name = "CoachInfoDataEditParam ", value = "驳回教练信息表", dataType = "CoachInfoDataEditParam")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:updateCoachInfo')")
	@EventLog(message = "驳回教练信息表", businessType = EventLogEnum.UPDATE)
	@PostMapping("/rejectCoach")
	public ResObject rejectCoachInfo(@Valid @RequestBody CoachInfoInstallParam coachInfoEditParam) {
		return coachInfoRepository.rejectCoach(coachInfoEditParam);
	}

	/**
	* 删除教练信息表
	*/
	@ApiOperation("删除教练信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delete')")
	@EventLog(message = "删除教练信息表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable String[] ids) {
		return R.toRes(coachInfoService.removeByIds(Arrays.asList(ids)));
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
		return coachInfoRepository.deleteById(id);
	}

	/**
	* 导出教练信息表
	*/
	@ApiOperation("导出教练信息表")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:export')")
	@SneakyThrows
	@EventLog(message = "导出教练信息表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody CoachInfoPageQueryParam param, HttpServletResponse response) {
			coachInfoRepository.exportXls(param,response);
	}

}
