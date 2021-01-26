package com.drive.monitor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drive.monitor.mapper.OperLogMapper;
import com.drive.monitor.pojo.entity.OperLogEntity;
import com.drive.monitor.service.OperLogService;
import org.springframework.stereotype.Service;

/**
 * 操作日志记录 服务实现类
 *
 * @author DreamChan
 */
@Service
public class OperLogServiceImpl extends ServiceImpl<OperLogMapper, OperLogEntity> implements OperLogService {

}

