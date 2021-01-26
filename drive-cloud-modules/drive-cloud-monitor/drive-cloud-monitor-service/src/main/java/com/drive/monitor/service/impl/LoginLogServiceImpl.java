package com.drive.monitor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drive.monitor.mapper.LoginLogMapper;
import com.drive.monitor.pojo.entity.LoginLogEntity;
import com.drive.monitor.service.LoginLogService;
import org.springframework.stereotype.Service;

/**
 * 系统访问记录 服务实现类
 *
 * @author DreamChan
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLogEntity> implements LoginLogService {

}

