package com.drive.basics.controller;

import com.drive.basics.pojo.dto.CategoryEditParam;
import com.drive.basics.pojo.dto.CategoryPageQueryParam;
import com.drive.basics.pojo.entity.CategoryEntity;
import com.drive.basics.repository.CategoryRepository;
import com.drive.basics.service.CategoryService;
import com.drive.basics.service.mapstruct.CategoryMapStruct;
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
 * 产品分类表管理
 *
 * @author xiaoguo
 */
@Api(tags = "产品分类表管理")
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController<CategoryPageQueryParam, CategoryEntity> {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CategoryMapStruct categoryMapStruct;

	/**
	* 产品分类表 分页列表
	*/
	@ApiOperation("产品分类表分页列表")
	//@PreAuthorize("hasPermission('/basics/category',  'basics:category:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody CategoryPageQueryParam param) {
		return categoryRepository.pageList(param);
	}
	/**
	* 产品分类表 分页列表
	*/
	@ApiOperation("产品分类表列表")
	//@PreAuthorize("hasPermission('/basics/category',  'basics:category:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody CategoryPageQueryParam param) {
		return categoryRepository.findList(param);
	}

	/**
	* 获取产品分类表
	*/
	@ApiOperation("获取产品分类表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/basics/category',  'basics:category:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return categoryRepository.getById(id);
	}

	/**
	* 新增产品分类表
	*/
	@ApiOperation("新增产品分类表")
	@ApiImplicitParam(name = "CategoryEditParam ", value = "新增产品分类表", dataType = "CategoryEditParam")
	//@PreAuthorize("hasPermission('/basics/category',  'basics:category:add')")
	@EventLog(message = "新增产品分类表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody CategoryEditParam categoryEditParam) {
		return categoryRepository.save(categoryEditParam);
	}

	/**
	* 修改产品分类表
	*/
	@ApiOperation("修改产品分类表")
	@ApiImplicitParam(name = "CategoryEditParam ", value = "修改产品分类表", dataType = "CategoryEditParam")
	//@PreAuthorize("hasPermission('/basics/category',  'basics:category:edit')")
	@EventLog(message = "修改产品分类表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody CategoryEditParam categoryEditParam) {
		return categoryRepository.update(categoryEditParam);
	}

	/**
	* 删除产品分类表
	*/
	@ApiOperation("删除产品分类表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/basics/category',  'basics:category:delete')")
	@EventLog(message = "删除产品分类表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable String[] ids) {
		return R.toRes(categoryService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除产品分类表
	*/
	@ApiOperation("通过主键删除产品分类表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除产品分类表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return categoryRepository.deleteById(id);
	}

	/**
	* 导出产品分类表
	*/
	@ApiOperation("导出产品分类表")
	//@PreAuthorize("hasPermission('/basics/category',  'basics:category:export')")
	@SneakyThrows
	@EventLog(message = "导出产品分类表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody CategoryPageQueryParam param, HttpServletResponse response) {
		categoryRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用产品分类表")
	//@PreAuthorize("hasPermission('/basics/category',  'basics:category:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用产品分类表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody CategoryEditParam categoryEditParam) {
		return categoryRepository.changeStatus(categoryEditParam);
	}

	/**
	 * 获取产品分类表
	 */
	@ApiOperation("获取产品分类表")
	@ApiImplicitParam(name = "type", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/basics/category',  'basics:category:query')")
	@PostMapping("/getCategoryByType")
	public ResObject getCategoryByType(@RequestBody CategoryPageQueryParam categoryPageQueryParam) {
		return categoryRepository.getCategoryByType(categoryPageQueryParam.getDictType(),categoryPageQueryParam.getTenantId());
	}

}
