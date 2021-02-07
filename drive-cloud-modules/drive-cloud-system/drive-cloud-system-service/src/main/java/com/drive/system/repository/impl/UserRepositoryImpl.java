package com.drive.system.repository.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.datascope.annotation.DataScope;
import com.drive.system.pojo.dto.UserEditParam;
import com.drive.system.pojo.dto.UserPageQueryParam;
import com.drive.system.pojo.entity.UserEntity;
import com.drive.system.pojo.vo.UserVo;
import com.drive.system.repository.UserRepository;
import com.drive.system.service.UserService;
import com.drive.system.service.mapstruct.UserMapStruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@Slf4j
public class UserRepositoryImpl extends BaseController implements UserRepository {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapStruct userMapStruct;

    @DataScope(deptAlias = "d", userAlias = "u")
    @Override
    public ResObject pageList(UserPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<UserEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<UserEntity> pageList = userService.page(page, this.getQueryWrapper(userMapStruct, param));
        Page<UserVo> userVoPage = userMapStruct.toVoList(pageList);
        log.info(this.getClass() + "pageList-方法请求结果{}",userVoPage);
        return R.success(userVoPage);
    }

    @Override
    public ResObject findList(UserPageQueryParam param) {
        return null;
    }

    @Override
    public ResObject getById(String id) {
        return null;
    }

    @Override
    public ResObject getInfo(UserPageQueryParam param) {
        return null;
    }


    @Override
    public ResObject save(UserEditParam installParam) {
        return null;
    }

    @Override
    public ResObject update(UserEditParam updateParam) {
        return null;
    }

    @Override
    public ResObject deleteByIds(String[] ids) {
        return null;
    }

    @Override
    public ResObject deleteById(String id) {
        return null;
    }

    @Override
    public ResObject exportXls(UserPageQueryParam param, HttpServletResponse response) throws IOException {
        return null;
    }

    @Override
    public ResObject changeStatus(UserEditParam param) {
        return null;
    }
}
