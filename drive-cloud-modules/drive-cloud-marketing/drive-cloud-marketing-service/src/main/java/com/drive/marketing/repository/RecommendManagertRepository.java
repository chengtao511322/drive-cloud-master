package com.drive.marketing.repository;

import cn.hutool.json.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drive.marketing.pojo.dto.RecommendUserPageQueryParam;

/**
 * 渠道经理
 */
public interface RecommendManagertRepository {

    /**
     * 通过Id查询
     * @param param
     */
    JSONObject getRecommendManagert(String managerId);


    JSONObject getRecommendUser(String ercommendUserId);


    JSONArray recommendUserList(RecommendUserPageQueryParam param);
}
