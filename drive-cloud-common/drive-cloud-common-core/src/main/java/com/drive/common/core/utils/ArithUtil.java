package com.drive.common.core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @ClassName ArithUtil
 * @Description TODO：BigDecimal  高精度运算  由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精
 *  * 确的浮点数运算，包括加减乘除和四舍五入。
 * @Author @{用户} 小郭
 * @Date @{时间} 2020/3/1 13:58
 * @Version 1.0
 **/
public class ArithUtil {

    //默认除法运算精度
    private static final int DEF_DIV_SCALE = 10;
    /**
     * 提供精确的加法运算。
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static BigDecimal add(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2);
    }
    /**
     * 提供精确的减法运算。
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static BigDecimal sub(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2);
    }

    public static BigDecimal sub(BigDecimal v1,BigDecimal v2){
        return v1.subtract(v2);
    }

    /**
     * 提供精确的乘法运算。
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static BigDecimal mul(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2);
    }

/*    public static BigDecimal mul(double v1,double v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2,scale,BigDecimal.ROUND_HALF_UP);
    }*/

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static BigDecimal div(double v1,double v2){
        return div(v1,v2,DEF_DIV_SCALE);
    }
    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static BigDecimal div(double v1,double v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP);
    }
    /**
     * 提供精确的小数位四舍五入处理。
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static BigDecimal round(double v,int scale){
        if(scale<0){
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one,scale, BigDecimal.ROUND_HALF_UP);
    }

    /** 金额为分的格式 */
    public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";

    /**
     * 将分为单位的转换为元 （除100）
     *
     * @param amount
     * @return
     * @throws Exception
     */
    public static String changeF2Y(String amount) {
     /*   if (!amount.matches(CURRENCY_FEN_REGEX)) {
            return null;
        }*/
        return BigDecimal.valueOf(Long.valueOf(amount)).multiply(new BigDecimal(100)).toString();
    }

    public static BigDecimal divDown(BigDecimal v1,BigDecimal v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        // 被除数.divide(除数,保留小数位, RoundingMode.结果处理);
        return v1.divide(v2,scale, RoundingMode.DOWN);
    }
    /**
     * 提供精确的乘法运算
     * 由scale参数指定精度，以后的小数直接省略
     * @param v1 乘数
     * @param v2 被乘数
     * @return 两个参数的积
     *
     *  BigDecimal 处理浮点数计算时, 需要将浮点数对象以字符串的形式转为BigDecimal 对象；
     */
    public static BigDecimal mulDown(BigDecimal v1,BigDecimal v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        return v1.multiply(v2).setScale(scale,RoundingMode.DOWN);
    }

    public static void main(String[] args) {
        BigDecimal b1 = mul(Double.parseDouble("1"),100);
        BigDecimal b2 = new BigDecimal("5.67");
        System.out.println(round(b2.doubleValue(),2));
        System.out.println(b1.compareTo(b2) == 0);
        System.out.println(b2.setScale( 0, BigDecimal.ROUND_UP ));
        //System.out.println(changeF2Y("0.01"));
    }
}
