package com.drive.marketing.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.core.utils.Redeem;
import com.drive.common.data.utils.ExcelUtils;
import com.drive.common.log.annotation.EventLog;
import com.drive.common.security.utils.SecurityUtils;
import com.drive.marketing.pojo.dto.CouponEditParam;
import com.drive.marketing.pojo.dto.CouponPageQueryParam;
import com.drive.marketing.pojo.entity.CouponEntity;
import com.drive.marketing.pojo.entity.CouponGetEntity;
import com.drive.marketing.pojo.entity.CouponProductRelationEntity;
import com.drive.marketing.pojo.vo.CouponProductRelationVo;
import com.drive.marketing.pojo.vo.CouponVo;
import com.drive.marketing.repository.CouponRepository;
import com.drive.marketing.service.ActivityCouponRelationService;
import com.drive.marketing.service.CouponGetService;
import com.drive.marketing.service.CouponProductRelationService;
import com.drive.marketing.service.CouponService;
import com.drive.marketing.service.mapstruct.CouponMapStruct;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 管理
 *
 * @author xiaoguo
 */
@Api(tags = "优惠券管理")
@Slf4j
@RestController
@RequestMapping("/coupon")
public class CouponController extends BaseController<CouponPageQueryParam, CouponEntity> {

	@Autowired
	private CouponService couponService;


	@Autowired
	private CouponGetService couponGetService;

	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private CouponMapStruct couponMapStruct;

	@Autowired
	private ActivityCouponRelationService activityCouponRelationService;

	@Autowired
	private CouponProductRelationService couponProductRelationService;

	/**
	*  分页列表
	*/
	@ApiOperation("分页列表")
	@PreAuthorize("hasPermission('/marketing/coupon',  'marketing:coupon:query')")
	@GetMapping(value = "/pageList")
	public ResObject pageList(@Valid CouponPageQueryParam param) {
		QueryWrapper<CouponEntity> queryWrapper = new QueryWrapper<CouponEntity>();
		queryWrapper.eq(param.getType() !=null,"type",param.getType());
		queryWrapper.like(StrUtil.isNotEmpty(param.getName()),"name",param.getName());
		queryWrapper.orderByDesc("create_time");
		Page<CouponEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
		IPage<CouponEntity> pageList = couponService.page(page, queryWrapper);
		Page<CouponVo> couponVoPage = couponMapStruct.toVoList(pageList);
		return R.success(couponVoPage);
	}

	/**
	* 获取
	*/
	@ApiOperation("获取")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	@PreAuthorize("hasPermission('/marketing/coupon',  'marketing:coupon:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		CouponEntity coupon = couponService.getById(id);
		CouponVo couponVo = BeanConvertUtils.copy(coupon,CouponVo.class);
		if (coupon != null){
			QueryWrapper<CouponProductRelationEntity> queryWrapper = new QueryWrapper<CouponProductRelationEntity>();
			queryWrapper.eq("coupon_id",coupon.getId());
			CouponProductRelationEntity couponProductRelationEntity =couponProductRelationService.getOne(queryWrapper);
			if(couponProductRelationEntity != null)couponVo.setCouponProductRelation(BeanConvertUtils.copy(couponProductRelationEntity, CouponProductRelationVo.class));
		}

		return R.success(couponVo);
	}

	/**
	* 新增
	*/
	@ApiOperation("新增")
	@ApiImplicitParam(name = "CouponEditParam ", value = "新增", dataType = "CouponEditParam")
	@PreAuthorize("hasPermission('/marketing/coupon',  'marketing:coupon:add')")
	@EventLog(message = "新增", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody CouponEditParam couponEditParam) {
		Long userId = SecurityUtils.getLoginUser().getUserId();
		CouponEntity coupon = couponMapStruct.toEntity(couponEditParam);
		coupon.setCreateBy(String.valueOf(userId));
		coupon.setReleaseTime(LocalDateTime.now());
		return R.toRes(couponService.save(coupon));
	}

	/**
	* 修改
	*/
	@ApiOperation("修改")
	@ApiImplicitParam(name = "CouponEditParam ", value = "修改", dataType = "CouponEditParam")
	@PreAuthorize("hasPermission('/marketing/coupon',  'marketing:coupon:edit')")
	@EventLog(message = "修改", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody CouponEditParam couponEditParam) {
		CouponEntity coupon = couponMapStruct.toEntity(couponEditParam);
		return R.toRes(couponService.updateById(coupon));
	}

	/**
	* 删除
	*/
	@ApiOperation("删除")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/marketing/coupon',  'marketing:coupon:delete')")
	@EventLog(message = "删除", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(couponService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 导出
	*/
	@ApiOperation("导出")
	@PreAuthorize("hasPermission('/marketing/coupon',  'marketing:coupon:export')")
	@SneakyThrows
	@EventLog(message = "导出", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(CouponPageQueryParam param, HttpServletResponse response) {
		List<CouponEntity> list = couponService.list(this.getQueryWrapper(couponMapStruct, param));
		List<CouponVo> couponVoList = couponMapStruct.toVoList(list);
		ExcelUtils.exportExcel(couponVoList, CouponVo.class, "", new ExportParams(), response);
	}


	/**
	 * 发布优惠券
	 * @param couponInputDto
	 * @return
	 */
	@PostMapping("/publishCoupon")
	@ApiOperation(value = "发布优惠券信息", notes = "远程调用发布优惠券信息")
	ResObject publishCoupon(@RequestBody CouponEditParam couponEditParam){
		return couponRepository.publishCoupon(couponEditParam);
	}

	@PostMapping("/updateCoupon")
	@ApiOperation(value = "发布优惠券信息", notes = "远程调用发布优惠券信息")
	ResObject updateCoupon(@RequestBody CouponEditParam couponEditParam){
		return couponRepository.updateCoupon(couponEditParam);
	}



	@PostMapping("/issueCoupon/{couponId}/{count}")
	@ApiOperation(value = "修改优惠券信息", notes = "远程调用修改优惠券信息")
	ResObject issueCoupon(@PathVariable("count") Integer count,@PathVariable("couponId") String couponId){
		// 发行数量
		int publishCount = count;
		if (publishCount == 0){
			return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
		}


		CouponEntity markCouponEntity = couponService.getById(couponId);
		if (markCouponEntity == null){
			log.error("空");
			return R.failure(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
		}

		// 数量滴加
		Integer publishSum = markCouponEntity.getPublishCount() + count;

		List<CouponGetEntity> couponGetList = new ArrayList<>();
		for (int i=0; i<publishCount; i++){
			CouponGetEntity couponGetEntity = new CouponGetEntity();
			couponGetEntity.setStatus("1");
			couponGetEntity.setCouponId(couponId);
			// 设置优惠券码
			couponGetEntity.setCouponCode(Redeem.create((byte) 1, 1, 12, Redeem.password));
			couponGetList.add(couponGetEntity);
		}

		// 发行优惠券码
		Boolean isGetCoupResult =couponGetService.saveBatch(couponGetList);

		// 重新设置新的发行数量
		markCouponEntity.setPublishCount(publishSum);
		couponService.updateById(markCouponEntity);
		return R.success(isGetCoupResult);
	}







}
