package com.drive.monitor.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.core.utils.StringUtils;
import com.drive.common.data.utils.ExcelUtils;
import com.drive.common.log.annotation.EventLog;
import com.drive.monitor.pojo.dto.OperLogEditParam;
import com.drive.monitor.pojo.dto.OperLogPageQueryParam;
import com.drive.monitor.pojo.entity.OperLogEntity;
import com.drive.monitor.pojo.vo.OperLogVo;
import com.drive.monitor.service.OperLogService;
import com.drive.monitor.service.mapstruct.OperLogMapStruct;
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
 * 操作日志记录管理
 *
 * @author DreamChan
 */
@Api(tags = "操作日志记录管理")
@Slf4j
@RestController
@RequestMapping("/operLog")
public class OperLogController extends BaseController<OperLogPageQueryParam, OperLogEntity> {

    @Autowired
    private OperLogService operLogService;
    @Autowired
    private OperLogMapStruct operLogMapStruct;

    /**
     * 操作日志记录 分页列表
     */
    @ApiOperation("操作日志记录分页列表")
    @PreAuthorize("hasPermission('/operLog', 'monitor:operlog:query')")
    @GetMapping(value = "/pageList")
    public ResObject pageList(@Valid OperLogPageQueryParam param) {
        Page<OperLogEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper queryWrapper = this.getQueryWrapper(operLogMapStruct, param);
        if(StringUtils.isNotBlank(param.getBeginTime()) || StringUtils.isNotBlank(param.getEndTime())){
            queryWrapper.between("request_date", param.getBeginTime(), param.getEndTime());
        }

        IPage<OperLogEntity> pageList = operLogService.page(page, queryWrapper);
        Page<OperLogVo> operLogVoPage = operLogMapStruct.toVoList(pageList);
        return R.success(operLogVoPage);
    }

    /**
     * 获取操作日志记录
     */
    @ApiOperation("获取操作日志记录")
    @ApiImplicitParam(name = "operLogId", required = true, dataType = "Long", paramType = "path")
    @PreAuthorize("hasPermission('/operLog',  'monitor:operlog:query')")
    @GetMapping("/{operLogId}")
    public ResObject get(@PathVariable Long operLogId) {
        OperLogEntity operLog = operLogService.getById(operLogId);
        return R.success(operLogMapStruct.toVo(operLog));
    }

    /**
     * 新增操作日志记录
     */
    @ApiOperation("新增操作日志记录")
    @ApiImplicitParam(name = "OperLogEditParam ", value = "新增操作日志记录", dataType = "OperLogEditParam")
//    @PreAuthorize("hasPermission('/operLog',  'monitor:operlog:add')")
//    @EventLog(message = "新增操作日志记录", businessType = EventLogEnum.CREATE)
    @PostMapping
    public ResObject save(@Valid @RequestBody OperLogEditParam operLogEditParam) {
        OperLogEntity operLog = operLogMapStruct.toEntity(operLogEditParam);
        operLogService.save(operLog);
        return R.success();
    }

    /**
     * 修改操作日志记录
     */
    @ApiOperation("修改操作日志记录")
    @ApiImplicitParam(name = "OperLogEditParam ", value = "修改操作日志记录", dataType = "OperLogEditParam")
    @PreAuthorize("hasPermission('/operLog',  'monitor:operlog:edit')")
    @EventLog(message = "修改操作日志记录", businessType = EventLogEnum.UPDATE)
    @PutMapping
    public ResObject edit(@Valid @RequestBody OperLogEditParam operLogEditParam) {
        OperLogEntity operLog = operLogMapStruct.toEntity(operLogEditParam);
        operLogService.updateById(operLog);
        return R.success();
    }

    /**
     * 删除操作日志记录
     */
    @ApiOperation("删除操作日志记录")
    @ApiImplicitParam(name = "operLogId", required = true, dataType = "Long", paramType = "path")
    @PreAuthorize("hasPermission('/operLog',  'monitor:operlog:delete')")
    @EventLog(message = "删除操作日志记录", businessType = EventLogEnum.DELETE)
    @DeleteMapping("/{operLogIds}")
    public ResObject delete(@PathVariable Long[] operLogIds) {
        operLogService.removeByIds(Arrays.asList(operLogIds));
        return R.success();
    }

    /**
     * 导出操作日志记录
     */
    @ApiOperation("导出操作日志记录")
    @PreAuthorize("hasPermission('/operLog',  'monitor:operlog:export')")
    @SneakyThrows
    @EventLog(message = "导出操作日志记录", businessType = EventLogEnum.EXPORT)
    @GetMapping(value = "/exportXls")
    public void exportXls(OperLogPageQueryParam param, HttpServletResponse response) {
        List<OperLogEntity> list = operLogService.list(this.getQueryWrapper(operLogMapStruct, param));
        List<OperLogVo> operLogVoList = operLogMapStruct.toVoList(list);
        ExcelUtils.exportExcel(operLogVoList, OperLogVo.class, "操作日志记录", new ExportParams(), response);
    }

}
