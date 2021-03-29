package com.drive.admin.controller;

import com.drive.admin.pojo.dto.CoachHourSettingDetailEditParam;
import com.drive.admin.pojo.dto.CoachHourSettingEditParam;
import com.drive.admin.pojo.dto.CoachHourSettingPageQueryParam;
import com.drive.admin.pojo.entity.CoachHourSettingEntity;
import com.drive.admin.repository.CoachHourSettingRepository;
import com.drive.admin.service.CoachHourSettingService;
import com.drive.admin.service.mapstruct.CoachHourSettingMapStruct;
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
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.List;


/**
 * 教练发课设置管理
 *
 * @author xiaoguo
 */
@Api(tags = "教练发课设置管理")
@Slf4j
@RestController
@RequestMapping("/coachHourSetting")
public class CoachHourSettingController extends BaseController<CoachHourSettingPageQueryParam, CoachHourSettingEntity> {

	// 教练发课设置 服务
	@Autowired
	private CoachHourSettingService coachHourSettingService;
	// 教练发课设置 业务服务
	@Autowired
	private CoachHourSettingRepository coachHourSettingRepository;
	// 教练发课设置 DO-DTO转化
	@Autowired
	private CoachHourSettingMapStruct coachHourSettingMapStruct;

	/**
	* 教练发课设置 分页列表
	*/
	@ApiOperation("教练发课设置分页列表")
	@PreAuthorize("hasPermission('/admin/coachHourSetting',  'admin:coachHourSetting:query')")
	@GetMapping(value = "/pageList")
	public ResObject pageList(@Valid CoachHourSettingPageQueryParam param) {
		return coachHourSettingRepository.pageList(param);
	}
	/**
	* 教练发课设置 列表
	*/
	@ApiOperation("教练发课设置列表")
	@PreAuthorize("hasPermission('/admin/coachHourSetting',  'admin:coachHourSetting:query')")
	@GetMapping(value = "/findList")
	public ResObject findList(@Valid CoachHourSettingPageQueryParam param) {
		return coachHourSettingRepository.findList(param);
	}

	/**
	* 获取教练发课设置
	*/
	@ApiOperation("获取教练发课设置")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachHourSetting',  'admin:coachHourSetting:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return coachHourSettingRepository.getById(id);
	}

	@ApiOperation("通过运营商获取有效时间")
	@ApiImplicitParam(name = "operatorId", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachHourSetting',  'admin:coachHourSetting:query')")
	@GetMapping("/getEffectiveDateTime/{operatorId}")
	public ResObject getEffectiveDateTime(@PathVariable @NotBlank(message = "运营商ID不能为空") String operatorId) {
		return coachHourSettingRepository.getEffectiveDateTime(operatorId);
	}

	/**
	 * 条件查询获取教练发课设置
	 */
	@ApiOperation("条件查询获取教练发课设置")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachHourSetting',  'admin:coachHourSetting:query')")
	@GetMapping("/getInfo")
	public ResObject getInfo(@PathVariable CoachHourSettingPageQueryParam param) {
		return coachHourSettingRepository.getInfo(param);
	}

	/**
	* 新增教练发课设置
	*/
	@ApiOperation("新增教练发课设置")
	@ApiImplicitParam(name = "CoachHourSettingEditParam ", value = "新增教练发课设置", dataType = "CoachHourSettingEditParam")
	@PreAuthorize("hasPermission('/admin/coachHourSetting',  'admin:coachHourSetting:add')")
	@EventLog(message = "新增教练发课设置", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody CoachHourSettingEditParam coachHourSettingEditParam) {
		return coachHourSettingRepository.save(coachHourSettingEditParam);
	}

	@ApiOperation("发布教练发课设置")
	@ApiImplicitParam(name = "CoachHourSettingEditParam ", value = "新增教练发课设置", dataType = "CoachHourSettingEditParam")
	@PreAuthorize("hasPermission('/admin/coachHourSetting',  'admin:coachHourSetting:add')")
	@EventLog(message = "发布教练发课设置", businessType = EventLogEnum.CREATE)
	@PostMapping("/publish")
	public ResObject publish(@Valid @RequestBody CoachHourSettingEditParam coachHourSettingEditParam) {
		return coachHourSettingRepository.publish(coachHourSettingEditParam);
	}
	@ApiOperation("修改教练发课时")
	@ApiImplicitParam(name = "CoachHourSettingEditParam ", value = "修改教练发课时", dataType = "CoachHourSettingEditParam")
	@PreAuthorize("hasPermission('/admin/coachHourSetting',  'admin:coachHourSetting:add')")
	@EventLog(message = "修改教练发课时", businessType = EventLogEnum.UPDATE)
	@PostMapping("/updateCoachHourSettingDetail")
	public ResObject updateCoachHourSettingDetail(@Valid @RequestBody List<CoachHourSettingDetailEditParam> coachHourSettingDetailList) {
		return coachHourSettingRepository.updateCoachHourSettingDetail(coachHourSettingDetailList);
	}

	/**
	* 修改教练发课设置
	*/
	@ApiOperation("修改教练发课设置")
	@ApiImplicitParam(name = "CoachHourSettingEditParam ", value = "修改教练发课设置", dataType = "CoachHourSettingEditParam")
	@PreAuthorize("hasPermission('/admin/coachHourSetting',  'admin:coachHourSetting:edit')")
	@EventLog(message = "修改教练发课设置", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody CoachHourSettingEditParam coachHourSettingEditParam) {
		return coachHourSettingRepository.update(coachHourSettingEditParam);
	}

	/**
	* 删除教练发课设置
	*/
	@ApiOperation("删除教练发课设置")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachHourSetting',  'admin:coachHourSetting:delete')")
	@EventLog(message = "删除教练发课设置", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(coachHourSettingService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除教练发课设置
	*/
	@ApiOperation("通过主键删除教练发课设置")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除教练发课设置", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return coachHourSettingRepository.deleteById(id);
	}

	/**
	* 导出教练发课设置
	*/
	@ApiOperation("导出教练发课设置")
	@PreAuthorize("hasPermission('/admin/coachHourSetting',  'admin:coachHourSetting:export')")
	@SneakyThrows
	@EventLog(message = "导出教练发课设置", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(CoachHourSettingPageQueryParam param, HttpServletResponse response) {
		coachHourSettingRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用教练发课设置")
	@PreAuthorize("hasPermission('/admin/coachHourSetting',  'admin:coachHourSetting:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用教练发课设置", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody CoachHourSettingEditParam coachHourSettingEditParam) {
		return coachHourSettingRepository.changeStatus(coachHourSettingEditParam);
	}

}
