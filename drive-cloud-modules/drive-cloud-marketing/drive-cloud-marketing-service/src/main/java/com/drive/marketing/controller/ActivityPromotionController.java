package com.drive.marketing.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.fastjson.JSONObject;
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
import com.drive.marketing.pojo.dto.ActivityPromotionEditParam;
import com.drive.marketing.pojo.dto.ActivityPromotionPageQueryParam;
import com.drive.marketing.pojo.dto.ChannelManagerActivityEditParam;
import com.drive.marketing.pojo.entity.ActivityProjectSettingEntity;
import com.drive.marketing.pojo.entity.ActivityPromotionEntity;
import com.drive.marketing.pojo.vo.ActivityPromotionVo;
import com.drive.marketing.repository.ChannelManagerActivityRepository;
import com.drive.marketing.repository.RecommendManagertRepository;
import com.drive.marketing.service.ActivityProjectSettingService;
import com.drive.marketing.service.ActivityPromotionService;
import com.drive.marketing.service.mapstruct.ActivityPromotionMapStruct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


/**
 * 管理
 *
 * @author xiaoguo
 */
@Api(tags = "活动推广分润提成管理")
@Slf4j
@RestController
@RequestMapping("/activityPromotion")
public class ActivityPromotionController extends BaseController<ActivityPromotionPageQueryParam, ActivityPromotionEntity> {

	@Autowired
	private ActivityPromotionService activityPromotionService;
	@Autowired
	private ActivityPromotionMapStruct activityPromotionMapStruct;

	@Autowired
	private RecommendManagertRepository recommendManagertRepository;

	@Autowired
	private ActivityProjectSettingService activityProjectSettingService;

	@Autowired
	private ChannelManagerActivityRepository channelManagerActivityRepository;

	/**
	*  分页列表
	*/
	@ApiOperation("分页列表")
	//@PreAuthorize("hasPermission('/marketing/activityPromotion',  'marketing:activityPromotion:query')")
	@GetMapping(value = "/pageList")
	public ResObject pageList(@Valid ActivityPromotionPageQueryParam param) {
		Page<ActivityPromotionEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
		IPage<ActivityPromotionEntity> pageList = activityPromotionService.page(page, this.getQueryWrapper(activityPromotionMapStruct, param));
		Page<ActivityPromotionVo> activityPromotionVoPage = activityPromotionMapStruct.toVoList(pageList);
		activityPromotionVoPage.getRecords().stream().forEach((item) ->{
			JSONObject channelManager = recommendManagertRepository.getRecommendUser(item.getPromotionUserId());
			if (channelManager != null)item.setJsonObject(channelManager);
		});
		return R.success(activityPromotionVoPage);
	}

