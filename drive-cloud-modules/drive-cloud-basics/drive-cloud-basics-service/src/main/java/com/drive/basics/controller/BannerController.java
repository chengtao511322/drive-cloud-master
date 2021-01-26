package com.drive.basics.controller;

import com.drive.basics.pojo.dto.BannerEditParam;
import com.drive.basics.pojo.dto.BannerPageQueryParam;
import com.drive.basics.pojo.entity.BannerEntity;
import com.drive.basics.repository.BannerRepository;
import com.drive.basics.service.BannerService;
import com.drive.basics.service.mapstruct.BannerMapStruct;
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
 * banner 轮播图管理
 *
 * @author xiaoguo
 */
@Api(tags = "banner 轮播图管理")
@Slf4j
@RestController
@RequestMapping("/banner")
public class BannerController extends BaseController<BannerPageQueryParam, BannerEntity> {

	@Autowired
	private BannerService bannerService;
	@Autowired
	private BannerRepository bannerRepository;
	@Autowired
	private BannerMapStruct bannerMapStruct;

	/**
	* banner 轮播图 分页列表
	*/
	@ApiOperation("banner 轮播图分页列表")
	@PreAuthorize("hasPermission('/basics/banner',  'basics:banner:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid BannerPageQueryParam param) {
		return bannerRepository.pageList(param);
	}
	/**
	* banner 轮播图 分页列表
	*/
	@ApiOperation("banner 轮播图列表")
	@PreAuthorize("hasPermission('/basics/banner',  'basics:banner:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid BannerPageQueryParam param) {
		return bannerRepository.findList(param);
	}

	/**
	* 获取banner 轮播图
	*/
	@ApiOperation("获取banner 轮播图")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/basics/banner',  'basics:banner:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		return bannerRepository.getInfo(id);
	}

	/**
	* 新增banner 轮播图
	*/
	@ApiOperation("新增banner 轮播图")
	@ApiImplicitParam(name = "BannerEditParam ", value = "新增banner 轮播图", dataType = "BannerEditParam")
	@PreAuthorize("hasPermission('/basics/banner',  'basics:banner:add')")
	@EventLog(message = "新增banner 轮播图", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody BannerEditParam bannerEditParam) {
		return bannerRepository.save(bannerEditParam);
	}

	/**
	* 修改banner 轮播图
	*/
	@ApiOperation("修改banner 轮播图")
	@ApiImplicitParam(name = "BannerEditParam ", value = "修改banner 轮播图", dataType = "BannerEditParam")
	@PreAuthorize("hasPermission('/basics/banner',  'basics:banner:edit')")
	@EventLog(message = "修改banner 轮播图", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody BannerEditParam bannerEditParam) {
		return bannerRepository.update(bannerEditParam);
	}

	/**
	* 删除banner 轮播图
	*/
	@ApiOperation("删除banner 轮播图")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/basics/banner',  'basics:banner:delete')")
	@EventLog(message = "删除banner 轮播图", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(bannerService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 通过主键删除banner 轮播图
	*/
	@ApiOperation("通过主键删除banner 轮播图")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delById')")
	@EventLog(message = "通过主键删除banner 轮播图", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return bannerRepository.deleteById(id);
	}

	/**
	* 导出banner 轮播图
	*/
	@ApiOperation("导出banner 轮播图")
	@PreAuthorize("hasPermission('/basics/banner',  'basics:banner:export')")
	@SneakyThrows
	@EventLog(message = "导出banner 轮播图", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(BannerPageQueryParam param, HttpServletResponse response) {
		bannerRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用banner 轮播图")
	@PreAuthorize("hasPermission('/basics/banner',  'basics:banner:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用banner 轮播图", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody BannerEditParam bannerEditParam) {
		return bannerRepository.changeStatus(bannerEditParam);
	}

}
