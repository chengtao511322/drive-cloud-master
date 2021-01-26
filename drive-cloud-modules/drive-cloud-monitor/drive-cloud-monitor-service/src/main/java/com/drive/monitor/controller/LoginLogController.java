package com.drive.monitor.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.data.utils.ExcelUtils;
import com.drive.common.log.annotation.EventLog;
import com.drive.monitor.pojo.dto.LoginLogEditParam;
import com.drive.monitor.pojo.dto.LoginLogPageQueryParam;
import com.drive.monitor.pojo.entity.LoginLogEntity;
import com.drive.monitor.pojo.vo.LoginLogVo;
import com.drive.monitor.service.LoginLogService;
import com.drive.monitor.service.mapstruct.LoginLogMapStruct;
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
 * 系统访问记录管理
 *
 * @author DreamChan
 */
@Api(tags = "系统访问记录管理")
@Slf4j
@RestController
@RequestMapping("/loginLog")
public class LoginLogController extends BaseController<LoginLogPageQueryParam, LoginLogEntity> {

    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private LoginLogMapStruct loginLogMapStruct;

    /**
     * 系统访问记录 分页列表
     */
    @ApiOperation("系统访问记录分页列表")
    @PreAuthorize("hasPermission('/loginLog',  'monitor:loginlog:query')")
    @GetMapping(value = "/pageList")
    public ResObject pageList(@Valid LoginLogPageQueryParam param) {
        Page<LoginLogEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<LoginLogEntity> pageList = loginLogService.page(page, this.getQueryWrapper(loginLogMapStruct, param));
        Page<LoginLogVo> loginLogVoPage = loginLogMapStruct.toVoList(pageList);
        return R.success(loginLogVoPage);
    }

    /**
     * 获取系统访问记录
     */
    @ApiOperation("获取系统访问记录")
    @ApiImplicitParam(name = "loginLogId", required = true, dataType = "Long", paramType = "path")
    @PreAuthorize("hasPermission('/loginLog',  'monitor:loginlog:query')")
    @GetMapping("/{loginLogId}")
    public ResObject get(@PathVariable Long loginLogId) {
        LoginLogEntity loginLog = loginLogService.getById(loginLogId);
        return R.success(loginLogMapStruct.toVo(loginLog));
    }

    /**
     * 新增系统访问记录
     */
    @ApiOperation("新增系统访问记录")
    @ApiImplicitParam(name = "LoginLogEditParam ", value = "新增系统访问记录", dataType = "LoginLogEditParam")
//    @PreAuthorize("hasPermission('/loginLog',  'monitor:loginlog:add')")
//    @EventLog(message = "新增系统访问记录", businessType = EventLogEnum.CREATE)
    @PostMapping
    public ResObject save(@Valid @RequestBody LoginLogEditParam loginLogEditParam) {
        LoginLogEntity loginLog = loginLogMapStruct.toEntity(loginLogEditParam);
        loginLogService.save(loginLog);
        return R.success();
    }

    /**
     * 修改系统访问记录
     */
    @ApiOperation("修改系统访问记录")
    @ApiImplicitParam(name = "LoginLogEditParam ", value = "修改系统访问记录", dataType = "LoginLogEditParam")
    @PreAuthorize("hasPermission('/loginLog',  'monitor:loginlog:edit')")
    @EventLog(message = "修改系统访问记录", businessType = EventLogEnum.UPDATE)
    @PutMapping
    public ResObject edit(@Valid @RequestBody LoginLogEditParam loginLogEditParam) {
        LoginLogEntity loginLog = loginLogMapStruct.toEntity(loginLogEditParam);
        loginLogService.updateById(loginLog);
        return R.success();
    }

    /**
     * 删除系统访问记录
     */
    @ApiOperation("删除系统访问记录")
    @ApiImplicitParam(name = "loginLogId", required = true, dataType = "Long", paramType = "path")
    @PreAuthorize("hasPermission('/loginLog',  'monitor:loginlog:delete')")
    @EventLog(message = "删除系统访问记录", businessType = EventLogEnum.DELETE)
    @DeleteMapping("/{loginLogIds}")
    public ResObject delete(@PathVariable Long[] loginLogIds) {
        loginLogService.removeByIds(Arrays.asList(loginLogIds));
        return R.success();
    }

    /**
     * 导出系统访问记录
     */
    @ApiOperation("导出系统访问记录")
    @PreAuthorize("hasPermission('/loginLog',  'monitor:loginlog:export')")
    @SneakyThrows
    @EventLog(message = "导出系统访问记录", businessType = EventLogEnum.EXPORT)
    @PostMapping(value = "/exportXls")
    public void exportXls(LoginLogPageQueryParam param, HttpServletResponse response) {
        List<LoginLogEntity> list = loginLogService.list(this.getQueryWrapper(loginLogMapStruct, param));
        List<LoginLogVo> loginLogVoList = loginLogMapStruct.toVoList(list);
        ExcelUtils.exportExcel(loginLogVoList, LoginLogVo.class, "系统访问记录", new ExportParams(), response);
    }

}