	/**
	* 获取
	*/
	@ApiOperation("获取")
	@ApiImplicitParam(name = "activityId", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/marketing/activityPromotion',  'marketing:activityPromotion:query')")
	@GetMapping("/{activityId}")
	public ResObject get(@PathVariable String activityId) {
		ActivityPromotionEntity activityPromotion = activityPromotionService.getById(activityId);
		return R.success(activityPromotionMapStruct.toVo(activityPromotion));
	}

	/**
	* 新增
	*/
	@ApiOperation("新增")
	@ApiImplicitParam(name = "ActivityPromotionEditParam ", value = "新增", dataType = "ActivityPromotionEditParam")
	//@PreAuthorize("hasPermission('/marketing/activityPromotion',  'marketing:activityPromotion:add')")
	@EventLog(message = "新增", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody ActivityPromotionEditParam activityPromotionEditParam) {
		ActivityPromotionEntity activityPromotion = activityPromotionMapStruct.toEntity(activityPromotionEditParam);
		return R.toRes(activityPromotionService.save(activityPromotion));
	}
	/**
	* 新增
	*/
	@ApiOperation("添加推广人员")
	@ApiImplicitParam(name = "addActivityPromotion ", value = "添加推广人员", dataType = "ActivityPromotionEditParam")
	//@PreAuthorize("hasPermission('/marketing/addActivityPromotion',  'marketing:activityPromotion:add')")
	@EventLog(message = "添加推广人员", businessType = EventLogEnum.CREATE)
	@PostMapping("/addActivityPromotion")
	public ResObject addActivityPromotion(@RequestBody List<ChannelManagerActivityEditParam> channelManagerActivityEditParamList) {
		/*QueryWrapper<ActivityProjectSettingEntity> wrapper = new QueryWrapper<ActivityProjectSettingEntity>();
		activityProjectSettingService.list();*/
		return channelManagerActivityRepository.addActivityPromotion(channelManagerActivityEditParamList);
	}



	/**
	 * 批量新增渠道经理 可推广表配置
	 */
	@ApiOperation("批量新增渠道经理 可推广表配置")
	@ApiImplicitParam(name = "ChannelManagerActivityEditParam ", value = "新增渠道经理 可推广表配置", dataType = "ChannelManagerActivityEditParam")
	//@PreAuthorize("hasPermission('/marketing/channelManagerActivity',  'marketing:channelManagerActivity:add')")
	@EventLog(message = "批量新增渠道经理 可推广表配置", businessType = EventLogEnum.CREATE)
	@PostMapping("saveBatch")
	@Transactional
	public ResObject saveBatch(@Valid @RequestBody List<ActivityPromotionEditParam> activityPromotionEditParams) {
		List<ActivityPromotionEntity> channelManagerActivity = BeanConvertUtils.copyList(activityPromotionEditParams,ActivityPromotionEntity.class);
		channelManagerActivity.stream().forEach((item) ->{
			QueryWrapper<ActivityProjectSettingEntity> wrapper = new QueryWrapper<ActivityProjectSettingEntity>();
			wrapper.eq("activity_id",item.getActivityId());
			List<ActivityProjectSettingEntity> activityProjectSettingList = activityProjectSettingService.list(wrapper);
			if (!activityProjectSettingList.isEmpty()){
				activityProjectSettingList.forEach((setting) ->{
					item.setDeductAmount(setting.getDeductAmount());
					item.setPromotionAmount(setting.getPromotionAmount());
					item.setChannelManagerAmount(setting.getChannelManagerAmount());
					item.setProjectId(setting.getProjectId());
				});
			}
		});
		return R.toRes(activityPromotionService.saveBatch(channelManagerActivity));
	}

	/**
	* 修改
	*/
	@ApiOperation("修改")
	@ApiImplicitParam(name = "ActivityPromotionEditParam ", value = "修改", dataType = "ActivityPromotionEditParam")
	//@PreAuthorize("hasPermission('/marketing/activityPromotion',  'marketing:activityPromotion:edit')")
	@EventLog(message = "修改", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody ActivityPromotionEditParam activityPromotionEditParam) {
		ActivityPromotionEntity activityPromotion = activityPromotionMapStruct.toEntity(activityPromotionEditParam);
		return R.toRes(activityPromotionService.updateById(activityPromotion));
	}

	/**
	* 删除
	*/
	@ApiOperation("删除")
	@ApiImplicitParam(name = "ids", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/marketing/activityPromotion',  'marketing:activityPromotion:delete')")
	@EventLog(message = "删除", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(activityPromotionService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 导出
	*/
	@ApiOperation("导出")
	//@PreAuthorize("hasPermission('/marketing/activityPromotion',  'marketing:activityPromotion:export')")
	@SneakyThrows
	@EventLog(message = "导出", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(ActivityPromotionPageQueryParam param, HttpServletResponse response) {
		List<ActivityPromotionEntity> list = activityPromotionService.list(this.getQueryWrapper(activityPromotionMapStruct, param));
		List<ActivityPromotionVo> activityPromotionVoList = activityPromotionMapStruct.toVoList(list);
		ExcelUtils.exportExcel(activityPromotionVoList, ActivityPromotionVo.class, "", new ExportParams(), response);
	}


	/**
	 * 删除渠道经理 可推广表配置
	 */
	@ApiOperation("删除推广配置")
	@ApiImplicitParam(name = "channelManagerId", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/marketing/promotionActivity',  'marketing:channelManagerActivity:delete')")
	@EventLog(message = "删除渠道经理 可推广表配置", businessType = EventLogEnum.DELETE)
	@PostMapping("/deleteByQueryWrapper")
	public ResObject deleteByQueryWrapper(@RequestBody ActivityPromotionEditParam param) {
		QueryWrapper<ActivityPromotionEntity> queryWrapper = new QueryWrapper<ActivityPromotionEntity>();
		queryWrapper.eq("promotion_user_id",param.getProjectId());
		queryWrapper.eq("activity_id",param.getActivityId());
		return R.toRes(activityPromotionService.remove(queryWrapper));
	}
}
