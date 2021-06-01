package com.drive.common.core.utils;

import com.google.common.base.CaseFormat;

import java.text.*;
import java.util.Calendar;


/**
 * 字符串工具类
 *
 * @author xiaoguo
 */
public class StringUtils {

    /** This Format for format the data to special format. */
    private final static Format dateFormat = new SimpleDateFormat("YYYYMMddHHmmssS");

    /** This Format for format the number to special format. */
    private final static NumberFormat numberFormat = new DecimalFormat("0000");

    /** The FieldPosition. */
    private static final FieldPosition HELPER_POSITION = new FieldPosition(0);


    /** This int is the sequence number ,the default value is 0. */
    private static int seq = 0;

    private static final int MAX = 9999;

    public static boolean isNotBlank(final String str) {
        return !isBlank(str);
    }

    public static boolean isBlank(final String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /**
     * 删除前后空格
     */
    public static String trim(final String str) {
        return str == null ? null : str.trim();
    }

    public static String capitalize(final String str) {
        return org.apache.commons.lang3.StringUtils.capitalize(str);
    }

    public static String uncapitalize(final String str) {
        return org.apache.commons.lang3.StringUtils.uncapitalize(str);
    }

    /**
     * 下划线的字符串转换为驼峰式字符串
     */
    public static String lowerUnderscoreToLowerCamel(String str) {
        CaseFormat fromFormat = CaseFormat.LOWER_UNDERSCORE;//
        CaseFormat toFormat = CaseFormat.LOWER_CAMEL;
        return fromFormat.to(toFormat, str);
    }

    /**
     * 时间格式生成序列(时间序列)
     * @return String
     */
    public static synchronized String generateSequenceNo() {

        Calendar rightNow = Calendar.getInstance();

        StringBuffer sb = new StringBuffer();

        dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION);

        numberFormat.format(seq, sb, HELPER_POSITION);

        if (seq == MAX) {
            seq = 0;
        } else {
            seq++;
        }

        return sb.toString();
    }


    /**
     * 驼峰式的字符串转换为下划线
     */
    public static String lowerCamelToLowerUnderscore(String str) {
        CaseFormat fromFormat = CaseFormat.LOWER_CAMEL;
        CaseFormat toFormat = CaseFormat.LOWER_UNDERSCORE;
        return fromFormat.to(toFormat, str);
    }

    /**
     * 驼峰式的字符串转换为下划线并在指定字符后加下划线
     * 处理如subject1CostFreeNumber 字段驼峰转下划线时会转为 subject1_cost_free_number(实际table字段subject_1_cost_free_number)
     * 导致以该字段为查询条件时数据库报 找不到字段的错误
     * @author chentao
     * @param str 需要转下划线的驼峰字符串,
     * @param afterStrAddUnder 在该字符串后加下划线
     */
    public static String lowerCamelToLowerUnderscoreAdd(String str,String... afterStrAddUnder){
        //驼峰转下划线
        final String underString = lowerCamelToLowerUnderscore(str);
        if(afterStrAddUnder == null){
            return underString;
        }
        StringBuilder sb = new StringBuilder(underString);
        //在给定字符后添加下划线
        for (String s : afterStrAddUnder) {
            int macthIndex = sb.indexOf(s, 0);
            if(macthIndex != -1){
                int addCharIndex = macthIndex + s.length();
                sb.insert(addCharIndex,"_");
            }else {
                continue;
            }
        }
        return sb.toString();
    }

    /**
     * 字符串格式化
     * @param s
     * @param args
     * @return
     */
    public static String format(String s, Object... args) {
        if (isNotBlank(s)) {
            return String.format(s, args);
        }
        return "";
    }

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj)
    {
        return (T) obj;
    }

}



