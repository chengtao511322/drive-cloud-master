package com.drive.marketing.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.data.utils.ExcelUtils;
import com.drive.common.log.annotation.EventLog;
import com.drive.common.redis.service.RedisService;
import com.drive.marketing.pojo.dto.ActivityProjectSettingEditParam;
import com.drive.marketing.pojo.dto.ActivityProjectSettingPageQueryParam;
import com.drive.marketing.pojo.entity.ActivityCouponRelationEntity;
import com.drive.marketing.pojo.entity.ActivityProjectSettingEntity;
import com.drive.marketing.pojo.vo.ActivityProjectSettingVo;
import com.drive.marketing.service.ActivityCouponRelationService;
import com.drive.marketing.service.ActivityProjectSettingService;
import com.drive.marketing.service.mapstruct.ActivityProjectSettingMapStruct;
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
 * 活动项目设置管理
 *
 * @author xiaoguo
 */
@Api(tags = "活动项目设置管理")
@Slf4j
@RestController
@RequestMapping("/activityProjectSetting")
public class ActivityProjectSettingController extends BaseController<ActivityProjectSettingPageQueryParam, ActivityProjectSettingEntity> {

	@Autowired
	private ActivityProjectSettingService activityProjectSettingService;
	@Autowired
	private ActivityProjectSettingMapStruct activityProjectSettingMapStruct;

	@Autowired
	private ActivityCouponRelationService activityCouponRelationService;

	@Autowired
	private RedisService redisService;

	/**
	* 活动项目设置 分页列表
	*/
	@ApiOperation("活动项目设置分页列表")
	//@PreAuthorize("hasPermission('/marketing/activityProjectSetting',  'marketing:activityProjectSetting:query')")
	@GetMapping(value = "/pageList")
	public ResObject pageList(@Valid ActivityProjectSettingPageQueryParam param) {

		Page<ActivityProjectSettingEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
		IPage<ActivityProjectSettingEntity> pageList = activityProjectSettingService.page(page, this.getQueryWrapper(activityProjectSettingMapStruct, param));
		Page<ActivityProjectSettingVo> activityProjectSettingVoPage = activityProjectSettingMapStruct.toVoList(pageList);
		/*activityProjectSettingVoPage.getRecords().forEach((item) ->{
			if (StrUtil.isNotEmpty(item.getTenantId())){
				redisService.getStr(item.getTenantId());
			}
		});*/
		return R.success(activityProjectSettingVoPage);
	}
	@ApiOperation("活动项目设置分页列表")
	//@PreAuthorize("hasPermission('/marketing/activityProjectSetting',  'marketing:activityProjectSetting:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody ActivityProjectSettingPageQueryParam param) {
		QueryWrapper<ActivityProjectSettingEntity> queryWrapper = new QueryWrapper<ActivityProjectSettingEntity>();
		queryWrapper.eq("activity_id",param.getActivityId());
		List<ActivityProjectSettingEntity> activityProjectSettingEntities = activityProjectSettingService.list(queryWrapper);
		List<ActivityProjectSettingVo> activityProjectSettingVoList = BeanConvertUtils.copyList(activityProjectSettingEntities,ActivityProjectSettingVo.class);
		/*if (activityProjectSettingVoList == null){
			return
		}*/
		activityProjectSettingVoList.forEach((item) ->{
			QueryWrapper<ActivityCouponRelationEntity> queryWrapperActivityCouponRelation = new QueryWrapper<ActivityCouponRelationEntity>();
			queryWrapperActivityCouponRelation.eq("activity_id",param.getActivityId());
			queryWrapperActivityCouponRelation.eq("project_id",item.getProjectId());
			ActivityCouponRelationEntity activityCouponRelation = activityCouponRelationService.getOne(queryWrapperActivityCouponRelation);
			if (activityCouponRelation!=null && StrUtil.isNotEmpty(activityCouponRelation.getCouponId()))item.setCouponId(activityCouponRelation.getCouponId());
			if (activityCouponRelation!=null && StrUtil.isNotEmpty(activityCouponRelation.getCouponName()))item.setCouponName(activityCouponRelation.getCouponName());
		});
		return R.success(activityProjectSettingVoList);
	}

	/**
	* 获取活动项目设置
	*/
	@ApiOperation("获取活动项目设置")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/marketing/activityProjectSetting',  'marketing:activityProjectSetting:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable Long id) {
		ActivityProjectSettingEntity activityProjectSetting = activityProjectSettingService.getById(id);
		return R.success(activityProjectSettingMapStruct.toVo(activityProjectSetting));
	}

	/**
	* 新增活动项目设置
	*/
	@ApiOperation("新增活动项目设置")
	@ApiImplicitParam(name = "ActivityProjectSettingEditParam ", value = "新增活动项目设置", dataType = "ActivityProjectSettingEditParam")
	//@PreAuthorize("hasPermission('/marketing/activityProjectSetting',  'marketing:activityProjectSetting:add')")
	@EventLog(message = "新增活动项目设置", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody ActivityProjectSettingEditParam activityProjectSettingEditParam) {
		ActivityProjectSettingEntity activityProjectSetting = activityProjectSettingMapStruct.toEntity(activityProjectSettingEditParam);
		QueryWrapper<ActivityProjectSettingEntity> queryWrapper = new QueryWrapper<ActivityProjectSettingEntity>();
		queryWrapper.eq("activity_id",activityProjectSettingEditParam.getActivityId());
		queryWrapper.eq("project_id",activityProjectSettingEditParam.getProjectId());
		int result = activityProjectSettingService.count(queryWrapper);
		if (result > 0){
			return R.failure("不可重复添加");
		}
		return R.toRes(activityProjectSettingService.save(activityProjectSetting));
	}

	/**
	* 修改活动项目设置
	*/
	@ApiOperation("修改活动项目设置")
	@ApiImplicitParam(name = "ActivityProjectSettingEditParam ", value = "修改活动项目设置", dataType = "ActivityProjectSettingEditParam")
	//@PreAuthorize("hasPermission('/marketing/activityProjectSetting',  'marketing:activityProjectSetting:edit')")
	@EventLog(message = "修改活动项目设置", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody ActivityProjectSettingEditParam activityProjectSettingEditParam) {
		ActivityProjectSettingEntity activityProjectSetting = activityProjectSettingMapStruct.toEntity(activityProjectSettingEditParam);
		return R.toRes(activityProjectSettingService.updateById(activityProjectSetting));
	}

	/**
	* 删除活动项目设置
	*/
	@ApiOperation("删除活动项目设置")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/marketing/activityProjectSetting',  'marketing:activityProjectSetting:delete')")
	@EventLog(message = "删除活动项目设置", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(activityProjectSettingService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 导出活动项目设置
	*/
	@ApiOperation("导出活动项目设置")
	//@PreAuthorize("hasPermission('/marketing/activityProjectSetting',  'marketing:activityProjectSetting:export')")
	@SneakyThrows
	@EventLog(message = "导出活动项目设置", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(ActivityProjectSettingPageQueryParam param, HttpServletResponse response) {
		List<ActivityProjectSettingEntity> list = activityProjectSettingService.list(this.getQueryWrapper(activityProjectSettingMapStruct, param));
		List<ActivityProjectSettingVo> activityProjectSettingVoList = activityProjectSettingMapStruct.toVoList(list);
		ExcelUtils.exportExcel(activityProjectSettingVoList, ActivityProjectSettingVo.class, "活动项目设置", new ExportParams(), response);
	}

}
