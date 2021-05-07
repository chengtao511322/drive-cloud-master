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
import com.drive.common.data.utils.ExcelUtils;
import com.drive.common.log.annotation.EventLog;
import com.drive.marketing.pojo.dto.ActivityProjectSettingEditParam;
import com.drive.marketing.pojo.dto.ChannelManagerActivityEditParam;
import com.drive.marketing.pojo.dto.ChannelManagerActivityPageQueryParam;
import com.drive.marketing.pojo.entity.ChannelManagerActivityEntity;
import com.drive.marketing.pojo.vo.ChannelManagerActivityVo;
import com.drive.marketing.repository.ActivityInfoRepository;
import com.drive.marketing.repository.ChannelManagerActivityRepository;
import com.drive.marketing.repository.RecommendManagertRepository;
import com.drive.marketing.service.ActivityPromotionService;
import com.drive.marketing.service.ChannelManagerActivityService;
import com.drive.marketing.service.mapstruct.ChannelManagerActivityMapStruct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * 渠道经理 可推广表配置管理
 *
 * @author xiaoguo
 */
@Api(tags = "渠道经理 可推广表配置管理")
@Slf4j
@RestController
@RequestMapping("/channelManagerActivity")
public class ChannelManagerActivityController extends BaseController<ChannelManagerActivityPageQueryParam, ChannelManagerActivityEntity> {

	@Autowired
	private ChannelManagerActivityService channelManagerActivityService;
	@Autowired
	private ChannelManagerActivityMapStruct channelManagerActivityMapStruct;

	@Autowired
	private ActivityPromotionService activityPromotionService;

	@Autowired
	private ActivityInfoRepository activityInfoRepository;


	@Autowired
	private ChannelManagerActivityRepository channelManagerActivityRepository;


	@Autowired
	private RecommendManagertRepository recommendManagertRepository;


	// @Autowired
	//private RemoteChannelFeignService remoteChannelFeignService;

	/**
	* 渠道经理 可推广表配置 分页列表
	*/
	@ApiOperation("渠道经理 可推广表配置分页列表")
	//@PreAuthorize("hasPermission('/marketing/channelManagerActivity',  'marketing:channelManagerActivity:query')")
	@GetMapping(value = "/pageList")
	public ResObject pageList(@Valid ChannelManagerActivityPageQueryParam param) {
		log.info(this.getClass() + "pageList 请求方法{}",param);
		Page<ChannelManagerActivityEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
		IPage<ChannelManagerActivityEntity> pageList = channelManagerActivityService.page(page, this.getQueryWrapper(channelManagerActivityMapStruct, param));
		Page<ChannelManagerActivityVo> channelManagerActivityVoPage = channelManagerActivityMapStruct.toVoList(pageList);
		/*channelManagerActivityVoPage.getRecords().forEach((item) ->{
			JSONObject jsonObject = recommendManagertRepository.getRecommendManagert(item.getChannelManagerId());
		});*/
		return R.success(channelManagerActivityVoPage);
	}


	@ApiOperation("渠道经理分页列表")
	@GetMapping(value = "/findList")
	public ResObject findList(@Valid ChannelManagerActivityPageQueryParam param) {
		return channelManagerActivityRepository.findList(param);
	}

	@ApiOperation("数据同步")
	@GetMapping(value = "/synData/{activityId}")
	public ResObject synData(@PathVariable String activityId) throws ExecutionException, InterruptedException {
		return channelManagerActivityRepository.synData(activityId);
	}

