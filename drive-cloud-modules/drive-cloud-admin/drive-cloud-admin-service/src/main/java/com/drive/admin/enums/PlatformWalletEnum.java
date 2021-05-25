package com.drive.admin.enums;

import cn.hutool.core.util.StrUtil;
import com.alipay.api.domain.CustomerEntity;
import com.alipay.api.domain.StudentInfo;
import com.drive.admin.pojo.entity.*;
import com.drive.admin.service.*;
import com.drive.common.core.utils.StringUtils;

import java.lang.reflect.Field;

/**
 * 钱包类型枚举
 *
 * @Author chentao
 * @Date 2021/5/21
 */
public enum PlatformWalletEnum {

    //学员钱包
    STUDENT_WALLET("1",StudentInfoEntity.class, StudentInfoService.class,"real_name"),
    //教练钱包
    COACH_WALLET("2", CoachInfoEntity.class, CoachInfoService.class,"real_name"),
    //客服钱包
    CUSTOMERSERVICE_WALLET("3", ServiceInfoEntity.class,ServiceInfoService.class,"real_name"),
    //运营商钱包
    OPERATOR_WALLET("7", OperatorEntity.class, OperatorService.class,"name"),
    //驾校钱包
    DRIVESCHOLL_WALLET("6", DriveSchoolEntity.class, DriveSchoolService.class,"school_name");

    private Class entityClass;//实体对象class
    private String code;//钱包类型
    private Class serviceClass;//钱包类型的用户所对应的实体service
    private String nameField;//钱包用户所对应数据库表的name字段名称  如student对应的name为real_name

    PlatformWalletEnum( String code,Class entityClass,Class serviceClass,String nameField) {
        this.code = code;
        this.entityClass = entityClass;
        this.serviceClass = serviceClass;
        this.nameField = nameField;
    }

    /**
     * 获得实体对象名称,因为实体对象的名称不一样
     * @return
     */
    public static String getEntityName(Object entityObject,PlatformWalletEnum walletEnum){
        if(entityObject != null){
            try {
                //获取对象的name字段属性
                String s = StringUtils.lowerUnderscoreToLowerCamel(walletEnum.getNameField());
                System.out.println(s);
                Field field = entityObject.getClass().getDeclaredField(StringUtils.lowerUnderscoreToLowerCamel(walletEnum.getNameField()));
                field.setAccessible(true);
                String nameStr = field.get(entityObject).toString();
                return StrUtil.isNotEmpty(nameStr) ? nameStr : null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获得实体对象id
     * @return
     */
    public static String getEntityId(Object object){
        if(object != null){
            try {
                Field idField = object.getClass().getDeclaredField("id");
                idField.setAccessible(true);
                return idField.get(object).toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public Class getEntityClass() {
        return entityClass;
    }


    public String getCode() {
        return code;
    }


    public Class getServiceClass() {
        return serviceClass;
    }


    public String getNameField() {
        return nameField;
    }

}
