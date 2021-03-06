package com.drive.basics.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.basics.pojo.dto.ChannelEditParam;
import com.drive.basics.pojo.dto.ChannelPageQueryParam;
import com.drive.basics.pojo.entity.ChannelEntity;
import com.drive.basics.pojo.vo.ChannelVo;
import com.drive.basics.repository.ChannelRepository;
import com.drive.basics.service.ChannelService;
import com.drive.basics.service.mapstruct.ChannelMapStruct;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.core.utils.BeanConvertUtils;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

                                                                                
/**
 *
 * 栏目 服务类
 *
 * @author xiaoguo
 */
@Slf4j
@Service
public class  ChannelRepositoryImpl extends BaseController<ChannelPageQueryParam, ChannelEntity>  implements ChannelRepository{

     // 1210513762407149568
    //1210514029525594112
    //1273152505248866306
    private final String GENERALID = "1210513762407149568";
    private final String CELERITYID = "1210514029525594112";
    private final String MAKEID = "1273152505248866306";
    private final String[] ARRID = new String[]{"1210513762407149568","1210514029525594112","1273152505248866306"};


    @Autowired
    private ChannelService channelService;
    @Autowired
    private ChannelMapStruct channelMapStruct;

    /**
    * *栏目 分页列表
    **/
    @Override
    public ResObject pageList(ChannelPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<ChannelEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        QueryWrapper queryWrapper = this.getQueryWrapper(channelMapStruct, param);
        //queryWrapper.in("parent_id",ARRID);
        // 报名单号 模糊查询
        queryWrapper.like(StrUtil.isNotEmpty(param.getVagueNameSearch()),"name",param.getVagueNameSearch());
        IPage<ChannelEntity> pageList = channelService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),pageList);
        }
        Page<ChannelVo> channelVoPage = channelMapStruct.toVoList(pageList);
        channelVoPage.getRecords().stream().forEach((item) ->{
           /* QueryWrapper queryChannelWrapper = new QueryWrapper();
            queryChannelWrapper.eq("parent_id",item.getId());
            int count = channelService.count(queryChannelWrapper);
            if (count >0 )item.setHasChildren(true);*/
           /* ChannelVo vo = BeanConvertUtils.copy(channelService.getById(item.getParentId()),ChannelVo.class);
            log.info("vo{}",vo);
            if (vo != null)channelVoPage.getRecords().add(vo);*/
        });
        log.info(this.getClass() + "pageList-方法请求结果{}",channelVoPage);
        return R.success(channelVoPage);
    }

    @Override
    public ResObject findList(ChannelPageQueryParam param) {
        log.info(this.getClass() + "findList-方法请求参数{}",param);
        // 这里判断条件进行查询
        QueryWrapper queryWrapper= this.getQueryWrapper(channelMapStruct, param);
        // 如 queryWrapper.eq(StrUtil.isNotEmpty(param.getPhone()),"phone",param.getPhone());
        List<ChannelEntity> pageList = channelService.list(queryWrapper);
        List<ChannelVo> channelVoList = channelMapStruct.toVoList(pageList);
        log.info(this.getClass() + "findList-方法请求结果{}",channelVoList);
        if (channelVoList == null){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(channelVoList);
    }



    @Override
    public ResObject getInfo(ChannelPageQueryParam param) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",param);
        if (param == null){
            return R.failure("数据空");
        }
        QueryWrapper queryWrapper= this.getQueryWrapper(channelMapStruct, param);
        ChannelEntity channel = channelService.getOne(queryWrapper);
        ChannelVo channelVo = BeanConvertUtils.copy(channel, ChannelVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",channelVo);
        if (channelVo ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(channelVo);
    }

    /**
     * *通过ID获取栏目 列表
     **/
    @Override
    public ResObject getById(String id) {
        log.info(this.getClass() + "getInfo-方法请求参数{}",id);
        if (StrUtil.isEmpty(id)){
            return R.failure("数据空");
        }
        ChannelEntity channel = channelService.getById(id);
        // do  转 DTO
        ChannelVo channelVo = BeanConvertUtils.copy(channel, ChannelVo.class);
        log.info(this.getClass() + "getInfo-方法请求结果{}",channelVo);
        if (channelVo ==null){
            log.error("活动数据对象空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg());
        }
        return R.success(channelVo);
    }

    /**
     * *保存栏目 信息
     **/
    @Override
    public ResObject save(ChannelEditParam installParam) {
        log.info(this.getClass() + "save方法请求参数{}",installParam);
        if (installParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        ChannelEntity channel = BeanConvertUtils.copy(installParam, ChannelEntity.class);
        Boolean result = channelService.save(channel);
        log.info(this.getClass() + "save-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *修改栏目 信息
     **/
    @Override
    public ResObject update(ChannelEditParam updateParam) {
        log.info(this.getClass() + "update方法请求参数{}",updateParam);
        if (updateParam == null){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        ChannelEntity channel = BeanConvertUtils.copy(updateParam, ChannelEntity.class);
        Boolean result = channelService.updateById(channel);
        log.info(this.getClass() + "update-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *删除栏目 信息
     **/
    @Override
    public ResObject deleteByIds(String[] ids) {
        return R.toRes(channelService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * *通过id删除栏目 信息
     **/
    @Override
    public ResObject deleteById(String id) {
        log.info(this.getClass() + "deleteById-方法请求参数{}",id);
        if(StrUtil.isEmpty(id)){
            log.error("ID数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        //QueryWrapper<String> queryWrapper = new QueryWrapper<String>();
        //queryWrapper.eq(StrUtil.isNotEmpty(id),"id",id);
        Boolean result = channelService.removeById(id);
        log.info(this.getClass() + "deleteById-方法请求结果{}",result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    /**
     * *导出栏目 信息
     **/
    @Override
    public ResObject exportXls(ChannelPageQueryParam param, HttpServletResponse response) {
        return null;
    }

    /**
     * *修改状态
     **/
    @Override
    public ResObject changeStatus(ChannelEditParam param) {

        ChannelEntity ChannelEntity = new ChannelEntity();
        ChannelEntity.setId(param.getId());
        ChannelEntity.setStatus(param.getStatus());
        ChannelEntity.setParentId(param.getParentId());
        ChannelEntity.setTenantId(param.getTenantId());
        Boolean result = channelService.updateById(ChannelEntity);
        log.info(this.getClass() +"changeStatus方法请求对象参数{}，请求结果{}",ChannelEntity,result);
        // 判断结果
        return result ?R.success(result):R.failure(result);
    }

    @Override
    public ResObject allList(ChannelEditParam channelEditParam) {
        QueryWrapper<ChannelEntity> queryWrapper = new QueryWrapper<ChannelEntity>();
        QueryWrapper<ChannelEntity> parantWrapper = new QueryWrapper<ChannelEntity>();
        log.info(this.getClass() + "allList");
        queryWrapper.eq(StrUtil.isNotEmpty(channelEditParam.getTenantId()),"tenant_id",channelEditParam.getTenantId());
        List<ChannelEntity> allList = channelService.list(queryWrapper);
        parantWrapper.eq("parent_id","0");
        List<ChannelEntity> parantList = channelService.list(parantWrapper);
        List<ChannelVo> aparantVoList = channelMapStruct.toVoList(parantList);
        List<ChannelVo> allVoList = channelMapStruct.toVoList(allList);
        aparantVoList.forEach((item) ->{
            allVoList.add(item);
        });
        log.info(this.getClass() + "allList-请求结果{}",allVoList);
        return R.success(allVoList);
    }

    @Override
    public ResObject updateChannel(ChannelEditParam channelEditParam) {
        log.info(this.getClass() + "updateChannel-方法请求参数{}",channelEditParam);
        if (StrUtil.isEmpty(channelEditParam.getId())){
            return R.failure();
        }
       synchronized (channelEditParam.getId()){
           ChannelEntity channelEntity = channelService.getById(channelEditParam.getId());
           if (channelEntity == null){
               log.error("数据空----");
               return R.failure();
           }
           String[] arrChannel = channelEntity.getAuth().split(",");
           List<String> arr = new ArrayList<String>();
           Arrays.stream(arrChannel).forEach((item)->{
               arr.add(item);
           });
           // 添加新的权限
           arr.add(channelEditParam.getAuth());
           String auth = Joiner.on(",").join(arr);
           channelEntity.setAuth(auth);
           channelEntity.setUpdateTime(LocalDateTime.now());
           Boolean result = channelService.updateById(channelEntity);
           if (!result){
               return R.failure(SubResultCode.DATA_INSTALL_FAILL.subCode(),SubResultCode.DATA_INSTALL_FAILL.subMsg());
           }
       }
        return R.success();
    }

    @Override
    public ResObject getChannelByParentId(String parentId) {
        log.info(this.getClass() + "getChannelByParentId方法请求参数{}",parentId);
        if (StrUtil.isEmpty(parentId)){
            log.error("数据空");
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id",parentId);
        List<ChannelEntity> channelEntitieList = channelService.list(queryWrapper);
        if (channelEntitieList.size() <=0){
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),channelEntitieList);
        }
        List<ChannelVo> channelVoList = channelMapStruct.toVoList(channelEntitieList);
        log.info(this.getClass() + "getChannelByParentId-方法请求结果{}",channelVoList);
        return R.success(channelVoList);
    }

    @Override
    public ResObject getParentList() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id",0);
        List<ChannelEntity> channelEntityList = channelService.list(queryWrapper);
        if (channelEntityList.size() <= 0){
            log.error("数据空");
            return R.success(SubResultCode.DATA_NULL.subCode(),SubResultCode.DATA_NULL.subMsg(),channelEntityList);
        }
        List<ChannelVo> channelVoList = channelMapStruct.toVoList(channelEntityList);
        log.info(this.getClass() + "findList-方法请求结果{}",channelVoList);
        return R.success(channelVoList);
    }

    @Override
    public ResObject move(ChannelEditParam channelEditParam) {
        log.info(this.getClass() + "move-方法请求参数{}",channelEditParam);
        if (StrUtil.isEmpty(channelEditParam.getId())){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }
        if (channelEditParam.getSortType() == null){
            return R.failure(SubResultCode.PARAMISBLANK.subCode(),SubResultCode.PARAMISBLANK.subMsg());
        }

        if (channelEditParam.getSortType() == 0) {
            this.moveDown(channelEditParam.getId());
        } else if (channelEditParam.getSortType() == 1) {
            this.moveUp(channelEditParam.getId());
        }
        return R.success("执行成功");
    }

    /**
     * 下移
     * @param groupId
     */
    public void moveDown(String groupId) {
        //获取要下移的数据信息
        ChannelEntity groupSortDTO = channelService.getById(groupId);
        //查询下一条记录
        ChannelEntity groupSortDTONext = channelService.moveDown(groupSortDTO.getSort());
        //最下边的记录不能下移
        if (null == groupSortDTONext) {
            return;
        }

        //交换两条记录的sortNum值
        Integer temp = groupSortDTO.getSort();
        groupSortDTO.setSort(groupSortDTONext.getSort());
        groupSortDTONext.setSort(temp);

        //更新到数据库
        channelService.updateById(groupSortDTO);
        channelService.updateById(groupSortDTONext);
    }


    /**
     * 上移
     * @param groupId
     */
    public void moveUp(String groupId) {
        //获取要上移的那条数据的信息
        ChannelEntity groupSortDTO = channelService.getById(groupId);
        //查询上一条记录
        ChannelEntity groupSortDTOPro = channelService.moveUp(groupSortDTO.getSort());

        //最上面的记录不能上移
        if (null == groupSortDTOPro) {
            return;
        }

        //交换两条记录的sort值
        Integer temp = groupSortDTO.getSort();
        groupSortDTO.setSort(groupSortDTOPro.getSort());
        groupSortDTOPro.setSort(temp);

        //更新到数据库
        channelService.updateById(groupSortDTO);
        channelService.updateById(groupSortDTOPro);
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

