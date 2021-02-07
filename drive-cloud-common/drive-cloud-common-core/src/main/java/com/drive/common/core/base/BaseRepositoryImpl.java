package com.drive.common.core.base;

import cn.hutool.core.util.StrUtil;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;

@Slf4j
public class BaseRepositoryImpl<T,S extends BaseService,Q extends BasePageQueryParam,EDIT> implements BaseRepository<Q,EDIT> {

    @Autowired
    private S baseService;

    @Override
    public ResObject pageList(Q param) {
        log.info(this.getClass() + "pageList方法请求参数{}",param);
        return R.success();
    }

    @Override
    public ResObject findList(Q param) {
        return null;
    }


    @Override
    public ResObject getInfo(Q param) {
        return null;
    }

    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getInfo方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            log.error("数据ID空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }

       // T t=baseService.getById(id);
        return null;
    }

    @Override
    public ResObject save(EDIT installParam) {
        return null;
    }

    @Override
    public ResObject update(EDIT updateParam) {
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
    public ResObject exportXls(Q param, HttpServletResponse response) {
        return null;
    }

    @Override
    public ResObject changeStatus(EDIT param) {
        return null;
    }
}
