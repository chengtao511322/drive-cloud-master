package com.drive.common.core.base;

import com.drive.common.core.constant.Constants;
import lombok.Data;

/**
 * PageQueryParam 基类
 *
 * @author xiaoguo
 */
@Data
public abstract class BasePageQueryParam implements java.io.Serializable{

    // 起始页数
    private Integer pageNum = 0;

    private Integer pageNo = 0;

    // 每页显示记录数
    private Integer pageSize = 10;

    // 开始时间
    private String beginTime;

    // 结束时间
    private String endTime;

    // 排序字段
    private String sortColumn = Constants.DEFAULT_SORT_COLUMN;

    // 排序方式
    private Boolean isAsc = Constants.DEFAULT_IS_ASC;

    // 模糊查询 key
    private String vagueSearch;

    private String vaguePhoneSearch;
    private String vaguePreSalesServiceNameSearch;
    private String vagueUserNameSearch ;
    private String vagueRealNameSearch;
    private String vagueEmailSearch;

    private SearchQueryParam searchQueryParam;

}
