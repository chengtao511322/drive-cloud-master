package com.drive.basics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.basics.pojo.dto.TreeNodeCategoryDto;
import com.drive.basics.pojo.entity.CategoryEntity;
import com.drive.basics.pojo.vo.CategoryVo;
import com.drive.common.core.tree.TreeSelect;

import java.util.List;

/**
 *
 * 产品分类表 服务类
 *
 * @author xiaoguo
 */
public interface CategoryService extends IService<CategoryEntity>{

    /**
     * 生成前端选择树
     * @param menus
     * @return
     */
    List<TreeSelect> buildMenuTreeSelect(List<CategoryVo> categoryVos);

    /**
     *通过类型查询树形结构
     * @param type
     * @return
     */
    List<TreeNodeCategoryDto> getTreeList(String type);

}

