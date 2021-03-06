package com.drive.system.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.api.RemoteCodeFeignService;
import com.drive.admin.pojo.dto.CodePageQueryParam;
import com.drive.admin.pojo.vo.CodeVo;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResCodeEnum;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.data.utils.ExcelUtils;
import com.drive.common.log.annotation.EventLog;
import com.drive.system.pojo.dto.DeptEditParam;
import com.drive.system.pojo.dto.DeptPageQueryParam;
import com.drive.system.pojo.entity.DeptEntity;
import com.drive.system.pojo.entity.DictItemEntity;
import com.drive.system.pojo.entity.RoleDeptEntity;
import com.drive.system.pojo.vo.DeptVo;
import com.drive.system.pojo.vo.RoleDeptVo;
import com.drive.system.repository.UserRepository;
import com.drive.system.service.DeptService;
import com.drive.system.service.DictItemService;
import com.drive.system.service.RoleDeptService;
import com.drive.system.service.mapstruct.DeptMapStruct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * ????????????
 *
 * @author xiaoguo
 */
@Api(tags = "????????????")
@Slf4j
@RestController
@RequestMapping("/dept")
public class DeptController extends BaseController<DeptPageQueryParam, DeptEntity> {

    @Autowired
    private DeptService deptService;
    @Autowired
    private DeptMapStruct deptMapStruct;

    @Autowired
    private RoleDeptService roleDeptService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RemoteCodeFeignService remoteCodeFeignService;

    @Autowired
    private DictItemService dictItemService;

    /**
     * ?????? ????????????
     */
    @ApiOperation("??????????????????")
    @PreAuthorize("hasPermission('/dept',  'system:dept:query')")
    @GetMapping(value = "/pageList")
    public ResObject<Page<DeptVo>> pageList(@Valid DeptPageQueryParam param) {
        Page<DeptEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<DeptEntity> pageList = deptService.page(page, this.getQueryWrapper(deptMapStruct, param));
        Page<DeptVo> deptVoPage = deptMapStruct.toVoList(pageList);
        return R.success(deptVoPage);
    }
    @ApiOperation("????????????")
    @Transactional
    @GetMapping(value = "/synData")
    public ResObject synData() {
        CodePageQueryParam codePageQueryParam = new CodePageQueryParam();
        codePageQueryParam.setCategory("TRADE_TYPE");
        ResObject<List<CodeVo>> codeVoResObject = remoteCodeFeignService.findList(codePageQueryParam);
        List<CodeVo> codeVoList = null;
        if (codeVoResObject.getCode().equals(ResCodeEnum.SUCCESS.getCode())){
            codeVoList = codeVoResObject.getData();
        }
        codeVoList.stream().forEach((item) ->{
            DictItemEntity dictItem = new DictItemEntity();
            dictItem.setDictCode("trade_type");
            dictItem.setItemName(item.getDisText());
            dictItem.setItemValue(item.getCodeValue());
            dictItem.setStatus("0");
            dictItemService.save(dictItem);
        });
        return R.success();
    }


    /**
     * ????????????
     */
    @ApiOperation("????????????")
    @PreAuthorize("hasPermission('/dept',  'system:dept:query')")
    @GetMapping(value = "/allList")
    public ResObject<List<DeptVo>> allList() {
        List<DeptEntity> allList = deptService.list();
        List<DeptVo> allVoList = deptMapStruct.toVoList(allList);
        return R.success(allVoList);
    }

