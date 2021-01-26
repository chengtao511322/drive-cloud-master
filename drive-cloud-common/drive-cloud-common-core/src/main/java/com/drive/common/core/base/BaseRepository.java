package com.drive.common.core.base;

import com.drive.common.core.biz.ResObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface BaseRepository<P,EDIT> {

    /**
     * 分页
     * @param param
     * @return
     */
    ResObject pageList(P param);

    /**
     * 查询数据列表
     * @param param
     * @return
     */
    ResObject findList(P param);

    /**
     * 通过ID获取数据
     * @param id
     * @return
     */
    ResObject getInfo(String  id);

    /**
     * 保存
     * @param
     * @return
     */
    ResObject save(EDIT installParam);

    /**
     * 修改
     * @param
     * @return
     */
    ResObject update(EDIT updateParam);


    /**
     * 删除多少
     * @param ids
     * @return
     */
    ResObject deleteByIds(String[] ids);

    /**
     * 删除单个
     * @param id
     * @return
     */
    ResObject deleteById(String id);


    /**
     * 导出
     * @param param
     * @param response
     * @return
     */
    ResObject exportXls(P param, HttpServletResponse response) throws IOException;

    /**
     * 状态修改
     * @param param
     * @return
     */
    ResObject changeStatus(EDIT param);
}
