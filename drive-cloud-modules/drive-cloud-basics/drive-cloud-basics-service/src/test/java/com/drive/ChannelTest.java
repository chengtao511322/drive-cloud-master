package com.drive;

import com.drive.basics.DriveCloudBasicsApplication;
import com.drive.basics.pojo.dto.ChannelEditParam;
import com.drive.basics.pojo.entity.ChannelEntity;
import com.drive.basics.pojo.vo.ChannelVo;
import com.drive.basics.repository.ChannelRepository;
import com.drive.basics.service.ChannelService;
import com.drive.common.core.utils.BeanConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = DriveCloudBasicsApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class ChannelTest {

    @Autowired
    private ChannelService channelService;

    @Autowired
    private ChannelRepository channelRepository;

    @Test
    public void test(){
        List<ChannelEntity> channelEntities = channelService.list();
        List<ChannelVo> list= BeanConvertUtils.copyList(channelEntities,ChannelVo.class);
        quickSortByList(list,0,10);
    }

    @Test
    public void move(){
        ChannelEditParam channelEditParam = new ChannelEditParam();
        channelEditParam.setId("1210487311284834300");
        channelEditParam.setSortType(0);
        channelRepository.move(channelEditParam);
    }


    /**快速排序方法（列表）*/
    public static void quickSortByList(List<ChannelVo> list, int lo0, int hi0) {
        int lo = lo0;
        int hi = hi0;
        if (lo >= hi)
            return;

        //确定指针方向的逻辑变量
        boolean transfer=true;

        while (lo != hi) {
            if (list.get(lo).getSort() > list.get(hi).getSort()) {
                //交换
                ChannelVo temp = list.get(lo);
                list.set(lo, list.get(hi));
                list.set(hi, temp);
                //决定下标移动，还是上标移动
                transfer = (transfer == true) ? false : true;
            }

            //将指针向前或者向后移动
            if(transfer)
                hi--;
            else
                lo++;
        }

        //将数组分开两半，确定每个数字的正确位置
        lo--;
        hi++;
        quickSortByList(list, lo0, lo);
        quickSortByList(list, hi, hi0);
    }
}
