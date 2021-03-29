package com.drive.admin.controller;

import com.drive.admin.pojo.dto.CoachHourSettingDetailEditParam;
import com.drive.admin.pojo.dto.CoachHourSettingDetailPageQueryParam;
import com.drive.admin.pojo.entity.CoachHourSettingDetailEntity;
import com.drive.admin.repository.CoachHourSettingDetailRepository;
import com.drive.admin.service.CoachHourSettingDetailService;
import com.drive.admin.service.mapstruct.CoachHourSettingDetailMapStruct;
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
 * 运营商教练课时设置表管理
 *
 * @author xiaoguo
 */
@Api(tags = "运营商教练课时设置表管理")
@Slf4j
@RestController
@RequestMapping("/coachHourSettingDetail")
public class CoachHourSettingDetailController extends BaseController<CoachHourSettingDetailPageQueryParam, CoachHourSettingDetailEntity> {

	// 运营商教练课时设置表 服务
	@Autowired
	private CoachHourSettingDetailService coachHourSettingDetailService;
	// 运营商教练课时设置表 业务服务
	@Autowired
	private CoachHourSettingDetailRepository coachHourSettingDetailRepository;
	// 运营商教练课时设置表 DO-DTO转化
	@Autowired
	private CoachHourSettingDetailMapStruct coachHourSettingDetailMapStruct;

	/**
	* 运营商教练课时设置表 分页列表
	*/
	@ApiOperation("运营商教练课时设置表分页列表")
	@PreAuthorize("hasPermission('/admin/coachHourSettingDetail',  'admin:coachHourSettingDetail:query')")
	@GetMapping(value = "/pageList")
	public ResObject pageList(@Valid CoachHourSettingDetailPageQueryParam param) {
		return coachHourSettingDetailRepository.pageList(param);
	}
	/**
	* 运营商教练课时设置表 列表
	*/
	@ApiOperation("运营商教练课时设置表列表")
	@PreAuthorize("hasPermission('/admin/coachHourSettingDetail',  'admin:coachHourSettingDetail:query')")
	@GetMapping(value = "/findList")
	public ResObject findList(@Valid CoachHourSettingDetailPageQueryParam param) {
		return coachHourSettingDetailRepository.findList(param);
	}

	/**
	* 获取运营商教练课时设置表
	*/
	@ApiOperation("获取运营商教练课时设置表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachHourSettingDetail',  'admin:coachHourSettingDetail:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return coachHourSettingDetailRepository.getById(id);
	}

	/**
	 * 条件查询获取运营商教练课时设置表
	 */
	@ApiOperation("条件查询获取运营商教练课时设置表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachHourSettingDetail',  'admin:coachHourSettingDetail:query')")
	@GetMapping("/getInfo")
	public ResObject getInfo(@PathVariable CoachHourSettingDetailPageQueryParam param) {
		return coachHourSettingDetailRepository.getInfo(param);
	}

	/**
	* 新增运营商教练课时设置表
	*/
	@ApiOperation("新增运营商教练课时设置表")
	@ApiImplicitParam(name = "CoachHourSettingDetailEditParam ", value = "新增运营商教练课时设置表", dataType = "CoachHourSettingDetailEditParam")
	@PreAuthorize("hasPermission('/admin/coachHourSettingDetail',  'admin:coachHourSettingDetail:add')")
	@EventLog(message = "新增运营商教练课时设置表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody CoachHourSettingDetailEditParam coachHourSettingDetailEditParam) {
		return coachHourSettingDetailRepository.save(coachHourSettingDetailEditParam);
	}

	/**
	* 修改运营商教练课时设置表
	*/
	@ApiOperation("修改运营商教练课时设置表")
	@ApiImplicitParam(name = "CoachHourSettingDetailEditParam ", value = "修改运营商教练课时设置表", dataType = "CoachHourSettingDetailEditParam")
	@PreAuthorize("hasPermission('/admin/coachHourSettingDetail',  'admin:coachHourSettingDetail:edit')")
	@EventLog(message = "修改运营商教练课时设置表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody CoachHourSettingDetailEditParam coachHourSettingDetailEditParam) {
		return coachHourSettingDetailRepository.update(coachHourSettingDetailEditParam);
	}

	/**
	* 删除运营商教练课时设置表
	*/
	@ApiOperation("删除运营商教练课时设置表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachHourSettingDetail',  'admin:coachHourSettingDetail:delete')")
	@EventLog(message = "删除运营商教练课时设置表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(coachHourSettingDetailService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除运营商教练课时设置表
	*/
	@ApiOperation("通过主键删除运营商教练课时设置表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除运营商教练课时设置表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return coachHourSettingDetailRepository.deleteById(id);
	}

	/**
	* 导出运营商教练课时设置表
	*/
	@ApiOperation("导出运营商教练课时设置表")
	@PreAuthorize("hasPermission('/admin/coachHourSettingDetail',  'admin:coachHourSettingDetail:export')")
	@SneakyThrows
	@EventLog(message = "导出运营商教练课时设置表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(CoachHourSettingDetailPageQueryParam param, HttpServletResponse response) {
		coachHourSettingDetailRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用运营商教练课时设置表")
	@PreAuthorize("hasPermission('/admin/coachHourSettingDetail',  'admin:coachHourSettingDetail:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用运营商教练课时设置表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody CoachHourSettingDetailEditParam coachHourSettingDetailEditParam) {
		return coachHourSettingDetailRepository.changeStatus(coachHourSettingDetailEditParam);
	}

}
