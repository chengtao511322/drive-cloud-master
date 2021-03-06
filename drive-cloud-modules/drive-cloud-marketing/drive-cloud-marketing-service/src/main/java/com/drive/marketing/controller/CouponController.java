package com.drive.marketing.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
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
import com.drive.marketing.pojo.dto.CouponAcquirePageQueryParam;
import com.drive.marketing.pojo.dto.CouponEditParam;
import com.drive.marketing.pojo.dto.CouponGetEditParam;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * ??????
 *
 * @author xiaoguo
 */
@Api(tags = "???????????????")
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
	*  ????????????
	*/
	@ApiOperation("????????????")
	//@PreAuthorize("hasPermission('/marketing/coupon',  'marketing:coupon:query')")
	@GetMapping(value = "/pageList")
	public ResObject pageList(@Valid CouponPageQueryParam param) {
		String searchName = param.getName();
		param.setName(null);
		QueryWrapper<CouponEntity> queryWrapper = this.getQueryWrapper(couponMapStruct,param);
		queryWrapper.like(StrUtil.isNotEmpty(searchName),"name",searchName);
		queryWrapper.orderByDesc("create_time");
		Page<CouponEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
		IPage<CouponEntity> pageList = couponService.page(page, queryWrapper);
		Page<CouponVo> couponVoPage = couponMapStruct.toVoList(pageList);
		return R.success(couponVoPage);
	}

	/**
	* ??????
	*/
	@ApiOperation("??????")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/marketing/coupon',  'marketing:coupon:query')")
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
	@ApiOperation("???????????????????????????")
	@GetMapping("/getCoupon")
	public ResObject getCoupon(@PathVariable @RequestBody CouponAcquirePageQueryParam couponPageQueryParam) {
		return couponRepository.getCoupon(couponPageQueryParam);
	}

	/**
	* ??????
	*/
	@ApiOperation("??????")
	@ApiImplicitParam(name = "CouponEditParam ", value = "??????", dataType = "CouponEditParam")
	//@PreAuthorize("hasPermission('/marketing/coupon',  'marketing:coupon:add')")
	@EventLog(message = "??????", businessType = EventLogEnum.CREATE)
	// @GlobalTransactional
	@LcnTransaction //?????????????????????
	@Transactional(rollbackFor = Exception.class)
	@PostMapping
	public ResObject save(@Valid @RequestBody CouponEditParam couponEditParam) {
		Long userId = SecurityUtils.getLoginUser().getUserId();
		CouponEntity coupon = couponMapStruct.toEntity(couponEditParam);
		coupon.setCreateBy(String.valueOf(userId));
		coupon.setReleaseTime(LocalDateTime.now());
		return R.toRes(couponService.save(coupon));
	}

	@ApiOperation("??????")
	@ApiImplicitParam(name = "CouponEditParam ", value = "??????", dataType = "CouponEditParam")
	//@PreAuthorize("hasPermission('/marketing/coupon',  'marketing:coupon:add')")
	@EventLog(message = "??????", businessType = EventLogEnum.CREATE)
	// @GlobalTransactional
	@PostMapping("/saveCoupon")
	public ResObject saveCoupon(@Valid @RequestBody CouponEditParam couponEditParam) {
		return couponRepository.saveCoupon(couponEditParam);
	}

	/**
	* ??????
	*/
	@ApiOperation("??????")
	@ApiImplicitParam(name = "CouponEditParam ", value = "??????", dataType = "CouponEditParam")
	//@PreAuthorize("hasPermission('/marketing/coupon',  'marketing:coupon:edit')")
	@EventLog(message = "??????", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody CouponEditParam couponEditParam) {
		CouponEntity coupon = couponMapStruct.toEntity(couponEditParam);
		return R.toRes(couponService.updateById(coupon));
	}

	/**
	* ??????
	*/
	@ApiOperation("??????")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/marketing/coupon',  'marketing:coupon:delete')")
	@EventLog(message = "??????", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(couponService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* ??????
	*/
	@ApiOperation("??????")
	//@PreAuthorize("hasPermission('/marketing/coupon',  'marketing:coupon:export')")
	@SneakyThrows
	@EventLog(message = "??????", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(CouponPageQueryParam param, HttpServletResponse response) {
		List<CouponEntity> list = couponService.list(this.getQueryWrapper(couponMapStruct, param));
		List<CouponVo> couponVoList = couponMapStruct.toVoList(list);
		ExcelUtils.exportExcel(couponVoList, CouponVo.class, "", new ExportParams(), response);
	}


	/**
	 * ???????????????
	 * @param couponInputDto
	 * @return
	 */
	@PostMapping("/publishCoupon")
	@ApiOperation(value = "?????????????????????", notes = "?????????????????????????????????")
	ResObject publishCoupon(@RequestBody CouponEditParam couponEditParam){
		return couponRepository.publishCoupon(couponEditParam);
	}

	@PostMapping("/sendCouponUser")
	@ApiOperation(value = "????????????ID??????????????????", notes = "?????????????????????????????????")
	ResObject sendCouponUser(@RequestBody CouponGetEditParam couponGetEditParam){
		return couponRepository.sendCouponUser(couponGetEditParam);
	}

	@PostMapping("/updateCoupon")
	@ApiOperation(value = "?????????????????????", notes = "?????????????????????????????????")
	ResObject updateCoupon(@RequestBody CouponEditParam couponEditParam){
		return couponRepository.updateCoupon(couponEditParam);
	}



	@PostMapping("/issueCoupon/{couponId}/{count}")
	@ApiOperation(value = "?????????????????????", notes = "?????????????????????????????????")
	ResObject issueCoupon(@PathVariable("count") Integer count,@PathVariable("couponId") String couponId){
		// ????????????
		int publishCount = count;
		if (publishCount == 0){
			return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
		}


		CouponEntity markCouponEntity = couponService.getById(couponId);
		if (markCouponEntity == null){
			log.error("???");
			return R.failure(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
		}

		// ????????????
		Integer publishSum = markCouponEntity.getPublishCount() + count;

		List<CouponGetEntity> couponGetList = new ArrayList<>();
		for (int i=0; i<publishCount; i++){
			CouponGetEntity couponGetEntity = new CouponGetEntity();
			couponGetEntity.setStatus("1");
			couponGetEntity.setCouponId(couponId);
			// ??????????????????
			couponGetEntity.setCouponCode(Redeem.create((byte) 1, 1, 12, Redeem.password));
			couponGetList.add(couponGetEntity);
		}

		// ??????????????????
		Boolean isGetCoupResult =couponGetService.saveBatch(couponGetList);

		// ??????????????????????????????
		markCouponEntity.setPublishCount(publishSum);
		couponService.updateById(markCouponEntity);
		return R.success(isGetCoupResult);
	}







}