	@ApiOperation("渠道经理分页列表")
	@GetMapping(value = "/findPromotionPageList")
	public ResObject findPromotionPageList(@Valid ChannelManagerActivityPageQueryParam param) {
		return channelManagerActivityRepository.findPromotionPageList(param);
	}
	@ApiOperation("渠道经理分页列表")
	@GetMapping(value = "/findPromotionPageListByManagerId")
	public ResObject findPromotionList(@Valid ChannelManagerActivityPageQueryParam param) {
		return channelManagerActivityRepository.findPromotionPageListByManagerId(param);
	}
	@ApiOperation(value = "通过活动ID获取渠道经理 和推广商信息分页列表", notes = "远程调用通过活动ID获取渠道经理 和推广商信息分页列表参数{activityId:活动ID}")
	@PostMapping(value = "/findChannelManagerOrPromotionPageList")
	ResObject findChannelManagerOrPromotionPageList(@RequestBody ChannelManagerActivityPageQueryParam param) {
		return channelManagerActivityRepository.findChannelManagerOrPromotionPageList(param);
	}
	@ApiOperation(value = "获取推广商推广用户信息分页列表", notes = "远程调用获取推广商推广用户信息分页列表分页列表参数{activityId:活动ID;pageNum: 0pageSize:10;promotionUserId：推广商ID}")
	@PostMapping(value = "/findPromotionUserPageList")
	ResObject findPromotionUserPageList(@RequestBody ChannelManagerActivityPageQueryParam param) {
		return channelManagerActivityRepository.findPromotionUserPageList(param);
	}
	@ApiOperation(value = "获取渠道经理推广的推广商信息分页列表", notes = "远程调用获取渠道经理推广的推广商信息分页列表参数{activityId:活动ID;pageNum: 0pageSize:10;channelManagerId：渠道经理ID;promotionType:1渠道经理 2 推广商}")
	@PostMapping(value = "/findChannelManagerPromotionUserPageList")
	ResObject findChannelManagerPromotionUserPageList(@RequestBody ChannelManagerActivityPageQueryParam param) {
		return channelManagerActivityRepository.findChannelManagerPromotionUserPageList(param);
	}

	/**
	 * 通过活动ID ，用户ID 查询可推广班型
	 * @param param
	 * @return
	 */
	@ApiOperation(value = "通过活动ID，用户ID获取推广商推广用户信息分页列表", notes = "远程调用通过活动ID，用户ID获取推广商推广用户信息分页列表参数{activityId:活动ID;pageNum: 0pageSize:10;userId：用户ID}")
	@PostMapping(value = "/findActivityProjectPageList")
	ResObject findActivityProjectPageList(@RequestBody ChannelManagerActivityPageQueryParam param){
		return channelManagerActivityRepository.findActivityProjectPageList(param);
	}

