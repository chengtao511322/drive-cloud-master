package com.drive.admin.controller;

import com.drive.admin.pojo.dto.StudentInfoEditParam;
import com.drive.admin.pojo.dto.StudentInfoPageQueryParam;
import com.drive.admin.pojo.entity.StudentInfoEntity;
import com.drive.admin.pojo.vo.StudentInfoRpcVo;
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.admin.repository.StudentInfoRepository;
import com.drive.admin.service.StudentInfoService;
import com.drive.admin.service.mapstruct.StudentInfoMapStruct;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.log.annotation.EventLog;
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
import java.util.Map;


/**
 * 学员信息表管理
 *
 * @author xiaoguo
 */
@Api(tags = "学员信息表管理")
@Slf4j
@RestController
@RequestMapping("/studentInfo")
public class StudentInfoController extends BaseController<StudentInfoPageQueryParam, StudentInfoEntity> {

	@Autowired
	private StudentInfoService studentInfoService;
	@Autowired
	private StudentInfoRepository studentInfoRepository;
	@Autowired
	private StudentInfoMapStruct studentInfoMapStruct;

	/**
	 * 新用户列表 分页列表
	 */
	@ApiOperation("新用户列表分页")
	//@PreAuthorize("hasPermission('/admin/studentInfo',  'admin:studentInfo:query')")
	@PostMapping(value = "/newStudentPageList")
	public ResObject newStudentPageList(@Valid @RequestBody StudentInfoPageQueryParam param) {
		return studentInfoRepository.newStudentPageList(param);
	}

	/**
	 * 事务回滚
	 * @return
	 */
	@PostMapping("/activity/reduceInventoryRollback")
	ResObject reduceInventoryRollback(){
		return studentInfoRepository.reduceInventoryRollback();
	}

	/**
	 * 新用户列表 分页列表
	 */
	@ApiOperation("新用户已回访列表分页")
	//@PreAuthorize("hasPermission('/admin/studentInfo',  'admin:studentInfo:query')")
	@PostMapping(value = "/newStudentReturnVisitPageList")
	public ResObject newStudentReturnVisitPageList(@Valid @RequestBody StudentInfoPageQueryParam param) {
		return studentInfoRepository.newStudentReturnVisitPageList(param);
	}


	/**
	* 学员信息表 分页列表
	*/
	@ApiOperation("学员信息表分页列表")
	//@PreAuthorize("hasPermission('/admin/studentInfo',  'admin:studentInfo:query')")
	@PostMapping(value = "/pageList")
	public ResObject pageList(@Valid @RequestBody StudentInfoPageQueryParam param) {
		return studentInfoRepository.pageList(param);
	}


	/**
	* 学员信息表 分页列表
	*/
	@ApiOperation("学员信息表列表")
	//@PreAuthorize("hasPermission('/admin/studentInfo',  'admin:studentInfo:query')")
	@PostMapping(value = "/findList")
	public ResObject findList(@Valid @RequestBody StudentInfoPageQueryParam param) {
		return studentInfoRepository.findList(param);
	}

	/**
	* 获取学员信息表
	*/
	@ApiOperation("获取学员信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/studentInfo',  'admin:studentInfo:query')")
	@GetMapping("/{id}")
	public ResObject<StudentInfoVo> get(@PathVariable String id) {
		return studentInfoRepository.getById(id);
	}


	@ApiOperation("通过ID获取学员信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/studentInfo',  'admin:studentInfo:query')")
	@GetMapping("/getByIdInfo/{id}")
	public ResObject<StudentInfoRpcVo> getByIdInfo(@PathVariable("id") String id) {
		return studentInfoRepository.getByIdInfo(id);
	}
	@ApiOperation("通过ID获取学员信息表")
	@ApiImplicitParam(name = "ids", required = true, dataType = "String", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/studentInfo',  'admin:studentInfo:query')")
	@GetMapping("/batchStudent/{ids}")
	public ResObject<Map<String, StudentInfoRpcVo>> batchStudent(@PathVariable("ids") String[] ids) {
		return studentInfoRepository.batchStudent(ids);
	}

	/**
	* 新增学员信息表
	*/
	@ApiOperation("新增学员信息表")
	@ApiImplicitParam(name = "StudentInfoEditParam ", value = "新增学员信息表", dataType = "StudentInfoEditParam")
	//@PreAuthorize("hasPermission('/admin/studentInfo',  'admin:studentInfo:add')")
	@EventLog(message = "新增学员信息表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody StudentInfoEditParam studentInfoEditParam) {
		return studentInfoRepository.save(studentInfoEditParam);
	}

	/**
	* 修改学员信息表
	*/
	@ApiOperation("修改学员信息表")
	@ApiImplicitParam(name = "StudentInfoEditParam ", value = "修改学员信息表", dataType = "StudentInfoEditParam")
	//@PreAuthorize("hasPermission('/admin/studentInfo',  'admin:studentInfo:edit')")
	@EventLog(message = "修改学员信息表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody StudentInfoEditParam studentInfoEditParam) {
		return studentInfoRepository.update(studentInfoEditParam);
	}

	/**
	* 删除学员信息表
	*/
	@ApiOperation("删除学员信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/studentInfo',  'admin:studentInfo:delete')")
	@EventLog(message = "删除学员信息表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{ids}")
	public ResObject delete(@PathVariable Long[] ids) {
		return R.toRes(studentInfoService.removeByIds(Arrays.asList(ids)));
	}


	@ApiOperation("批量修改学员信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/admin/studentInfo',  'admin:studentInfo:updateBatch')")
	@EventLog(message = "批量修改学员信息表", businessType = EventLogEnum.UPDATE)
	@PutMapping("/updateBatch")
	public ResObject updateBatch(@RequestBody List<StudentInfoEditParam> studentInfoEditParams) {
		return studentInfoRepository.updateBatch(studentInfoEditParams);
	}

	/**
	* 通过主键删除教练信息表
	*/
	@ApiOperation("删除教练信息表")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", paramType = "path")
	//@PreAuthorize("hasPermission('/admin/coachInfo',  'admin:coachInfo:delete')")
	@EventLog(message = "删除教练信息表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/delById/{id}")
	public ResObject delete(@PathVariable String id) {
		return studentInfoRepository.deleteById(id);
	}

	/**
	* 导出学员信息表
	*/
	@ApiOperation("导出学员信息表")
	//@PreAuthorize("hasPermission('/admin/studentInfo',  'admin:studentInfo:export')")
	@SneakyThrows
	@EventLog(message = "导出学员信息表", businessType = EventLogEnum.EXPORT)
	@PostMapping(value = "/exportXls")
	public void exportXls(@RequestBody StudentInfoPageQueryParam param, HttpServletResponse response) {
			studentInfoRepository.exportXls(param,response);
	}


	/**
	* 状态启用/停用
	*/
	@ApiOperation("状态启用/停用学员信息表")
	//@PreAuthorize("hasPermission('/admin/studentInfo',  'admin:studentInfo:changeStatus')")
	@SneakyThrows
	@EventLog(message = "状态启用/停用学员信息表", businessType = EventLogEnum.EXPORT)
	@PostMapping("/changeStatus")
	public ResObject changeStatus(@Valid @RequestBody StudentInfoEditParam studentInfoEditParam) {
		return studentInfoRepository.changeStatus(studentInfoEditParam);
	}

}
