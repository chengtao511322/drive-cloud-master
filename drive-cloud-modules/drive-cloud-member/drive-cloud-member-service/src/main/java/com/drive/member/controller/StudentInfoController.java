package com.drive.member.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.data.utils.ExcelUtils;
import com.drive.common.log.annotation.EventLog;
import com.drive.member.pojo.dto.StudentInfoEditParam;
import com.drive.member.pojo.dto.StudentInfoPageQueryParam;
import com.drive.member.pojo.entity.StudentInfoEntity;
import com.drive.member.pojo.vo.StudentInfoVo;
import com.drive.member.service.StudentInfoService;
import com.drive.member.service.mapstruct.StudentInfoMapStruct;
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
 * 学员信息表管理
 *
 * @author xiaoguo
 */
@Api(tags = "学员信息表管理")
@Slf4j
@RestController
@RequestMapping("/student")
public class StudentInfoController extends BaseController<StudentInfoPageQueryParam, StudentInfoEntity> {

	@Autowired
	private StudentInfoService studentInfoService;
	@Autowired
	private StudentInfoMapStruct studentInfoMapStruct;

	/**
	* 学员信息表 分页列表
	*/
	@ApiOperation("学员信息表分页列表")
	@PreAuthorize("hasPermission('/member/studentInfo',  'member:studentInfo:query')")
	@GetMapping(value = "/pageList")
	public ResObject pageList(@Valid StudentInfoPageQueryParam param) {

		Page<StudentInfoEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
		IPage<StudentInfoEntity> pageList = studentInfoService.page(page, this.getQueryWrapper(studentInfoMapStruct, param));
		Page<StudentInfoVo> studentInfoVoPage = studentInfoMapStruct.toVoList(pageList);
		return R.success(studentInfoVoPage);
	}

	/**
	* 获取学员信息表
	*/
	@ApiOperation("获取学员信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/member/studentInfo',  'member:studentInfo:query')")
	@GetMapping("/{id}")
	public ResObject get(@PathVariable String id) {
		StudentInfoEntity studentInfo = studentInfoService.getById(id);
		return R.success(studentInfoMapStruct.toVo(studentInfo));
	}

	/**
	* 新增学员信息表
	*/
	@ApiOperation("新增学员信息表")
	@ApiImplicitParam(name = "StudentInfoEditParam ", value = "新增学员信息表", dataType = "StudentInfoEditParam")
	@PreAuthorize("hasPermission('/member/studentInfo',  'member:studentInfo:add')")
	@EventLog(message = "新增学员信息表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody StudentInfoEditParam studentInfoEditParam) {
		StudentInfoEntity studentInfo = studentInfoMapStruct.toEntity(studentInfoEditParam);
		return R.toRes(studentInfoService.save(studentInfo));
	}

	/**
	* 修改学员信息表
	*/
	@ApiOperation("修改学员信息表")
	@ApiImplicitParam(name = "StudentInfoEditParam ", value = "修改学员信息表", dataType = "StudentInfoEditParam")
	@PreAuthorize("hasPermission('/member/studentInfo',  'member:studentInfo:edit')")
	@EventLog(message = "修改学员信息表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody StudentInfoEditParam studentInfoEditParam) {
		StudentInfoEntity studentInfo = studentInfoMapStruct.toEntity(studentInfoEditParam);
		return R.toRes(studentInfoService.updateById(studentInfo));
	}

	/**
	* 删除学员信息表
	*/
	@ApiOperation("删除学员信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/member/studentInfo',  'member:studentInfo:delete')")
	@EventLog(message = "删除学员信息表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(studentInfoService.removeByIds(Arrays.asList(ids)));
	}

	/**
	* 导出学员信息表
	*/
	@ApiOperation("导出学员信息表")
	@PreAuthorize("hasPermission('/member/studentInfo',  'member:studentInfo:export')")
	@SneakyThrows
	@EventLog(message = "导出学员信息表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(StudentInfoPageQueryParam param, HttpServletResponse response) {
		List<StudentInfoEntity> list = studentInfoService.list(this.getQueryWrapper(studentInfoMapStruct, param));
		List<StudentInfoVo> studentInfoVoList = studentInfoMapStruct.toVoList(list);
		ExcelUtils.exportExcel(studentInfoVoList, StudentInfoVo.class, "学员信息表", new ExportParams(), response);
	}

}
