package com.drive.admin.repository.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.drive.admin.pojo.entity.StudentInfoEntity;
import com.drive.admin.repository.AccountRepository;
import com.drive.admin.service.StudentInfoService;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.exception.BizException;
import com.drive.marketing.api.RemoteActivityService;
import com.drive.marketing.pojo.dto.CouponEditParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Slf4j
/*@Compensable(
        interfaceClass = AccountRepository.class
        , confirmableKey = "accountServiceConfirm"
        , cancellableKey = "accountServiceCancel"
)*/
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    private RemoteActivityService remoteActivityService;

    //@GlobalTransactional
    @LcnTransaction //分布式事务注解
    @Override
    public ResObject increaseAmount(String account) {
        log.info(this.getClass() + "increaseAmount-方法请求参数{}",account);
       StudentInfoEntity studentInfoEntity = new StudentInfoEntity();
        studentInfoEntity.setId("000f431303a543eb95a9");
        studentInfoEntity.setUsername("测试事务");
        studentInfoEntity.setUpdateTime(LocalDateTime.now());
        Boolean result = studentInfoService.updateById(studentInfoEntity);
        log.info("请求本地接口{}",result);
        CouponEditParam coupon = new CouponEditParam();
        coupon.setName("测试分布式事务优惠券");
        coupon.setAmount(new BigDecimal(100));
        coupon.setMinPoint(new BigDecimal(10));
        coupon.setStartTime(LocalDateTime.now());
        coupon.setEndTime(LocalDateTime.now());
        coupon.setReleaseTime(LocalDateTime.now());
        coupon.setTenantId("bbdc1bd499b241daa6fe99063e63a193");
        ResObject couponRes = remoteActivityService.saveCouponData(coupon);
        log.info("请求优惠券接口{}",couponRes);
        int i = 1 / Integer.parseInt(account);
        return result ? R.success(true) : R.failure("出错");
    }
}
