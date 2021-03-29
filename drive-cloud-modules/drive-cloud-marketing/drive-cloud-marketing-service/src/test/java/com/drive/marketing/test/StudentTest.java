
package com.drive.marketing.test;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.basics.feign.RemoteChannelAuthFeignService;
import com.drive.basics.pojo.dto.ChannelAuthEditParam;
import com.drive.marketing.DriveCloudMarketingApplication;
import com.drive.marketing.pojo.entity.ChannelManagerActivityEntity;
import com.drive.marketing.repository.RecommendManagertRepository;
import com.drive.marketing.service.ChannelManagerActivityService;
import com.drive.marketing.service.CouponGetService;
import com.drive.marketing.service.IActivityInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Autowired
    private ChannelManagerActivityService channelManagerActivityService;

    @Autowired
    private RemoteChannelAuthFeignService channelAuthFeignService;

    @Test
    public void getChannel(){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("activity_id","1362286770695180290");
        queryWrapper.groupBy("user_id");
        List<ChannelManagerActivityEntity> channelManagerActivityList = channelManagerActivityService.list(queryWrapper);

        if (channelManagerActivityList.size() > 0){
            channelManagerActivityList.stream().forEach((item) ->{
                ChannelAuthEditParam channelAuthEditParam = new ChannelAuthEditParam();
                // 运营商
                channelAuthEditParam.setTenantId("bbdc1bd499b241daa6fe99063e63a193");
                channelAuthEditParam.setUserId(item.getUserId());
                channelAuthEditParam.setChannelId("1278626418668916478");
                channelAuthEditParam.setCreateUser("系统数据同步");
                channelAuthFeignService.updateChannelAuth(channelAuthEditParam);
            });

        }
    }

    @Test
    public void  getBanner(){
        //ResObject resObject = studentService.get("000f431303a543eb95a9");
        //log.info("数据{}",resObject);
        // ConfigurableApplicationContext applicationContext = SpringApplication.run(WangMikeSpringApplication.class, args);
        // SpringBeanUtils.setApplicationContext(applicationContext);
    }


  /*  @Test
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
    }*/



   /* @Test
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
    }*/


}


