package com.drive.admin.repository.impl;

import com.drive.admin.pojo.entity.StudentInfoEntity;
import com.drive.admin.repository.AccountRepository;
import com.drive.admin.repository.AppVersionRepository;
import com.drive.admin.service.StudentInfoService;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public ResObject increaseAmount(String account) {
        log.info(this.getClass() + "increaseAmount-方法请求参数{}",account);
        StudentInfoEntity studentInfoEntity = new StudentInfoEntity();
        studentInfoEntity.setId(account);
        studentInfoEntity.setUsername("测试事务");
        Boolean result = studentInfoService.updateById(studentInfoEntity);
        int i = 1 / 0;
        return result ? R.success(true) : R.failure("出错");
    }
}
