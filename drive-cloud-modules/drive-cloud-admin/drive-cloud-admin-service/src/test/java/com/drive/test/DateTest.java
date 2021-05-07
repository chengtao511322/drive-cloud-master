package com.drive.test;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.util.Calendar;
import java.util.Date;

public class DateTest {

    public static void main(String[] args) {
        Date in = cn.hutool.core.date.DateUtil.parse("2021-04-26 16:18:16");
        Date out = cn.hutool.core.date.DateUtil.parse("2020-12-14 15:20:17");
        DateTime dateTime = DateUtil.offset(new Date(), DateField.DAY_OF_MONTH, 5);
        System.out.println(dateTime);

        String ids = "01b13fad909245abb042d5efc37b82ee,b00a7c7b60be4c42ba37f2f66c10806a,bbdc1bd499b241daa6fe99063e63a193";
        //System.out.println(DateUtil.between(new Date(),in, DateUnit.DAY, false));
        //新建FIFOCache
        /*Cache<String,String> fifoCache = CacheUtil.newFIFOCache(3);
        fifoCache.put("name","xiaoguo");
        fifoCache.get("name");
        Jedis jedis = RedisDS.create().getJedis();
        //jedis.set("name","xiaoguo");
        String result =  jedis.get("operator_dept:01b13fad909245abb042d5efc37b82ee");
        JSON json = JSONUtil.parse(result);
        System.out.println(json.getByPath("name"));
        System.out.println(DateUtil.now());
        List<String> arr = new ArrayList();
        List<String> arrJson = new ArrayList();
        arr.add("1");
        arr.add("2");
        arr.add("3");
        arrJson.add("3");
        arrJson.add("4");
        arrJson.add("5");
    *//*    arr.stream().forEach((item) ->{
            arr.add("4");
        });*//*
       ;
        System.out.println(Stream.concat(arr.stream(), arrJson.stream())
                .distinct()
                .collect(Collectors.toList()));*/
        /*System.out.println( arr.stream().filter((String student)->student.equals("1")) //筛选出大于150的
                .collect(Collectors.toList()));*/
    }

    /**
     * 日期差
     * @param sDate 计划日期
     * @param nDate 实际日期
     */
    public static String getDateDif(Date sDate,Date nDate) {

        long dif = DateUtil.between(sDate, nDate, DateUnit.DAY, false);
        System.out.println();

        return dif > 0 ? String.format("滞后%d天",dif) : dif < 0 ? String.format("提前%d天", -dif) : "当天完成";
    }

    /**
     * date2比date1多的天数
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2-day1) ;
        }
        else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1,Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }
}
