package com.drive.marketing.testMar;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.drive.admin.pojo.vo.CoachInfoVo;
import com.drive.common.core.enums.StatusEnum;
import com.drive.common.core.exception.BizException;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $
 * @Param $
 * @return $
 **/
public class MarketingTest {

    public static void main(String[] args) {
        CoachInfoVo coachInfoVo = null;
        //coachInfoVo.setId(IdWorker.getIdStr());
        //Optional.ofNullable(coachInfoVo).orElseThrow(()-> new BizException("取指错误"));
        /*Map<StatusEnum,Function<> action> actionMappings = new HashMap<>();
        // 这里泛型 ? 是为方便演示，实际可替换为你需要的类型
        // 初始化
        actionMappings.put(value1, (someParams) -> { doAction1(someParams)});
        actionMappings.put(value2, (someParams) -> { doAction2(someParams)});
        actionMappings.put(value3, (someParams) -> { doAction3(someParams)});
// 省略多余逻辑语句
        actionMappings.get(param).apply(someParams);*/

    }
}
