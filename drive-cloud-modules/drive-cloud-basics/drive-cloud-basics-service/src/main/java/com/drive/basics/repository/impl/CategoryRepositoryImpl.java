package com.drive.basics.repository.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.api.RemoteOneFeeSystemPriceFeignService;
import com.drive.admin.pojo.dto.OneFeeSystemPriceEditParam;
import com.drive.basics.pojo.dto.CategoryEditParam;
import com.drive.basics.pojo.dto.CategoryPageQueryParam;
import com.drive.basics.pojo.dto.TreeNodeCategoryDto;
import com.drive.basics.pojo.entity.CategoryEntity;
import com.drive.basics.pojo.vo.CategoryVo;
import com.drive.basics.repository.CategoryRepository;
import com.drive.basics.service.CategoryService;
import com.drive.basics.service.mapstruct.CategoryMapStruct;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResCodeEnum;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.tree.TreeNodeUtil;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.data.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

                                                                                
/**
 *
 * 产品分类表 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  CategoryRepositoryImpl extends BaseController<CategoryPageQueryParam, CategoryEntity>  implements CategoryRepository{

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryMapStruct categoryMapStruct;


    @Autowired
    private RemoteOneFeeSystemPriceFeignService remoteOneFeeSystemPriceFeignService;

    /*
     *
     *功能描述
     * @author xiaoguo
     * @description 产品分类表 分页列表
     * @date 2020/2/12 17:09
     * @param  * @param CategoryPageQueryParam
     * @return
     */
    @Override
    public ResObject pageList(CategoryPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<CategoryEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<CategoryEntity> pageList = categoryService.page(page, this.getQueryWrapper(categoryMapStruct, param));
        Page<CategoryVo> categoryVoPage = categoryMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",categoryVoPage);
        return R.success(categoryVoPage);
    }

    @Override
    public ResObject findList(CategoryPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(categoryMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<CategoryEntity> pageList = categoryService.list(queryWrapper);
        List<CategoryVo> categoryVoList = categoryMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findList-方法请求结果{}",categoryVoList);
        if (categoryVoList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(categoryVoList);
    }



    @Override
    public ResObject getInfo(CategoryPageQueryParam param) {
        return null;
    }

    /**
     * *通过ID获取产品分类表 列表
     **/
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        CategoryEntity category = categoryService.getById(id);
        CategoryVo categoryVo = BeanConvertUtils.copy(category, CategoryVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",categoryVo);
        if (categoryVo ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(categoryVo);
    }

    /**
     * *保存产品分类表 信息
     **/
    @Override
    public ResObject save(CategoryEditParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CategoryEntity category = BeanConvertUtils.copy(installParam, CategoryEntity.class);
        Boolean result = categoryService.save(category);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改产品分类表 信息
     **/
    @Override
    public ResObject update(CategoryEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CategoryEntity category = BeanConvertUtils.copy(updateParam, CategoryEntity.class);
        Boolean result = categoryService.updateById(category);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除产品分类表 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        if (ids.length <= 0){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        return R.toRes(categoryService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除产品分类表 信息
     **/
    @Override
    public ResObject deleteById(String id) {
        log.info(this.getClass() + "deleteById-方法请求参数{}",id);
        if(StrUtil.isEmpty(id)){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        //QueryWrapper<String> queryWrapper = new QueryWrapper<String>();
        //queryWrapper.eq(StrUtil.isNotEmpty(id),"id",id);
        Boolean result = categoryService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出产品分类表 信息
     **/
    @Override
    public ResObject exportXls(CategoryPageQueryParam param, HttpServletResponse response)throws IOException {
        log.info(this.getClass() + "exportXls方法请求参数{}",param);
        QueryWrapper queryWrapper = this.getQueryWrapper(categoryMapStruct, param);
        List<CategoryEntity> list = categoryService.list(queryWrapper);
        List<CategoryVo>categoryList = categoryMapStruct.toVoList(list);
        ExcelUtils.exportExcel(categoryList, CategoryVo.class, "", new ExportParams(), response);
        return R.success("导出成功");
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(CategoryEditParam param) {
        log.info(this.getClass() + "changeStatus方法请求参数{}",param);
        if (param.getId() == null){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        CategoryEntity CategoryEntity = new CategoryEntity();
        CategoryEntity.setId(param.getId());
        //CategoryEntity.setStatus(param.getStatus());
        //CategoryEntity.setUpdateTime()
        Boolean result = categoryService.updateById(CategoryEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",CategoryEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    @Override
    public ResObject getCategoryByType(String type,String tenantId) {
        log.info(this.getClass() + "----------getCategoryByType方法请求参数{}",type);
        if (StrUtil.isEmpty(type)){
            log.error("类型空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        if (StrUtil.isEmpty(tenantId)){
            log.error("类型空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        OneFeeSystemPriceEditParam oneFeeSystemPriceEditParam = new OneFeeSystemPriceEditParam();
        oneFeeSystemPriceEditParam.setOperatorId(tenantId);
        ResObject<List<com.drive.admin.pojo.dto.TreeNodeCategoryDto>> resObject = remoteOneFeeSystemPriceFeignService.getServicePackageTree(oneFeeSystemPriceEditParam);
        List<com.drive.admin.pojo.dto.TreeNodeCategoryDto> treeNodeCategoryDtoList = null;
        if (resObject.getCode().equals(ResCodeEnum.SUCCESS.getCode())) {
            treeNodeCategoryDtoList = resObject.getData();
        }
        List<TreeNodeCategoryDto> categoryList = categoryService.getTreeList(type);
        /*for (TreeNodeCategoryDto item:
        categoryList) {
            if (item.getId() .equals(34)){
                item.setChildren(treeNodeCategoryDtoList);
            }
        }*/
        if (treeNodeCategoryDtoList !=null){
            for (com.drive.admin.pojo.dto.TreeNodeCategoryDto item:
            treeNodeCategoryDtoList) {
                TreeNodeCategoryDto treeNodeCategoryDto = new TreeNodeCategoryDto();
                treeNodeCategoryDto.setpId("34");
                treeNodeCategoryDto.setValue(item.getValue());
                treeNodeCategoryDto.setLabel(item.getLabel());
                categoryList.add(treeNodeCategoryDto);
            }
        }

        log.info("结果{}",categoryList);
        List<TreeNodeCategoryDto>  listDto = TreeNodeUtil.assembleTree(categoryList);
        return R.success(listDto);
    }
}

