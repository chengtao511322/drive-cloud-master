package com.drive.basics.service.impl;

import com.drive.basics.mapper.CategoryMapper;
import com.drive.basics.pojo.dto.TreeNodeCategoryDto;
import com.drive.basics.pojo.entity.CategoryEntity;
import com.drive.basics.pojo.vo.CategoryVo;
import com.drive.basics.service.CategoryService;
import com.drive.common.core.base.BaseService;
import com.drive.common.core.constant.Constants;
import com.drive.common.core.tree.TreeSelect;
import com.drive.common.core.utils.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品分类表 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class CategoryServiceImpl extends BaseService<CategoryMapper, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<TreeSelect> buildMenuTreeSelect(List<CategoryVo> categoryVos) {
        List<CategoryVo> categoryTrees = TreeUtil.build(categoryVos, Constants.MENU_ROOT_ID);
        return TreeUtil.buildTreeSelect(categoryTrees);
    }

    @Override
    public List<TreeNodeCategoryDto> getTreeList(String type) {
        return categoryMapper.getTreeList(type);
    }

}

