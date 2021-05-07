package com.drive.marketing.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.api.RemoteStudentFeignService;
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.data.utils.ExcelUtils;
import com.drive.common.log.annotation.EventLog;
import com.drive.marketing.pojo.dto.CouponGetEditParam;
import com.drive.marketing.pojo.dto.CouponGetPageQueryParam;
import com.drive.marketing.pojo.entity.ActivityInfoEntity;
import com.drive.marketing.pojo.entity.CouponGetEntity;
import com.drive.marketing.pojo.vo.CouponGetVo;
import com.drive.marketing.repository.RecommendManagertRepository;
import com.drive.marketing.service.CouponGetService;
import com.drive.marketing.service.IActivityInfoService;
import com.drive.marketing.service.mapstruct.CouponGetMapStruct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * 管理
 *
 * @author xiaoguo
 */
@Api(tags = "发劵管理")
@Slf4j
@RestController
@RequestMapping("/couponGet")
public class CouponGetController extends BaseController<CouponGetPageQueryParam, CouponGetEntity> {

	@Autowired
	private CouponGetService couponGetService;
	@Autowired
	private CouponGetMapStruct couponGetMapStruct;

	@Autowired
	private RemoteStudentFeignService remoteStudentFeignService;

	@Autowired
	private IActivityInfoService activityInfoService;

	@Autowired
	private RecommendManagertRepository recommendManagertRepository;


	/**
	*  分页列表
	*/
	@ApiOperation("分页列表")
	//@PreAuthorize("hasPermission('/marketing/couponGet',  'marketing:couponGet:query')")
	@GetMapping(value = "/pageList")
	public ResObject pageList(@Valid CouponGetPageQueryParam param) throws IOException {
		log.info(this.getClass() + "方法pageList请求参数{}" , param);
		Page<CouponGetEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
		IPage<CouponGetEntity> pageList = couponGetService.page(page, this.getQueryWrapper(couponGetMapStruct, param));
		Page<CouponGetVo> couponGetVoPage = couponGetMapStruct.toVoList(pageList);
		/*QueryWrapper<CouponGetEntity> wrapper = new QueryWrapper<CouponGetEntity>();
		wrapper.eq("source","1323709835872100353");
		wrapper.eq("coupon_id","1323905452816822274");
		List<CouponGetEntity> pageList = couponGetService.list(wrapper);
		List<CouponGetVo> couponGetVos = BeanConvertUtils.copyList(pageList,CouponGetVo.class);*/
		List<CouponGetVo> nweCouponGetVo = new ArrayList<>();
		couponGetVoPage.getRecords().stream().forEach((item) ->{
			// 用户
			if (StrUtil.isNotEmpty(item.getUserId())){
				ResObject<StudentInfoVo> resObject = remoteStudentFeignService.get(item.getUserId());
				if (resObject.getCode().equals(200)){
					StudentInfoVo studentInfoVo = resObject.getData();
					if (studentInfoVo!=null){
						if(StrUtil.isEmpty(studentInfoVo.getUsername())){
							item.setUserName(studentInfoVo.getRealName());
						}else{
							item.setUserName(studentInfoVo.getUsername());
						}
						if (StrUtil.isNotEmpty(studentInfoVo.getPhone()))item.setPhone(studentInfoVo.getPhone());
					}

				}
			}
			// 活动
			if (StrUtil.isNotEmpty(item.getSource())){
				ActivityInfoEntity activityInfoEntity = activityInfoService.getById(item.getSource());
				if (activityInfoEntity != null){
					item.setActivity(activityInfoEntity.getZoneName());
				}
			}

			if (StrUtil.isNotEmpty(item.getPromoteUserId())){
				JSONObject jsonObject = recommendManagertRepository.getRecommendUser(item.getPromoteUserId());
				if (jsonObject != null){
					JSONObject studentInfo = (JSONObject) jsonObject.get("tStudentInfo");
					item.setPromoteUserName(jsonObject.getString("name"));
					item.setPromoteUserPhone(studentInfo.getString("phone"));
				}
			}
			nweCouponGetVo.add(item);
		});

		/*Workbook workbook = null;
		Date start = new Date();
		ExportParams params = new ExportParams("扫码领700优惠券参加人员统计", "扫码领700优惠券");
		workbook = ExcelExportUtil.exportExcel(params, CouponGetVo.class, nweCouponGetVo);
		System.out.println(new Date().getTime() - start.getTime());
		File savefile = new File("D:/excel/");
		if (!savefile.exists()) {
			savefile.mkdirs();
		}
		FileOutputStream fos = new FileOutputStream("D:/excel/ExcelExportBigData.bigDataExport.xlsx");
		workbook.write(fos);
		fos.close();*/
		//log.info(this.getClass() + "方法pageList请求结果{}", couponGetVoPage);
		return R.success(couponGetVoPage);
	}

