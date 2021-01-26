package com.drive.admin.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.pojo.dto.ServiceInfoEditParam;
import com.drive.admin.pojo.dto.ServiceInfoPageQueryParam;
import com.drive.admin.pojo.entity.ServiceInfoEntity;
import com.drive.admin.pojo.vo.ServiceInfoVo;
import com.drive.admin.repository.ServiceInfoRepository;
import com.drive.admin.service.ServiceInfoService;
import com.drive.admin.service.mapstruct.ServiceInfoMapStruct;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.data.utils.ExcelUtils;
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
import java.util.List;


/**
 * 客服人员信息表管理
 *
 * @author xiaoguo
 */
@Api(tags = "客服人员信息表管理")
@Slf4j
@RestController
@RequestMapping("/serviceInfo")
public class ServiceInfoController extends BaseController<ServiceInfoPageQueryParam, ServiceInfoEntity> {

	@Autowired
	private ServiceInfoService serviceInfoService;
	@Autowired
	private ServiceInfoMapStruct serviceInfoMapStruct;

	@Autowired
	private ServiceInfoRepository serviceInfoRepository;

	/**
	* 客服人员信息表 分页列表
	*/
	@ApiOperation("客服人员信息表分页列表")
	@PreAuthorize("hasPermission('/admin/serviceInfo',  'admin:serviceInfo:query')")
	@GetMapping(value = "/pageList")
	public ResObject pageList(@Valid ServiceInfoPageQueryParam param) {

		Page<ServiceInfoEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
		IPage<ServiceInfoEntity> pageList = serviceInfoService.page(page, this.getQueryWrapper(serviceInfoMapStruct, param));
		Page<ServiceInfoVo> serviceInfoVoPage = serviceInfoMapStruct.toVoList(pageList);
		return R.success(serviceInfoVoPage);
	}

	/**
	* 获取客服人员信息表
	*/
	@ApiOperation("获取客服人员信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/serviceInfo',  'admin:serviceInfo:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable Long id) {
		ServiceInfoEntity serviceInfo = serviceInfoService.getById(id);
		return R.success(serviceInfoMapStruct.toVo(serviceInfo));
	}

	/**
	* 新增客服人员信息表
	*/
	@ApiOperation("新增客服人员信息表")
	@ApiImplicitParam(name = "ServiceInfoEditParam ", value = "新增客服人员信息表", dataType = "ServiceInfoEditParam")
	@PreAuthorize("hasPermission('/admin/serviceInfo',  'admin:serviceInfo:add')")
	@EventLog(message = "新增客服人员信息表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody ServiceInfoEditParam serviceInfoEditParam) {
		ServiceInfoEntity serviceInfo = serviceInfoMapStruct.toEntity(serviceInfoEditParam);
		return R.toRes(serviceInfoService.save(serviceInfo));
	}

	/**
	* 修改客服人员信息表
	*/
	@ApiOperation("修改客服人员信息表")
	@ApiImplicitParam(name = "ServiceInfoEditParam ", value = "修改客服人员信息表", dataType = "ServiceInfoEditParam")
	@PreAuthorize("hasPermission('/admin/serviceInfo',  'admin:serviceInfo:edit')")
	@EventLog(message = "修改客服人员信息表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody ServiceInfoEditParam serviceInfoEditParam) {
		ServiceInfoEntity serviceInfo = serviceInfoMapStruct.toEntity(serviceInfoEditParam);
		return R.toRes(serviceInfoService.updateById(serviceInfo));
	}

	/**
	* 删除客服人员信息表
	*/
	@ApiOperation("删除客服人员信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/serviceInfo',  'admin:serviceInfo:delete')")
	@EventLog(message = "删除客服人员信息表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(serviceInfoService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 导出客服人员信息表
	*/
	@ApiOperation("导出客服人员信息表")
	@PreAuthorize("hasPermission('/admin/serviceInfo',  'admin:serviceInfo:export')")
	@SneakyThrows
	@EventLog(message = "导出客服人员信息表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(ServiceInfoPageQueryParam param, HttpServletResponse response) {
		List<ServiceInfoEntity> list = serviceInfoService.list(this.getQueryWrapper(serviceInfoMapStruct, param));
		List<ServiceInfoVo> serviceInfoVoList = serviceInfoMapStruct.toVoList(list);
		ExcelUtils.exportExcel(serviceInfoVoList, ServiceInfoVo.class, "客服人员信息表", new ExportParams(), response);
	}

}
