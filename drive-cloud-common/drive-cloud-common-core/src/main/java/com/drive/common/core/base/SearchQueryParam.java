package com.drive.common.core.base;

import lombok.Data;

@Data
public class SearchQueryParam implements java.io.Serializable {

    // 模糊查询字段
    private String searchKey;
    // 模糊查询value
    private String searchValue;
}
