package com.drive.common.core.base;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.common.core.utils.StringUtils;

import java.io.Serializable;

/**
 * Controller 基类
 *
 * @author xiaoguo
 */
public class BaseController<Q extends BasePageQueryParam, E extends Serializable> {


    /**
     * PostMapping("/xx/xxx")
     * 传的参是@RequestBody，多参或对对象
     *
     *
     * GetMapping("/xx/xxx")
     * 无参时
     * 传的参是@RequestParam
     * 传的参是@PathVariable
     */

    /**
     * 封装查询参数
     */
    public QueryWrapper getQueryWrapper(BaseMapStruct baseMapStruct, Q param) {
        QueryWrapper<E> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity((E) baseMapStruct.pageQueryParamToEntity(param));
        String sortColumn = param.getSortColumn();
        String underSortColumn = StringUtils.lowerCamelToLowerUnderscore(sortColumn);
        if (param.getIsAsc()) {
            queryWrapper.orderByAsc(underSortColumn);
        } else {
            queryWrapper.orderByDesc(underSortColumn);
        }
        return queryWrapper;
    }


}
