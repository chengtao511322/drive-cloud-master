/*
package com.drive.marketing.test;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.core.utils.SpringContextUtil;
import com.drive.marketing.DriveCloudMarketingApplication;
import com.drive.marketing.pojo.entity.ActivityInfoEntity;
import com.drive.marketing.pojo.entity.CouponGetEntity;
import com.drive.marketing.pojo.vo.CouponGetVo;
import com.drive.marketing.repository.RecommendManagertRepository;
import com.drive.marketing.service.CouponGetService;
import com.drive.marketing.service.IActivityInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SpringBootTest(classes = DriveCloudMarketingApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class StudentTest {




    @Autowired
    private CouponGetService couponGetService;


    @Autowired
    private RecommendManagertRepository recommendManagertRepository;

    @Autowired
    private IActivityInfoService activityInfoService;

    @Test
    public void  getBanner(){
        //ResObject resObject = studentService.get("000f431303a543eb95a9");
        //log.info("数据{}",resObject);
        // ConfigurableApplicationContext applicationContext = SpringApplication.run(WangMikeSpringApplication.class, args);
        // SpringBeanUtils.setApplicationContext(applicationContext);
    }


    @Test
    public void bigDataExport() throws Exception {
        QueryWrapper<CouponGetEntity> wrapper = new QueryWrapper<CouponGetEntity>();
        wrapper.eq("source","1323709835872100353");
        wrapper.eq("coupon_id","1323905452816822274");
        List<CouponGetEntity> pageList = couponGetService.list(wrapper);
        List<CouponGetVo> couponGetVos = BeanConvertUtils.copyList(pageList,CouponGetVo.class);
        List<CouponGetVo> nweCouponGetVo = new ArrayList<>();
        couponGetVos.stream().forEach((item) ->{
            // 用户
            if (StrUtil.isNotEmpty(item.getUserId())){
                ResObject<StudentInfoVo> resObject = remoteStudentService.get(item.getUserId());
                if (resObject.getCode().equals(200)){
                    StudentInfoVo studentInfoVo = resObject.getData();
                    item.setUserName(StrUtil.isEmpty(studentInfoVo.getUsername())?studentInfoVo.getRealName():studentInfoVo.getUsername());
                    if (StrUtil.isNotEmpty(studentInfoVo.getPhone()))item.setPhone(studentInfoVo.getPhone());
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

        Workbook workbook = null;
        Date start = new Date();
        ExportParams params = new ExportParams("VIP包过优惠券领取记录", "优惠券领取记录");
        workbook = ExcelExportUtil.exportExcel(params, CouponGetVo.class, nweCouponGetVo);
        System.out.println(new Date().getTime() - start.getTime());
        File savefile = new File("D:/excel/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("D:/excel/VIP包过优惠券领取记录.xlsx");
        workbook.write(fos);
        fos.close();
    }



    @Test
    public void excelData(){
        QueryWrapper<CouponGetEntity> wrapper = new QueryWrapper<CouponGetEntity>();
        wrapper.eq("source","1323709835872100353");
        wrapper.eq("coupon_id","1323905452816822274");
        List<CouponGetEntity> pageList = couponGetService.list(wrapper);
        List<CouponGetVo> couponGetVos = BeanConvertUtils.copyList(pageList,CouponGetVo.class);
        List<CouponGetVo> nweCouponGetVo = new ArrayList<>();
        couponGetVos.stream().forEach((item) ->{
            // 用户
            if (StrUtil.isNotEmpty(item.getUserId())){
                // ResObject<StudentInfoVo> resObject = remoteStudentService.get(item.getUserId());
                if (resObject.getCode().equals(200)){
                    StudentInfoVo studentInfoVo = resObject.getData();
                    item.setUserName(StrUtil.isEmpty(studentInfoVo.getUsername())?studentInfoVo.getRealName():studentInfoVo.getUsername());
                    if (StrUtil.isNotEmpty(studentInfoVo.getPhone()))item.setPhone(studentInfoVo.getPhone());
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


        //ExcelUtils.exportExcel(nweCouponGetVo, CouponGetVo.class, "参加活动人员", new ExportParams());
    }


}

*/