	/**
	* 获取
	*/
	@ApiOperation("获取")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/marketing/couponGet',  'marketing:couponGet:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		CouponGetEntity couponGet = couponGetService.getById(id);
		return R.success(couponGetMapStruct.toVo(couponGet));
	}

	/**
	* 新增
	*/
	@ApiOperation("新增")
	@ApiImplicitParam(name = "CouponGetEditParam ", value = "新增", dataType = "CouponGetEditParam")
	//@PreAuthorize("hasPermission('/marketing/couponGet',  'marketing:couponGet:add')")
	@EventLog(message = "新增", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody CouponGetEditParam couponGetEditParam) {
		CouponGetEntity couponGet = couponGetMapStruct.toEntity(couponGetEditParam);
		return R.toRes(couponGetService.save(couponGet));
	}

	/**
	* 修改
	*/
	@ApiOperation("修改")
	@ApiImplicitParam(name = "CouponGetEditParam ", value = "修改", dataType = "CouponGetEditParam")
	//@PreAuthorize("hasPermission('/marketing/couponGet',  'marketing:couponGet:edit')")
	@EventLog(message = "修改", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody CouponGetEditParam couponGetEditParam) {
		CouponGetEntity couponGet = couponGetMapStruct.toEntity(couponGetEditParam);
		return R.toRes(couponGetService.updateById(couponGet));
	}

	/**
	* 删除
	*/
	@ApiOperation("删除")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/marketing/couponGet',  'marketing:couponGet:delete')")
	@EventLog(message = "删除", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(couponGetService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 导出
	*/
	@ApiOperation("导出")
	//@PreAuthorize("hasPermission('/marketing/couponGet',  'marketing:couponGet:export')")
	@SneakyThrows
	@EventLog(message = "导出", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(CouponGetPageQueryParam param, HttpServletResponse response) {
		QueryWrapper queryWrapper =this.getQueryWrapper(couponGetMapStruct, param);
		queryWrapper.between(StrUtil.isNotEmpty(param.getBeginTime()),"get_time",param.getBeginTime(),param.getEndTime());
		queryWrapper.between(StrUtil.isNotEmpty(param.getUseBeginTime()),"use_time",param.getUseBeginTime(),param.getUseEndTime());
		List<CouponGetEntity> list = couponGetService.list(queryWrapper);
		List<CouponGetVo> couponGetVoList = BeanConvertUtils.copyList(list,CouponGetVo.class);
		couponGetVoList.stream().forEach((item) ->{
			// 用户
			if (StrUtil.isNotEmpty(item.getUserId())){
				ResObject<StudentInfoVo> resObject = remoteStudentFeignService.get(item.getUserId());
				if (resObject.getCode().equals(200)){
					StudentInfoVo studentInfoVo = resObject.getData();
					if (studentInfoVo!=null){
						if(StrUtil.isEmpty(studentInfoVo.getUsername())){
							item.setUserName(studentInfoVo.getRealName());
						}else{
							item.setUserName(studentInfoVo.getUsername());
						}
						if (StrUtil.isNotEmpty(studentInfoVo.getPhone()))item.setPhone(studentInfoVo.getPhone());
					}

				}
			}
			// 活动
			if (StrUtil.isNotEmpty(item.getSource())){
				ActivityInfoEntity activityInfoEntity = activityInfoService.getById(item.getSource());
				if (activityInfoEntity != null){
					item.setActivity(activityInfoEntity.getZoneName());
				}
			}

			if (StrUtil.isNotEmpty(item.getPromoteUserId())){
				JSONObject jsonObject = recommendManagertRepository.getRecommendUser(item.getPromoteUserId());
				if (jsonObject != null){
					JSONObject studentInfo = (JSONObject) jsonObject.get("tStudentInfo");
					item.setPromoteUserName(jsonObject.getString("name"));
					item.setPromoteUserPhone(studentInfo.getString("phone"));
				}
			}
		});
		ExcelUtils.exportExcel(couponGetVoList, CouponGetVo.class, "领劵记录", new ExportParams(), response);
	}
	/**
	* 导出
	*/
	@ApiOperation("导出")
	@SneakyThrows
	//@EventLog(message = "导出", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportExcelXls")
	public void exportExcelXls(CouponGetPageQueryParam param, HttpServletResponse response) {
		Workbook workbook = null;
		Date start = new Date();
		ExportParams params = new ExportParams("1对1王者班700抵用劵领取记录", "1对1王者班700抵用劵记录");
		workbook = ExcelExportUtil.exportExcel(params, CouponGetVo.class, this.getExcelData(param));
		System.out.println(new Date().getTime() - start.getTime());
		File savefile = new File("D:/excel/");
		if (!savefile.exists()) {
			savefile.mkdirs();
		}
		FileOutputStream fos = new FileOutputStream("D:/excel/1对1王者班700抵用劵领取记录.xlsx");
		workbook.write(fos);
		fos.close();
		//ExcelUtils.exportExcel(this.getExcelData(param), CouponGetVo.class, "700活动参加人员", new ExportParams(), response);
	}

	@Async
	public List<CouponGetVo> getExcelData(CouponGetPageQueryParam param){
		QueryWrapper<CouponGetEntity> wrapper = new QueryWrapper<CouponGetEntity>();
		//wrapper.eq("source",param.getSource());
		//wrapper.eq("coupon_id",param.getCouponId());
		wrapper.groupBy("user_id");
		wrapper.ge("get_time","2020-12-29 05:57:14");
		List<CouponGetEntity> pageList = couponGetService.list(wrapper);
		List<CouponGetVo> couponGetVos = BeanConvertUtils.copyList(pageList,CouponGetVo.class);
		List<CouponGetVo> nweCouponGetVo = new ArrayList<>();
		couponGetVos.stream().forEach((item) ->{
			// 用户
			if (StrUtil.isNotEmpty(item.getUserId())){
				ResObject<StudentInfoVo> resObject = remoteStudentFeignService.get(item.getUserId());
				if (resObject.getCode().equals(200)){
					StudentInfoVo studentInfoVo = resObject.getData();
					if (studentInfoVo!=null){
						if(StrUtil.isEmpty(studentInfoVo.getUsername())){
							item.setUserName(studentInfoVo.getRealName());
						}else{
							item.setUserName(studentInfoVo.getUsername());
						}
						if (StrUtil.isNotEmpty(studentInfoVo.getPhone()))item.setPhone(studentInfoVo.getPhone());
					}

				}
			}
			// 活动
			if (StrUtil.isNotEmpty(item.getSource())){
				ActivityInfoEntity activityInfoEntity = activityInfoService.getById(item.getSource());
				if (activityInfoEntity != null){
					item.setActivity(activityInfoEntity.getZoneName());
				}
			}

			if (StrUtil.isNotEmpty(item.getPromoteUserId())){
				JSONObject jsonObject = recommendManagertRepository.getRecommendUser(item.getPromoteUserId());
				if (jsonObject != null){
					JSONObject studentInfo = (JSONObject) jsonObject.get("tStudentInfo");
					item.setPromoteUserName(jsonObject.getString("name"));
					item.setPromoteUserPhone(studentInfo.getString("phone"));
				}
			}
			nweCouponGetVo.add(item);
		});
		return  nweCouponGetVo;
	}


}
