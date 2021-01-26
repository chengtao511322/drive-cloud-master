/*
package com.drive.member.test;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.core.utils.SpringContextUtil;
import com.drive.member.DriveMemberApplication;
import com.drive.member.pojo.vo.StudentInfoVo;
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


@SpringBootTest(classes = DriveMemberApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class StudentTest {




    @Autowired
    private CouponGetService couponGetService;

    @Autowired
    private  RemoteStudentService remoteStudentService;

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
        RemoteStudentService remoteStudentService = SpringContextUtil.getBean(RemoteStudentService.class);
        log.info("{}remoteStudentService---",remoteStudentService);
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
        ExportParams params = new ExportParams("大数据测试", "测试");
        workbook = ExcelExportUtil.exportExcel(params, CouponGetVo.class, nweCouponGetVo);
        System.out.println(new Date().getTime() - start.getTime());
        File savefile = new File("D:/excel/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("D:/excel/ExcelExportBigData.bigDataExport.xlsx");
        workbook.write(fos);
        fos.close();
    }





}

*/
