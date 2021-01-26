package com.drive.basics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.drive.basics.pojo.dto.TreeNodeCategoryDto;
import com.drive.basics.pojo.entity.CategoryEntity;

import java.util.List;

/**
 * 产品分类表 Mapper 接口
 *
 * @author xiaoguo
 */
public interface CategoryMapper extends BaseMapper<CategoryEntity> {

    /**
     *通过类型查询树形结构
     * @param type
     * @return
     */
    List<TreeNodeCategoryDto> getTreeList(String type);


}

