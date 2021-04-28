package com.drive.test;

import com.alibaba.fastjson.JSONObject;
import com.drive.admin.pojo.vo.AreaVo;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $
 * @Param $
 * @return $
 **/
@Slf4j
public class redisTest {

    public static void main(String[] args) throws InterruptedException {
        //System.out.println(ApplicationContextUtil.getBean(RedisService.class));;
        String res = "{\"baCode\":\"110101\",\"baName\":\"东城区\",\"baParentId\":\"110100\"}";
        System.out.println(JSONObject.parseObject(res,AreaVo.class));
    }
}
