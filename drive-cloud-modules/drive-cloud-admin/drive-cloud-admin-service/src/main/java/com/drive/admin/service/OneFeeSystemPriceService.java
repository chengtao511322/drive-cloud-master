package com.drive.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.admin.pojo.dto.TreeNodeCategoryDto;
import com.drive.admin.pojo.entity.OneFeeSystemPriceEntity;

import java.util.List;

/**
 *
 * 学车一费制定价表 服务类
 *
 * @author xiaoguo
 */
public interface OneFeeSystemPriceService extends IService<OneFeeSystemPriceEntity>{

    /**
     *获取树形tree
     * @return
     */
    List<TreeNodeCategoryDto> getServicePackageTree(String tenantId);
}

