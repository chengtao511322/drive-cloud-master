package com.drive.admin.controller;

import com.drive.admin.pojo.dto.AreaEditParam;
import com.drive.admin.pojo.dto.AreaPageQueryParam;
import com.drive.admin.pojo.entity.AreaEntity;
import com.drive.admin.repository.AreaRepository;
import com.drive.admin.service.AreaService;
import com.drive.admin.service.mapstruct.AreaMapStruct;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.log.annotation.EventLog;
import com.drive.system.api.RemoteUserService;
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
 * 城市区域管理
 *
 * @author xiaoguo
 */
@Api(tags = "城市区域管理")
@Slf4j
@RestController
@RequestMapping("/area")
public class AreaController extends BaseController<AreaPageQueryParam, AreaEntity> {



	@Autowired
	private AreaService areaService;
	@Autowired
	private AreaRepository areaRepository;
	@Autowired
	private AreaMapStruct areaMapStruct;

	/**
	*  分页列表
	*/
	@ApiOperation("城市区域分页列表")
	//@PreAuthorize("hasPermission('/admin/area',  'admin:area:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody AreaPageQueryParam param) {
		return areaRepository.pageList(param);
	}
	/**
	*  列表
	*/
	@ApiOperation("城市区域列表")
	//@PreAuthorize("hasPermission('/admin/area',  'admin:area:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody AreaPageQueryParam param) {
		return areaRepository.findList(param);
	}

	/**
	 * 部门列表
	 */
	@ApiOperation("城市区域列表")
	//@PreAuthorize("hasPermission('/admin/area',  'admin:area:allList')")
	@GetMapping(value = "/allList")
	public ResObject allList() {
		return areaRepository.allList();
	}


	/**
	* 获取
	*/
	@ApiOperation("城市区域通过ID获取")
	@ApiImplicitParam(name = "baCode", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/area',  'admin:area:query')")
	@GetMapping("/{baCode}")
	public ResObject get(@PathVariable String baCode) {
		return areaRepository.getById(baCode);
	}

	/**
	* 新增
	*/
	@ApiOperation("城市区域新增")
	@ApiImplicitParam(name = "AreaEditParam ", value = "新增", dataType = "AreaEditParam")
	//@PreAuthorize("hasPermission('/admin/area',  'admin:area:add')")
	@EventLog(message = "新增", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody AreaEditParam areaEditParam) {
		return areaRepository.save(areaEditParam);
	}

	/**
	* 修改
	*/
	@ApiOperation("城市区域修改")
	@ApiImplicitParam(name = "AreaEditParam ", value = "修改", dataType = "AreaEditParam")
	//@PreAuthorize("hasPermission('/admin/area',  'admin:area:edit')")
	@EventLog(message = "修改", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody AreaEditParam areaEditParam) {
		return areaRepository.update(areaEditParam);
	}

	/**
	* 删除
	*/
	@ApiOperation("城市区域删除")
	@ApiImplicitParam(name = "baCode", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/area',  'admin:area:delete')")
	@EventLog(message = "删除", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{baCodes}")
	public ResObject delete(@PathVariable String[] baCodes) {
		return R.toRes(areaService.removeByIds(Arrays.asList(baCodes)));
	}

	/**
	* 通过主键删除
	*/
	@ApiOperation("通过主键删除城市区域")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return areaRepository.delAreaByCode(id);
	}

	/**
	* 导出
	*/
	@ApiOperation("城市区域导出")
	//@PreAuthorize("hasPermission('/admin/area',  'admin:area:export')")
	@SneakyThrows
	@EventLog(message = "导出", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody  AreaPageQueryParam param, HttpServletResponse response) {
		areaRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("城市区域状态启用/停用")
	//@PreAuthorize("hasPermission('/admin/area',  'admin:area:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody AreaEditParam areaEditParam) {
		return areaRepository.changeStatus(areaEditParam);
	}

	/**
	 * 查询运营商可选择城市区域列表
	 * @return
	 */
	@ApiOperation("可选择城市区域列表(包含当前传入的运营商id下的区域)")
	@GetMapping(value = "/allOptionalAreaList/{operatorId}")
	public ResObject allOptionalAreaList(@PathVariable String operatorId){
		return areaRepository.allOptionalAreaList(operatorId);
	}

	/**
	 * 查询运营商可选择城市区域列表
	 * @return
	 */
	@ApiOperation("可选择城市区域列表")
	@GetMapping(value = "/allOptionalAreaList")
	public ResObject getAllOptionalAreaList(){
		return areaRepository.allOptionalAreaList(null);
	}

	/**
	 * 通过运营商id查询当前运营商区域
	 */
	@ApiOperation("只获取该运营商下的区域")
	@GetMapping(value = "/allOptionalAreaListWithOperatorId/{operatorId}")
	public ResObject getOptionalAreaListByOptionalId(@PathVariable String operatorId){
		return areaRepository.getOptionalAreaById(operatorId);
	}


}
