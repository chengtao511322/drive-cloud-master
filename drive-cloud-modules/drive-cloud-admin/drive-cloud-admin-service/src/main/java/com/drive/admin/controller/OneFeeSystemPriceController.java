package com.drive.admin.controller;

import com.drive.admin.pojo.dto.OneFeeSystemPriceEditParam;
import com.drive.admin.pojo.dto.OneFeeSystemPricePageQueryParam;
import com.drive.admin.pojo.dto.TreeNodeCategoryDto;
import com.drive.admin.pojo.entity.OneFeeSystemPriceEntity;
import com.drive.admin.repository.OneFeeSystemPriceRepository;
import com.drive.admin.service.OneFeeSystemPriceService;
import com.drive.admin.service.mapstruct.OneFeeSystemPriceMapStruct;
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
 * 学车一费制定价表管理
 *
 * @author xiaoguo
 */
@Api(tags = "学车一费制定价表管理")
@Slf4j
@RestController
@RequestMapping("/oneFeeSystemPrice")
public class OneFeeSystemPriceController extends BaseController<OneFeeSystemPricePageQueryParam, OneFeeSystemPriceEntity> {

	@Autowired
	private OneFeeSystemPriceService oneFeeSystemPriceService;
	@Autowired
	private OneFeeSystemPriceRepository oneFeeSystemPriceRepository;
	@Autowired
	private OneFeeSystemPriceMapStruct oneFeeSystemPriceMapStruct;