	/**
	* 获取渠道经理 可推广表配置
	*/
	@ApiOperation("获取渠道经理 可推广表配置")
	@ApiImplicitParam(name = "channelManagerId", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/marketing/channelManagerActivity',  'marketing:channelManagerActivity:query')")
	@GetMapping("/{channelManagerId}")
	public ResObject get(@PathVariable Long channelManagerId) {
		ChannelManagerActivityEntity channelManagerActivity = channelManagerActivityService.getById(channelManagerId);
		return R.success(channelManagerActivityMapStruct.toVo(channelManagerActivity));
	}

	/**
	* 新增渠道经理 可推广表配置
	*/
	@ApiOperation("新增渠道经理 可推广表配置")
	@ApiImplicitParam(name = "ChannelManagerActivityEditParam ", value = "新增渠道经理 可推广表配置", dataType = "ChannelManagerActivityEditParam")
	//@PreAuthorize("hasPermission('/marketing/channelManagerActivity',  'marketing:channelManagerActivity:add')")
	@EventLog(message = "新增渠道经理 可推广表配置", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody ChannelManagerActivityEditParam channelManagerActivityEditParam) {
		QueryWrapper<ChannelManagerActivityEntity> queryWrapper = new QueryWrapper<ChannelManagerActivityEntity>();
		queryWrapper.eq("activity_id",channelManagerActivityEditParam.getActivityId());
		queryWrapper.eq("project_id",channelManagerActivityEditParam.getProjectId());
		queryWrapper.eq("channel_manager_id",channelManagerActivityEditParam.getChannelManagerId());
		queryWrapper.eq("promotion_type",channelManagerActivityEditParam.getPromotionType());
		int result = channelManagerActivityService.count(queryWrapper);
		if (result > 0){
			return R.failure("不可重复添加");
		}
		ChannelManagerActivityEntity channelManagerActivity = channelManagerActivityMapStruct.toEntity(channelManagerActivityEditParam);
		return R.toRes(channelManagerActivityService.save(channelManagerActivity));
	}


	@ApiOperation("新增渠道经理 可推广表配置")
	@ApiImplicitParam(name = "ChannelManagerActivityEditParam ", value = "新增渠道经理 可推广表配置", dataType = "ChannelManagerActivityEditParam")
	//@PreAuthorize("hasPermission('/marketing/channelManagerActivity',  'marketing:channelManagerActivity:add')")
	@EventLog(message = "新增渠道经理 可推广表配置", businessType = EventLogEnum.CREATE)
	@PostMapping("/savePromotionUser")
	public ResObject savePromotionUser(@Valid @RequestBody ChannelManagerActivityEditParam channelManagerActivityEditParam) {
		QueryWrapper<ChannelManagerActivityEntity> queryWrapper = new QueryWrapper<ChannelManagerActivityEntity>();
		queryWrapper.eq(StrUtil.isNotEmpty(channelManagerActivityEditParam.getActivityId()),"activity_id",channelManagerActivityEditParam.getActivityId());
		queryWrapper.eq(StrUtil.isNotEmpty(channelManagerActivityEditParam.getProjectId()),"project_id",channelManagerActivityEditParam.getProjectId());
		queryWrapper.eq(StrUtil.isNotEmpty(channelManagerActivityEditParam.getChannelManagerId()),"promotionUserId",channelManagerActivityEditParam.getChannelManagerId());
		queryWrapper.eq(channelManagerActivityEditParam.getPromotionType() != null,"promotion_type",channelManagerActivityEditParam.getPromotionType());
		int result = channelManagerActivityService.count(queryWrapper);
		if (result > 0){
			return R.failure("不可重复添加");
		}
		ChannelManagerActivityEntity channelManagerActivity = channelManagerActivityMapStruct.toEntity(channelManagerActivityEditParam);
		return R.toRes(channelManagerActivityService.save(channelManagerActivity));
	}
	/**
	* 新增渠道经理 可推广表配置
	*/
	@ApiOperation("新增渠道经理 可推广表配置")
	@ApiImplicitParam(name = "ChannelManagerActivityEditParam ", value = "新增渠道经理 可推广表配置", dataType = "ChannelManagerActivityEditParam")
	//@PreAuthorize("hasPermission('/marketing/channelManagerActivity',  'marketing:channelManagerActivity:add')")
	@EventLog(message = "新增渠道经理 可推广表配置", businessType = EventLogEnum.CREATE)
	@PostMapping("/publishChannelManager")
	public ResObject publishChannelManager(@Valid @RequestBody ChannelManagerActivityEditParam channelManagerActivityEditParam) {
		List<ActivityProjectSettingEditParam> activityProjectSettingList = channelManagerActivityEditParam.getActivityProjectSettingList();
		List<ChannelManagerActivityEntity> channelManagerActivityList = new ArrayList<>();
		QueryWrapper<ChannelManagerActivityEntity> queryWrapper = new QueryWrapper<ChannelManagerActivityEntity>();
		queryWrapper.eq("activity_id",channelManagerActivityEditParam.getActivityId());
		queryWrapper.eq("project_id",channelManagerActivityEditParam.getProjectId());
		queryWrapper.eq("channel_manager_id",channelManagerActivityEditParam.getChannelManagerId());
		int result = channelManagerActivityService.count(queryWrapper);
		if (result > 0){
			return R.failure("不可重复添加");
		}
		if (!activityProjectSettingList.isEmpty()){
			activityProjectSettingList.forEach((item) ->{
				ChannelManagerActivityEntity channelManagerActivity = channelManagerActivityMapStruct.toEntity(channelManagerActivityEditParam);
				// 设置版型ID
				channelManagerActivity.setProjectId(item.getProjectId());
				// 投入金额
				channelManagerActivity.setDeductAmount(item.getDeductAmount());
				// 推广商金额
				channelManagerActivity.setPromotionAmount(item.getPromotionAmount());
				// 渠道经理金额
				channelManagerActivity.setChannelManagerAmount(item.getChannelManagerAmount());
				// 产品名称
				channelManagerActivity.setProjectName(item.getProjectName());
				// 渠道经理
				channelManagerActivity.setChannelManagerId(channelManagerActivityEditParam.getChannelManagerId());
 				// LocalDateTime.now().format("")
				//channelManagerActivity.setCreateTime(DateUtil.format());

				channelManagerActivityList.add(channelManagerActivity);
			});
		}
		return R.toRes(channelManagerActivityService.saveBatch(channelManagerActivityList));
	}

	/**
	* 修改渠道经理 可推广表配置
	*/
	@ApiOperation("修改渠道经理 可推广表配置")
	@ApiImplicitParam(name = "ChannelManagerActivityEditParam ", value = "修改渠道经理 可推广表配置", dataType = "ChannelManagerActivityEditParam")
	//@PreAuthorize("hasPermission('/marketing/channelManagerActivity',  'marketing:channelManagerActivity:edit')")
	@EventLog(message = "修改渠道经理 可推广表配置", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody ChannelManagerActivityEditParam channelManagerActivityEditParam) {
		ChannelManagerActivityEntity channelManagerActivity = channelManagerActivityMapStruct.toEntity(channelManagerActivityEditParam);
		return R.toRes(channelManagerActivityService.updateById(channelManagerActivity));
	}

	/**
	* 删除渠道经理 可推广表配置
	*/
	@ApiOperation("删除渠道经理 可推广表配置")
	@ApiImplicitParam(name = "channelManagerId", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/marketing/channelManagerActivity',  'marketing:channelManagerActivity:delete')")
	@EventLog(message = "删除渠道经理 可推广表配置", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{channelManagerIds}")
	public ResObject delete(@PathVariable String[] channelManagerIds) {
		return R.toRes(channelManagerActivityService.removeByIds(Arrays.asList(channelManagerIds)));
	}

	/**
	* 导出渠道经理 可推广表配置
	*/
	@ApiOperation("导出渠道经理 可推广表配置")
	//@PreAuthorize("hasPermission('/marketing/channelManagerActivity',  'marketing:channelManagerActivity:export')")
	@SneakyThrows
	@EventLog(message = "导出渠道经理 可推广表配置", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody ChannelManagerActivityPageQueryParam param, HttpServletResponse response) {
		List<ChannelManagerActivityVo> listVo = channelManagerActivityRepository.exportXls(param);
		ExcelUtils.exportExcel(listVo, ChannelManagerActivityVo.class, "渠道经理可推广表配置", new ExportParams(), response);
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
	public ResObject saveBatch(@Valid @RequestBody List<ChannelManagerActivityEditParam> channelManagerActivityEditParam) {
		return channelManagerActivityRepository.saveBatch(channelManagerActivityEditParam);
	}

	@ApiOperation("发布渠道经理数据")
	@ApiImplicitParam(name = "ChannelManagerActivityEditParam ", value = "发布渠道经理数据", dataType = "ChannelManagerActivityEditParam")
	//@PreAuthorize("hasPermission('/marketing/channelManagerActivity',  'marketing:channelManagerActivity:publishChannelManagerData')")
	@EventLog(message = "发布渠道经理数据", businessType = EventLogEnum.CREATE)
	@PostMapping("publishChannelManagerData")
	ResObject publishChannelManagerData(@RequestBody  ChannelManagerActivityEditParam channelManagerActivityEditParam){
		return channelManagerActivityRepository.publishChannelManager(channelManagerActivityEditParam);
	}

	/**
	 * 渠道经理 可推广表配置 分页列表
	 */
	@ApiOperation("渠道经理 可推广表配置分页列表")
	//@PreAuthorize("hasPermission('/marketing/channelManagerActivity',  'marketing:channelManagerActivity:query')")
	@GetMapping(value = "/pageChannelManagerList")
	public ResObject pageChannelManagerList(@Valid ChannelManagerActivityPageQueryParam param) {
		return channelManagerActivityRepository.pageChannelManagerList(param);
	}


	/**
	 * 删除渠道经理 可推广表配置
	 */
	@ApiOperation("删除渠道经理 可推广表配置")
	@ApiImplicitParam(name = "channelManagerId", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/marketing/channelManagerActivity',  'marketing:channelManagerActivity:delete')")
	@EventLog(message = "删除渠道经理 可推广表配置", businessType = EventLogEnum.DELETE)
	@PostMapping("/deleteByQueryWrapper")
	public ResObject deleteByQueryWrapper(@RequestBody ChannelManagerActivityPageQueryParam param) {
		QueryWrapper<ChannelManagerActivityEntity> queryWrapper = new QueryWrapper<ChannelManagerActivityEntity>();
		queryWrapper.eq("channel_manager_id",param.getChannelManagerId());
		queryWrapper.eq("activity_id",param.getActivityId());
		return R.toRes(channelManagerActivityService.remove(queryWrapper));
	}


	/**
	 * 状态修改
	 */
	@EventLog(message = "渠道经理状态修改", businessType = EventLogEnum.UPDATE)
	@PostMapping("/changeStatus")
	@Transactional
	public ResObject changeStatus(@RequestBody ChannelManagerActivityEditParam channelManagerActivityEditParam) {

		ChannelManagerActivityEntity channelManagerActivityEntity = new ChannelManagerActivityEntity();
		//
		channelManagerActivityEntity.setStatus(channelManagerActivityEditParam.getStatus());
		channelManagerActivityEntity.setId(channelManagerActivityEditParam.getId());
		channelManagerActivityService.updateById(channelManagerActivityEntity);
		// 停用后把推广人员里面渠道经理所得设置为0
		/*QueryWrapper<ChannelManagerActivityEntity> queryWrapper = new QueryWrapper<ChannelManagerActivityEntity>();
		queryWrapper.eq("activity_id",channelManagerActivityEditParam.getActivityId());
		queryWrapper.eq(!StrUtil.isEmpty(channelManagerActivityEditParam.getChannelManagerId()),"channel_manager_id",channelManagerActivityEditParam.getChannelManagerId());
		queryWrapper.eq(StrUtil.isNotEmpty(channelManagerActivityEditParam.getPromotionUserId()),"promotion_user_id",channelManagerActivityEditParam.getPromotionUserId() );
		List<ChannelManagerActivityEntity> channelManagerActivityList = channelManagerActivityService.list(queryWrapper);
		channelManagerActivityList.forEach((item) ->{
			item.setChannelManagerAmount(new BigDecimal(0));
		});*/
		return R.success();
	}


	/**
	 * 状态修改
	 */
	@EventLog(message = "渠道经理状态修改", businessType = EventLogEnum.UPDATE)
	@PostMapping("/changeChannelManagerStatus")
	public ResObject changeChannelManagerStatus(@RequestBody ChannelManagerActivityEditParam channelManagerActivityEditParam) {
		return channelManagerActivityRepository.changeChannelManagerStatus(channelManagerActivityEditParam);
	}
	@EventLog(message = "推广商状态修改", businessType = EventLogEnum.UPDATE)
	@PostMapping("/changePromotionUserStatus")
	public ResObject changePromotionUserStatus(@RequestBody ChannelManagerActivityEditParam channelManagerActivityEditParam) {
		return channelManagerActivityRepository.changePromotionUserStatus(channelManagerActivityEditParam);
	}
}
