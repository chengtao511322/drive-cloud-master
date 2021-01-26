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
import com.drive.system.pojo.dto.PostEditParam;
import com.drive.system.pojo.dto.PostPageQueryParam;
import com.drive.system.pojo.entity.PostEntity;
import com.drive.system.pojo.vo.PostVo;
import com.drive.system.service.PostService;
import com.drive.system.service.mapstruct.PostMapStruct;
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
 * 岗位信息管理
 *
 * @author xiaoguo
 */
@Api(tags = "岗位管理")
@Slf4j
@RestController
@RequestMapping("/post")
public class PostController extends BaseController<PostPageQueryParam, PostEntity> {

    @Autowired
    private PostService postService;
    @Autowired
    private PostMapStruct postMapStruct;

    /**
     * 岗位信息 分页列表
     */
    @ApiOperation("岗位信息分页列表")
    @PreAuthorize("hasPermission('/post',  'system:post:query')")
    @GetMapping(value = "/pageList")
    public ResObject pageList(@Valid PostPageQueryParam param) {

        Page<PostEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<PostEntity> pageList = postService.page(page, this.getQueryWrapper(postMapStruct, param));
        Page<PostVo> postVoPage = postMapStruct.toVoList(pageList);
        return R.success(postVoPage);
    }

    /**
     * 获取岗位信息
     */
    @ApiOperation("获取岗位信息")
    @ApiImplicitParam(name = "postId", required = true, dataType = "Long", paramType = "path")
    @PreAuthorize("hasPermission('/post',  'system:post:query')")
    @GetMapping("/{postId}")
    public ResObject get(@PathVariable Long postId) {
        PostEntity post = postService.getById(postId);
        return R.success(post);
    }

    /**
     * 新增岗位信息
     */
    @ApiOperation("新增岗位信息")
    @ApiImplicitParam(name = "PostEditParam ", value = "新增岗位信息", dataType = "PostEditParam")
    @PreAuthorize("hasPermission('/post',  'system:post:add')")
    @EventLog(message = "新增岗位信息", businessType = EventLogEnum.CREATE)
    @PostMapping
    public ResObject save(@Valid @RequestBody PostEditParam postEditParam) {
        PostEntity postEntity = postService.lambdaQuery().eq(PostEntity::getPostCode, postEditParam.getPostCode()).one();
        if (postEntity != null) {
            return R.failure("岗位编码已存在, 请使用其他岗位编码");
        }

        PostEntity post = postMapStruct.toEntity(postEditParam);
        return R.toRes(postService.save(post));
    }

    /**
     * 修改岗位信息
     */
    @ApiOperation("修改岗位信息")
    @ApiImplicitParam(name = "PostEditParam ", value = "修改岗位信息", dataType = "PostEditParam")
    @PreAuthorize("hasPermission('/post',  'system:post:edit')")
    @EventLog(message = "修改岗位信息", businessType = EventLogEnum.UPDATE)
    @PutMapping
    public ResObject edit(@Valid @RequestBody PostEditParam postEditParam) {
        PostEntity postEntity = postService.lambdaQuery().eq(PostEntity::getPostCode, postEditParam.getPostCode()).one();
        if (postEntity != null) {
            return R.failure("岗位编码已存在, 请使用其他岗位编码");
        }

        PostEntity post = postMapStruct.toEntity(postEditParam);
        return R.toRes(postService.updateById(post));
    }

    /**
     * 删除岗位信息
     */
    @ApiOperation("删除岗位信息")
    @ApiImplicitParam(name = "postId", required = true, dataType = "Long", paramType = "path")
    @PreAuthorize("hasPermission('/post',  'system:post:delete')")
    @EventLog(message = "删除岗位信息", businessType = EventLogEnum.DELETE)
    @DeleteMapping("/{postIds}")
    public ResObject delete(@PathVariable Long[] postIds) {
        return R.toRes(postService.removeByIds(Arrays.asList(postIds)));
    }

    /**
     * 导出岗位信息
     */
    @ApiOperation("导出岗位信息")
    @PreAuthorize("hasPermission('/post',  'system:post:export')")
    @SneakyThrows
    @EventLog(message = "导出岗位信息", businessType = EventLogEnum.EXPORT)
    @PostMapping(value = "/exportXls")
    public void exportXls(PostPageQueryParam param, HttpServletResponse response) {
        List<PostEntity> list = postService.list(this.getQueryWrapper(postMapStruct, param));
        List<PostVo> postVoList = postMapStruct.toVoList(list);
        ExcelUtils.exportExcel(postVoList, PostVo.class, "岗位信息", new ExportParams(), response);
    }

    /**
     * 获取岗位选择框列表
     */
    @GetMapping("/optionselect")
    public ResObject optionselect() {
        List<PostEntity> posts = postService.list();
        return R.success(postMapStruct.toVoList(posts));
    }
}
