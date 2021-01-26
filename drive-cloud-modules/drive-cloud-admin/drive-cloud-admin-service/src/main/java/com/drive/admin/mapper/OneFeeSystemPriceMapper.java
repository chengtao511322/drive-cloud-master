package com.drive.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.drive.admin.pojo.dto.TreeNodeCategoryDto;
import com.drive.admin.pojo.entity.OneFeeSystemPriceEntity;

import java.util.List;

/**
 * 学车一费制定价表 Mapper 接口
 *
 * @author xiaoguo
 */
public interface OneFeeSystemPriceMapper extends BaseMapper<OneFeeSystemPriceEntity> {

    /**
     *获取树形tree
     * @return
     */
    List<TreeNodeCategoryDto> getServicePackageTree(String tenantId);
}

