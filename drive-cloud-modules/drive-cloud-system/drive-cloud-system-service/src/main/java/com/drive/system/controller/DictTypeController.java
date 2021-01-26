package com.drive.system.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.data.utils.ExcelUtils;
import com.drive.common.log.annotation.EventLog;
import com.drive.system.pojo.dto.DictTypeEditParam;
import com.drive.system.pojo.dto.DictTypePageQueryParam;
import com.drive.system.pojo.entity.DictTypeEntity;
import com.drive.system.pojo.vo.DictTypeVo;
import com.drive.system.service.DictTypeService;
import com.drive.system.service.mapstruct.DictTypeMapStruct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


/**
 * 字典类型管理
 *
 * @author xiaoguo
 */
@Api(tags = "字典类型管理")
@Slf4j
@RestController
@RequestMapping("/dictType")
public class DictTypeController extends BaseController<DictTypePageQueryParam, DictTypeEntity> {

    @Autowired
    private DictTypeService dictTypeService;
    @Autowired
    private DictTypeMapStruct dictTypeMapStruct;

    /**
     * 字典类型 分页列表
     */
    @ApiOperation("字典类型分页列表")
    @PreAuthorize("hasPermission('/dictType',  'system:dict:query')")
    @GetMapping(value = "/pageList")
    public ResObject pageList(@Valid DictTypePageQueryParam param) {

        Page<DictTypeEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<DictTypeEntity> pageList = dictTypeService.page(page, this.getQueryWrapper(dictTypeMapStruct, param));
        Page<DictTypeVo> dictTypeVoPage = dictTypeMapStruct.toVoList(pageList);
        return R.success(dictTypeVoPage);
    }

    @ApiOperation("字典类型列表")
    @PreAuthorize("hasPermission('/dictType',  'system:dict:query')")
    @GetMapping(value = "/allList")
    public ResObject allList() {
        List<DictTypeEntity> allList = dictTypeService.list();
        List<DictTypeVo> list = dictTypeMapStruct.toVoList(allList);
        return R.success(list);
    }

    /**
     * 获取字典类型
     */
    @ApiOperation("获取字典类型")
    @ApiImplicitParam(name = "dictTypeId", required = true, dataType = "Long", paramType = "path")
    @PreAuthorize("hasPermission('/dictType',  'system:dict:query')")
    @GetMapping("/{dictTypeId}")
    public ResObject get(@PathVariable Long dictTypeId) {
        DictTypeEntity dictType = dictTypeService.getById(dictTypeId);
        return R.success(dictType);
    }

    /**
     * 新增字典类型
     */
    @ApiOperation("新增字典类型")
    @ApiImplicitParam(name = "DictTypeEditParam ", value = "新增字典类型", dataType = "DictTypeEditParam")
    @PreAuthorize("hasPermission('/dictType',  'system:dict:add')")
    @EventLog(message = "新增字典类型", businessType = EventLogEnum.CREATE)
    @PostMapping
    public ResObject save(@Valid @RequestBody DictTypeEditParam dictTypeEditParam) {
        DictTypeEntity dictTypeEntity = dictTypeService.lambdaQuery().eq(DictTypeEntity::getDictCode, dictTypeEditParam.getDictCode()).one();
        if (dictTypeEntity != null) {
            return R.failure("字典编码已存在, 请使用其他字典编码");
        }

        DictTypeEntity dictType = dictTypeMapStruct.toEntity(dictTypeEditParam);
        return R.toRes(dictTypeService.save(dictType));
    }

    /**
     * 修改字典类型
     */
    @ApiOperation("修改字典类型")
    @ApiImplicitParam(name = "DictTypeEditParam ", value = "修改字典类型", dataType = "DictTypeEditParam")
    @PreAuthorize("hasPermission('/dictType',  'system:dict:edit')")
    @EventLog(message = "修改字典类型", businessType = EventLogEnum.UPDATE)
    @PutMapping
    public ResObject edit(@Valid @RequestBody DictTypeEditParam dictTypeEditParam) {
        DictTypeEntity dictTypeEntity = dictTypeService.lambdaQuery().eq(DictTypeEntity::getDictCode, dictTypeEditParam.getDictCode()).one();
        if (dictTypeEntity != null) {
            return R.failure("字典编码已存在, 请使用其他字典编码");
        }

        DictTypeEntity dictType = dictTypeMapStruct.toEntity(dictTypeEditParam);
        return R.toRes(dictTypeService.updateById(dictType));
    }

    /**
     * 删除字典类型
     */
    @ApiOperation("删除字典类型")
    @ApiImplicitParam(name = "dictTypeId", required = true, dataType = "Long", paramType = "path")
    @PreAuthorize("hasPermission('/dictType',  'system:dict:delete')")
    @EventLog(message = "删除字典类型", businessType = EventLogEnum.DELETE)
    @DeleteMapping("/{dictTypeIds}")
    public ResObject delete(@PathVariable Long[] dictTypeIds) {
        return R.toRes(dictTypeService.removeByIds(Arrays.asList(dictTypeIds)));
    }

    /**
     * 导出字典类型
     */
    @ApiOperation("导出字典类型")
    @PreAuthorize("hasPermission('/dictType',  'system:dict:export')")
    @SneakyThrows
    @EventLog(message = "导出字典类型", businessType = EventLogEnum.EXPORT)
    @PostMapping(value = "/exportXls")
    public void exportXls(DictTypePageQueryParam param, HttpServletResponse response) {
        List<DictTypeEntity> list = dictTypeService.list(this.getQueryWrapper(dictTypeMapStruct, param));
        List<DictTypeVo> dictTypeVoList = dictTypeMapStruct.toVoList(list);
        ExcelUtils.exportExcel(dictTypeVoList, DictTypeVo.class, "字典类型", new ExportParams(), response);
    }


    /**
     * 获取字典选择框列表
     */
    @GetMapping("/optionselect")
    public ResObject optionselect() {
        List<DictTypeEntity> dictTypes = dictTypeService.list();
        return R.success(dictTypes);
    }
}