	/**
	* 学车一费制定价表 分页列表
	*/
	@ApiOperation("学车一费制定价表分页列表")
	//@PreAuthorize("hasPermission('/admin/oneFeeSystemPrice',  'admin:oneFeeSystemPrice:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody OneFeeSystemPricePageQueryParam param) {
		return oneFeeSystemPriceRepository.pageList(param);
	}
	/**
	* 学车一费制定价表 分页列表
	*/
	@ApiOperation("学车一费制定价表列表")
	//@PreAuthorize("hasPermission('/admin/oneFeeSystemPrice',  'admin:oneFeeSystemPrice:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody OneFeeSystemPricePageQueryParam param) {
		return oneFeeSystemPriceRepository.findList(param);
	}

	/**
	* 获取学车一费制定价表
	*/
	@ApiOperation("获取学车一费制定价表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/oneFeeSystemPrice',  'admin:oneFeeSystemPrice:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return oneFeeSystemPriceRepository.getById(id);
	}


	@ApiOperation("获取学车一费制定价树形结构")
	@ApiImplicitParam(name = "tenantId", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/oneFeeSystemPrice',  'admin:oneFeeSystemPrice:query')")
	@PostMapping("/getServicePackageTree")
	ResObject<List<TreeNodeCategoryDto>> getServicePackageTree(@RequestBody OneFeeSystemPriceEditParam oneFeeSystemPriceEditParam){
		return oneFeeSystemPriceRepository.getServicePackageTree(oneFeeSystemPriceEditParam.getOperatorId());
	}
	/**
	* 新增学车一费制定价表
	*/
	@ApiOperation("新增学车一费制定价表")
	@ApiImplicitParam(name = "OneFeeSystemPriceEditParam ", value = "新增学车一费制定价表", dataType = "OneFeeSystemPriceEditParam")
	//@PreAuthorize("hasPermission('/admin/oneFeeSystemPrice',  'admin:oneFeeSystemPrice:add')")
	@EventLog(message = "新增学车一费制定价表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody OneFeeSystemPriceEditParam oneFeeSystemPriceEditParam) {
		return oneFeeSystemPriceRepository.save(oneFeeSystemPriceEditParam);
	}

	/**
	* 修改学车一费制定价表
	*/
	@ApiOperation("修改学车一费制定价表")
	@ApiImplicitParam(name = "OneFeeSystemPriceEditParam ", value = "修改学车一费制定价表", dataType = "OneFeeSystemPriceEditParam")
	//@PreAuthorize("hasPermission('/admin/oneFeeSystemPrice',  'admin:oneFeeSystemPrice:edit')")
	@EventLog(message = "修改学车一费制定价表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody OneFeeSystemPriceEditParam oneFeeSystemPriceEditParam) {
		return oneFeeSystemPriceRepository.update(oneFeeSystemPriceEditParam);
	}

	/**
	 *发布服务包
	 * @return
	 */
	@ApiOperation("发布学车一费制定价表")
	@ApiImplicitParam(name = "OneFeeSystemPriceEditParam ", value = "发布学车一费制定价表", dataType = "OneFeeSystemPriceEditParam")
	//@PreAuthorize("hasPermission('/admin/oneFeeSystemPrice',  'admin:oneFeeSystemPrice:publishServicePackage')")
	@EventLog(message = "修改学车一费制定价表", businessType = EventLogEnum.CREATE)
	@PostMapping("/publishServicePackage")
	ResObject publishServicePackage(@Valid @RequestBody OneFeeSystemPriceEditParam oneFeeSystemPriceEditParam){
		return oneFeeSystemPriceRepository.publishServicePackage(oneFeeSystemPriceEditParam);
	}

	/**
	 * 修改服务包
	 * @param oneFeeSystemPriceEditParam
	 * @return
	 */
	@ApiOperation("修改学车一费制定价表")
	@ApiImplicitParam(name = "OneFeeSystemPriceEditParam ", value = "修改学车一费制定价表", dataType = "OneFeeSystemPriceEditParam")
	//@PreAuthorize("hasPermission('/admin/oneFeeSystemPrice',  'admin:oneFeeSystemPrice:add')")
	@EventLog(message = "修改学车一费制定价表", businessType = EventLogEnum.UPDATE)
	@PostMapping("/updateServicePackage")
	ResObject updateServicePackage(@Valid @RequestBody OneFeeSystemPriceEditParam oneFeeSystemPriceEditParam){
		return oneFeeSystemPriceRepository.updateServicePackage(oneFeeSystemPriceEditParam);
	}


	@ApiOperation("复制学车一费制定价表")
	@ApiImplicitParam(name = "OneFeeSystemPriceEditParam ", value = "发布学车一费制定价表", dataType = "OneFeeSystemPriceEditParam")
	//@PreAuthorize("hasPermission('/admin/oneFeeSystemPrice',  'admin:oneFeeSystemPrice:copyServicePackage')")
	@EventLog(message = "复制学车一费制定价表", businessType = EventLogEnum.CREATE)
	@PostMapping("/copyServicePackage")
	ResObject copyServicePackage(@Valid @RequestBody OneFeeSystemPriceEditParam oneFeeSystemPriceEditParam){
		return oneFeeSystemPriceRepository.copyServicePackage(oneFeeSystemPriceEditParam);
	}
	/**
	* 删除学车一费制定价表
	*/
	@ApiOperation("删除学车一费制定价表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/oneFeeSystemPrice',  'admin:oneFeeSystemPrice:delete')")
	@EventLog(message = "删除学车一费制定价表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable String[] ids) {
		return R.toRes(oneFeeSystemPriceService.removeByIds(Arrays.asList(ids)));
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
		return oneFeeSystemPriceRepository.deleteById(id);
	}

	/**
	* 导出学车一费制定价表
	*/
	@ApiOperation("导出学车一费制定价表")
	//@PreAuthorize("hasPermission('/admin/oneFeeSystemPrice',  'admin:oneFeeSystemPrice:export')")
	@SneakyThrows
	@EventLog(message = "导出学车一费制定价表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody OneFeeSystemPricePageQueryParam param, HttpServletResponse response) {
			oneFeeSystemPriceRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用学车一费制定价表")
	//@PreAuthorize("hasPermission('/admin/oneFeeSystemPrice',  'admin:oneFeeSystemPrice:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用学车一费制定价表", businessType = EventLogEnum.UPDATE)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody OneFeeSystemPriceEditParam oneFeeSystemPriceEditParam) {
		return oneFeeSystemPriceRepository.changeStatus(oneFeeSystemPriceEditParam);
	}

}