    /**
     * ????????????
     */
    @ApiOperation("????????????")
    @ApiImplicitParam(name = "deptId", required = true, dataType = "Long", paramType = "path")
    @PreAuthorize("hasPermission('/dept',  'system:dept:query')")
    @GetMapping("/{deptId}")
    public ResObject<DeptEntity> get(@PathVariable Long deptId) {
        DeptEntity dept = deptService.getById(deptId);
        return R.success(dept);
    }
    /**
     * ????????????
     */
    @ApiOperation("????????????")
    @ApiImplicitParam(name = "deptId", required = true, dataType = "Long", paramType = "path")
    @PreAuthorize("hasPermission('/dept',  'system:dept:query')")
    @GetMapping("/getDeptByRoleId/{roleId}")
    public ResObject<RoleDeptVo> getDeptByRoleId(@PathVariable Long roleId) {
        if (roleId == null){
            return R.failure(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id",roleId);
        List<RoleDeptEntity> roleDeptList = roleDeptService.list(queryWrapper);
        return R.success(BeanConvertUtils.copyList(roleDeptList,com.drive.system.pojo.vo.RoleDeptVo.class));
    }

    /**
     * ????????????
     */
    @ApiOperation("????????????")
    @ApiImplicitParam(name = "DeptEditParam ", value = "????????????", dataType = "DeptEditParam")
    @PreAuthorize("hasPermission('/dept',  'system:dept:add')")
    @EventLog(message = "????????????", businessType = EventLogEnum.CREATE)
    @PostMapping
    public ResObject save(@Valid @RequestBody DeptEditParam deptEditParam) {
        DeptEntity deptEntity = deptService.lambdaQuery().eq(DeptEntity::getDeptName, deptEditParam.getDeptName())
                                    .eq(DeptEntity::getParentId, deptEditParam.getParentId()).one();
        if (deptEntity != null) {
            return R.failure("?????????????????????, ???????????????????????????");
        }

        DeptEntity dept = deptMapStruct.toEntity(deptEditParam);
        return R.toRes(deptService.insertDept(dept));
    }

    /**
     * ????????????
     */
    @ApiOperation("????????????")
    @ApiImplicitParam(name = "DeptEditParam ", value = "????????????", dataType = "DeptEditParam")
    @PreAuthorize("hasPermission('/dept',  'system:dept:edit')")
    @EventLog(message = "????????????", businessType = EventLogEnum.UPDATE)
    @PutMapping
    public ResObject edit(@Valid @RequestBody DeptEditParam deptEditParam) {
        DeptEntity dept = deptMapStruct.toEntity(deptEditParam);
        return R.toRes(deptService.updateById(dept));
    }

    /**
     * ????????????
     */
    @ApiOperation("????????????")
    @ApiImplicitParam(name = "deptId", required = true, dataType = "Long", paramType = "path")
    @PreAuthorize("hasPermission('/dept',  'system:dept:delete')")
    @EventLog(message = "????????????", businessType = EventLogEnum.DELETE)
    @DeleteMapping("/{deptIds}")
    public ResObject delete(@PathVariable Long[] deptIds) {
        return R.toRes(deptService.removeByIds(Arrays.asList(deptIds)));
    }

    /**
     * ????????????
     */
    @ApiOperation("????????????")
    @PreAuthorize("hasPermission('/dept',  'system:dept:export')")
    @SneakyThrows
    @EventLog(message = "????????????", businessType = EventLogEnum.EXPORT)
    @PostMapping(value = "/exportXls")
    public void exportXls(DeptPageQueryParam param, HttpServletResponse response) {
        List<DeptEntity> list = deptService.list(this.getQueryWrapper(deptMapStruct, param));
        List<DeptVo> deptVoList = deptMapStruct.toVoList(list);
        ExcelUtils.exportExcel(deptVoList, DeptVo.class, "??????", new ExportParams(), response);
    }

    /**
     * ???????????????????????????
     */
    @GetMapping("/treeselect")
    public ResObject treeselect(@Valid DeptPageQueryParam param) {
        List<DeptEntity> depts = deptService.list(this.getQueryWrapper(deptMapStruct, param));
        List<DeptVo> deptVos = deptMapStruct.toVoList(depts);
        return R.success(deptService.buildDeptTreeSelect(deptVos));
    }

    /**
     * ?????????????????????????????????
     */
    @GetMapping(value = "/roleDeptTreeselect/{roleId}")
    public ResObject roleDeptTreeselect(@PathVariable("roleId") Long roleId) {
        List<DeptEntity> list = deptService.list();
        List<DeptVo> deptVos = deptMapStruct.toVoList(list);

        Map result = new HashMap();
        result.put("checkedKeys", deptService.selectDeptListByRoleId(roleId));
        result.put("depts", deptService.buildDeptTreeSelect(deptVos));
        return R.success(result);
    }

    /**
     * ???????????????
     * @return
     */
    @GetMapping(value = "/initCache")
    @Async
    public ResObject initCache(){
        deptService.init();
        roleDeptService.init();
        userRepository.init();
        return R.success();
    }

}
