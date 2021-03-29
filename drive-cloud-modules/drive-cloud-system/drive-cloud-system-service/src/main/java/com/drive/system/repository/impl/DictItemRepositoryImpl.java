package com.drive.system.repository.impl;

import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.system.pojo.dto.DictItemEditParam;
import com.drive.system.pojo.dto.DictItemPageQueryParam;
import com.drive.system.pojo.entity.DictTypeEntity;
import com.drive.system.pojo.vo.DictItemVo;
import com.drive.system.pojo.vo.DictTypeVo;
import com.drive.system.repository.DictItemRepository;
import com.drive.system.service.DictItemService;
import com.drive.system.service.DictTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DictItemRepositoryImpl extends BaseController implements DictItemRepository {

    @Autowired
    private DictItemService dictItemService;

    @Autowired
    private DictTypeService dictTypeService;

    @Override
    public ResObject pageList(DictItemPageQueryParam param) {
        return null;
    }

    @Override
    public ResObject findList(DictItemPageQueryParam param) {
        return null;
    }

    @Override
    public ResObject getById(String id) {
        return null;
    }

    @Override
    public ResObject getInfo(DictItemPageQueryParam param) {
        return null;
    }



    @Override
    public ResObject save(DictItemEditParam installParam) {
        return null;
    }

    @Override
    public ResObject update(DictItemEditParam updateParam) {
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
    public ResObject exportXls(DictItemPageQueryParam param, HttpServletResponse response) throws IOException {
        return null;
    }

    @Override
    public ResObject changeStatus(DictItemEditParam param) {
        return null;
    }

    // @Cacheable(value = "redisCache", key = "dictItem")
    @Override
    public ResObject allList() {
        log.info(this.getClass() + "allList-请求参数{}");
        // 查询所有数据重新组装
        List<DictTypeEntity> dictTypeList = dictTypeService.list();
        List<DictTypeVo> dictTypeVoList = BeanConvertUtils.copyList(dictTypeList,DictTypeVo.class);

        List<Map> dictItemList =new ArrayList<Map>();

        dictTypeVoList.stream().forEach((item) ->{
            item.setDictItemVoList(BeanConvertUtils.copyList(dictItemService.getItemByDictCode(item.getDictCode()), DictItemVo.class));
        });
        return R.success(dictTypeVoList);
    }
}
