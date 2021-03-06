package com.drive.system.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.enums.EventLogEnum;
import com.drive.common.log.annotation.EventLog;
import com.drive.common.security.utils.SecurityUtils;
import com.drive.system.pojo.dto.OauthClientDetailsEditParam;
import com.drive.system.pojo.dto.OauthClientDetailsPageQueryParam;
import com.drive.system.pojo.entity.OauthClientDetailsEntity;
import com.drive.system.pojo.vo.OauthClientDetailsVo;
import com.drive.system.service.OauthClientDetailsService;
import com.drive.system.service.mapstruct.OauthClientDetailsMapStruct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;


/**
 * 终端配置表管理
 *
 * @author xiaoguo
 */
@Api(tags = "终端配置表管理")
@Slf4j
@RestController
@RequestMapping("/client")
public class OauthClientDetailsController extends BaseController<OauthClientDetailsPageQueryParam, OauthClientDetailsEntity> {

	@Autowired
	private OauthClientDetailsService oauthClientDetailsService;
	@Autowired
	private OauthClientDetailsMapStruct oauthClientDetailsMapStruct;

	/**
	* 终端配置表 分页列表
	*/
	@ApiOperation("终端配置表分页列表")
	@PreAuthorize("hasPermission('/client',  'system:client:query')")
	@GetMapping(value = "/pageList")
	public ResObject pageList(@Valid OauthClientDetailsPageQueryParam param) {
		param.setSortColumn("client_id");
		Page<OauthClientDetailsEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
		QueryWrapper queryWrapper = this.getQueryWrapper(oauthClientDetailsMapStruct, param);
		queryWrapper.like(StrUtil.isNotEmpty(param.getVagueClientIdSearch()),"client_id",param.getVagueClientIdSearch());
		IPage<OauthClientDetailsEntity> pageList = oauthClientDetailsService.page(page, queryWrapper);
		Page<OauthClientDetailsVo> oauthClientDetailsVoPage = oauthClientDetailsMapStruct.toVoList(pageList);
		return R.success(oauthClientDetailsVoPage);
	}

	/**
	* 获取终端配置表
	*/
	@ApiOperation("获取终端配置表")
	@ApiImplicitParam(name = "clientId", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/client',  'system:client:query')")
	@GetMapping("/{clientId}")
	public ResObject get(@PathVariable String clientId) {
		OauthClientDetailsEntity client = oauthClientDetailsService.getById(clientId);
		return R.success(oauthClientDetailsMapStruct.toVo(client));
	}

	/**
	* 新增终端配置表
	*/
	@ApiOperation("新增终端配置表")
	@ApiImplicitParam(name = "OauthClientDetailsEditParam ", value = "新增终端配置表", dataType = "OauthClientDetailsEditParam")
	@PreAuthorize("hasPermission('/client',  'system:client:add')")
	@EventLog(message = "新增终端配置表", businessType = EventLogEnum.CREATE)
	@PostMapping
	public ResObject save(@Valid @RequestBody OauthClientDetailsEditParam oauthClientDetailsEditParam) {

		OauthClientDetailsEntity entity = oauthClientDetailsService.lambdaQuery().eq(OauthClientDetailsEntity::getClientId, oauthClientDetailsEditParam.getClientId()).one();
		if (entity != null) {
			return R.failure("新增终端ID已存在, 请使用其他编号");
		}
		oauthClientDetailsEditParam.setClientSecret(SecurityUtils.encryptPassword(oauthClientDetailsEditParam.getClientSecret()));
		OauthClientDetailsEntity client = oauthClientDetailsMapStruct.toEntity(oauthClientDetailsEditParam);

		return R.toRes(oauthClientDetailsService.save(client));
	}

	/**
	* 修改终端配置表
	*/
	@ApiOperation("修改终端配置表")
	@ApiImplicitParam(name = "OauthClientDetailsEditParam ", value = "修改终端配置表", dataType = "OauthClientDetailsEditParam")
	@PreAuthorize("hasPermission('/client',  'system:client:edit')")
	@EventLog(message = "修改终端配置表", businessType = EventLogEnum.UPDATE)
	@PutMapping
	public ResObject edit(@Valid @RequestBody OauthClientDetailsEditParam oauthClientDetailsEditParam) {
		OauthClientDetailsEntity client = oauthClientDetailsMapStruct.toEntity(oauthClientDetailsEditParam);
		return R.toRes(oauthClientDetailsService.updateById(client));
	}

	/**
	* 删除终端配置表
	*/
	@ApiOperation("删除终端配置表")
	@ApiImplicitParam(name = "clientId", required = true, dataType = "Long", paramType = "path")
	@PreAuthorize("hasPermission('/client',  'system:client:delete')")
	@EventLog(message = "删除终端配置表", businessType = EventLogEnum.DELETE)
	@DeleteMapping("/{clientIds}")
	public ResObject delete(@PathVariable String[] clientIds) {
		return R.toRes(oauthClientDetailsService.removeByIds(Arrays.asList(clientIds)));
	}

}
