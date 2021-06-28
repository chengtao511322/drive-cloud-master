package com.drive.common.core.utils;

import cn.hutool.core.util.StrUtil;
import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;



/** 时间日期工具类 **/
public class TmDateUtil {
	public static final String Format_Date = "yyyy-MM-dd";
	public static final String Format_Time = "HH:mm:ss";
	public static final String Format_DateTime = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_DATE_TIME = "yyyy-MM-dd-HH-mm-ss"; 

	public static String getCurrentDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	public static String getCurrentDate(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	public static String getCurrentDatetime(Date date) {
		return new SimpleDateFormat(Format_DateTime).format(date);
	}
	
	public static String getCurrentDate(Date date,String format) {
		return new SimpleDateFormat(format).format(date);
	}
	
	
	
	public static String getCurrentDate(String format) {
		SimpleDateFormat t = new SimpleDateFormat(format);
		return t.format(new Date());
	}

	public static String getCurrentTime() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}

	public static String getCurrentTime(String format) {
		SimpleDateFormat t = new SimpleDateFormat(format);
		return t.format(new Date());
	}

	public static String getCurrentDateTime() {
		String format = "yyyy-MM-dd HH:mm:ss";
		return getCurrentDateTime(format);
	}

	public static int getDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		int week = cal.get(7)-1;
		if(week<=0)week=7;
		return week;
	}

	public static int getDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(7);
	}

	public static int getDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(5);
	}

	public static int getDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(5);
	}

	public static int getMaxDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getActualMaximum(5);
	}

	public static String getFirstDayOfMonth(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parse(date));
		cal.set(5, 1);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	public static int getDayOfYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(6);
	}

	public static int getDayOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(6);
	}

	public static int getDayOfWeek(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parse(date));
		return cal.get(7);
	}

	public static int getDayOfMonth(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parse(date));
		return cal.get(5);
	}

	public static int getDayOfYear(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parse(date));
		return cal.get(6);
	}

	public static String getCurrentDateTime(String format) {
		SimpleDateFormat t = new SimpleDateFormat(format);
		//设置为东八区
		t.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		return t.format(new Date());
	}

	public static String toString(Date date) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public static String toDateTimeString(Date date) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	public static String toString(Date date, String format) {
		SimpleDateFormat t = new SimpleDateFormat(format);
		return t.format(date);
	}

	public static String toTimeString(Date date) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat("HH:mm:ss").format(date);
	}

	public static int compare(String date1, String date2) {
		return compare(date1, date2, "yyyy-MM-dd");
	}

	public static int compareTime(String time1, String time2) {
		return compareTime(time1, time2, "HH:mm:ss");
	}

	public static int compare(String date1, String date2, String format) {
		Date d1 = parse(date1, format);
		Date d2 = parse(date2, format);
		return d1.compareTo(d2);
	}

	public static int compareTime(String time1, String time2, String format) {
		String[] arr1 = time1.split(":");
		String[] arr2 = time2.split(":");
		if (arr1.length < 2) {
			throw new RuntimeException("错误的时间值:" + time1);
		}
		if (arr2.length < 2) {
			throw new RuntimeException("错误的时间值:" + time2);
		}
		int h1 = Integer.parseInt(arr1[0]);
		int m1 = Integer.parseInt(arr1[1]);
		int h2 = Integer.parseInt(arr2[0]);
		int m2 = Integer.parseInt(arr2[1]);
		int s1 = 0;
		int s2 = 0;
		if (arr1.length == 3) {
			s1 = Integer.parseInt(arr1[2]);
		}
		if (arr2.length == 3) {
			s2 = Integer.parseInt(arr2[2]);
		}
		if ((h1 < 0) || (h1 > 23) || (m1 < 0) || (m1 > 59) || (s1 < 0)
				|| (s1 > 59)) {
			throw new RuntimeException("错误的时间值:" + time1);
		}
		if ((h2 < 0) || (h2 > 23) || (m2 < 0) || (m2 > 59) || (s2 < 0)
				|| (s2 > 59)) {
			throw new RuntimeException("错误的时间值:" + time2);
		}
		if (h1 != h2) {
			return ((h1 > h2) ? 1 : -1);
		}
		if (m1 == m2) {
			if (s1 == s2) {
				return 0;
			}
			return ((s1 > s2) ? 1 : -1);
		}

		return ((m1 > m2) ? 1 : -1);
	}

	public static boolean isTime(String time) {
		String[] arr = time.split(":");
		if (arr.length < 2) {
			return false;
		}
		try {
			int h = Integer.parseInt(arr[0]);
			int m = Integer.parseInt(arr[1]);
			int s = 0;
			if (arr.length == 3) {
				s = Integer.parseInt(arr[2]);
			}
			if ((h < 0) || (h > 23) || (m < 0) || (m > 59) || (s < 0)
					|| (s > 59)) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean isDate(String date) {
		String[] arr = date.split("-");
		if (arr.length < 3) {
			return false;
		}
		try {
			int y = Integer.parseInt(arr[0]);
			int m = Integer.parseInt(arr[1]);
			int d = Integer.parseInt(arr[2]);
			if ((y < 0) || (m > 12) || (m < 0) || (d < 0) || (d > 31)) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean isWeekend(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int t = cal.get(7);

		return ((t == 7) || (t == 1));
	}

	public static boolean isWeekend(String str) {
		return isWeekend(parse(str));
	}

	public static Date parse(String str) {
		if (StrUtil.isEmpty(str)) {
			return null;
		}
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date parse(String str, String format) {
		if (StrUtil.isEmpty(str))
			return null;
		try {
			SimpleDateFormat t = new SimpleDateFormat(format);
			return t.parse(str);
		} catch (ParseException e) {
		}
		return null;
	}

	public static Date parseDateTime(String str) {
		if (StrUtil.isEmpty(str)) {
			return null;
		}
		if (str.length() <= 10) {
			return parse(str);
		}
		try {
			SimpleDateFormat t = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//设置为东八区
			t.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
			return t.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date parseDateTime(String str, String format) {
		if (StrUtil.isEmpty(str)) {
			return null;
		}
		try {
			SimpleDateFormat t = new SimpleDateFormat(format);
			return t.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date addMinute(Date date, int count) {
		return new Date(date.getTime() + 60000L * count);
	}

	public static Date addHour(Date date, int count) {
		return new Date(date.getTime() + 3600000L * count);
	}

	public static Date addDay(Date date, int count) {
		return new Date(date.getTime() + 86400000L * count);
	}

	public static Date addWeek(Date date, int count) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(3, count);
		return c.getTime();
	}

	public static Date addMonth(Date date, int count) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(2, count);
		return c.getTime();
	}

	public static Date addYear(Date date, int count) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(1, count);
		return c.getTime();
	}

	public static String toDisplayDateTime(String date) {
		if (StrUtil.isEmpty(date)) {
			return null;
		}
		try {
			if (isDate(date)) {
				return toDisplayDateTime(parse(date));
			}
            SimpleDateFormat t = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //设置为东八区
            t.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
			Date d = t.parse(date);
			return toDisplayDateTime(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "不是标准格式时间!";
	}

	public static String toDisplayDateTime(Date date) {
		String interval = "";
		//获得系统的时间，单位为毫秒,转换为妙
		long totalMilliSeconds = System.currentTimeMillis();
		/*System.out.println(System.currentTimeMillis());
		System.out.println(date.getTime());
		System.out.println(minite);*/

		DateFormat dateFormatterChina = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);//格式化输出
		TimeZone timeZoneChina = TimeZone.getTimeZone("Asia/Shanghai");//获取时区 这句加上，很关键。
		dateFormatterChina.setTimeZone(timeZoneChina);//设置系统时区
		long minite = (totalMilliSeconds - date.getTime()) / 1000L;
		/*if (minite == 0) {
			return minite + "刚刚";
		}
		if (minite < 60L) {
			return minite + "分钟前";
		}
		if (minite < 1440L) {
			return (minite / 60L) + "小时前";
		}
		return  (minite / 1440L) + "天前";*/
		if (minite == 0) {
			interval = "刚刚";
		} else if (minite < 30) {
			interval = minite + "秒以前";
		} else if (minite >= 30 && minite < 60) {
			interval = "半分钟前";
		} else if (minite >= 60 && minite < 60 * 60) {//大于1分钟 小于1小时
			long minute = minite / 60;
			interval = minute + "分钟前";
		} else if (minite >= 60 * 60 && minite < 60 * 60 * 24) {//大于1小时 小于24小时
			long hour = (minite / 60) / 60;
			if (hour <= 3) {
				interval = hour + "小时前";
			} else {
				interval = "今天" + getFormatTime(date, "HH:mm");
			}
		} else if (minite >= 60 * 60 * 24 && minite <= 60 * 60 * 24 * 2) {//大于1D 小于2D
			interval = "昨天" + getFormatTime(date, "HH:mm");
		} else if (minite >= 60 * 60 * 24 * 2 && minite <= 60 * 60 * 24 * 7) {//大于2D小时 小于 7天
			long day = ((minite / 60) / 60) / 24;
			interval = day + "天前";
		} else if ( minite <= 60 * 60 * 24 * 365 && minite >= 60 * 60 * 24 * 7) {//大于7天小于365天
			interval = getFormatTime(date, "MM-dd HH:mm");
		} else if (minite >= 60 * 60 * 24 * 365) {//大于365天
			interval = getFormatTime(date, "yyyy-MM-dd HH:mm");
		} else {
			interval = "0";
		}
		return interval;
		//return toString(date, "MM-dd") + " " + (minite / 1440L) + "天前";
	}
	
	public static String parseDateTime(String format, String str, int dateStyle) throws ParseException {
		return DateFormat.getDateTimeInstance(dateStyle,dateStyle).format(parseDateTime(str, format));
	}
	
	public static Date dateToString(String time){
		Date startTime = null;
		try {
			 startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return startTime;
	}
	
	public static Date dateToString(String time,String format){
		Date startTime = null;
		try {
			 startTime = new SimpleDateFormat(format).parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return startTime;
	}
	
	public static String getTimeFormat(String startTime){
		return getTimeFormat(dateToString(startTime));
	}
	

	public static String getTimeFormat(Date startTime){
		try{
			long startTimeMills = startTime.getTime();
			long endTimeMills = System.currentTimeMillis();
			long diff = (endTimeMills - startTimeMills)/1000;//秒
			long day_diff  = (long) Math.floor(diff/86400);//天
			StringBuffer buffer = new StringBuffer();
			if(day_diff<0){
				return "[error],时间越界...";
			}else{
				if(day_diff==0 && diff<60){
					if(diff==0)diff=1;
					buffer.append(diff+"秒前");
				}else if(day_diff==0 && diff<120){
					buffer.append("1 分钟前");
				}else if(day_diff==0 && diff<3600){
					buffer.append(Math.round(Math.floor(diff/60))+"分钟前");
				}else if(day_diff==0 && diff<7200){
					buffer.append("1小时前");
				}else if(day_diff==0 && diff<86400){
					buffer.append(Math.round(Math.floor(diff/3600))+"小时前");
				}else if(day_diff==1){
					buffer.append("1天前");
				}else if(day_diff<7){
					buffer.append(day_diff+"天前");
				}else if(day_diff <30){
					buffer.append(Math.round(Math.floor( day_diff / 7 )) + "星期前");
				}else if(day_diff >=30 && day_diff<=179 ){
					buffer.append(Math.round(Math.floor( day_diff / 30 )) + "月前");
				}else if(day_diff >=180 && day_diff<365){
					buffer.append("半年前");
				}else if(day_diff>=365){
					buffer.append(Math.round(Math.floor( day_diff /30/12))+"年前");
				}
			}
			return buffer.toString();
		}catch(Exception ex){
			return "";
		}
	}
	
	/**
	 * 
	 * @param time(时间和分钟字符串格式是:8:10)
	 * @param addminute(加和减多少时间) 
	 * @param mark(add加时间，minus减时间)
	 * @return
	 * @throws ParseException
	 */
	public static String getHourMinus(String time,int addminute,String mark) {
		Date date = null;
		try {
			date = new SimpleDateFormat("HH:mm").parse(time);
			DateTime dateTime = new DateTime(date.getTime());
			DateTime d  = null;
			if(StrUtil.isEmpty(mark))mark="add";
			if(mark.equals("add")){
				d = dateTime.plusMinutes(addminute);
			}else{
				d = dateTime.minusMinutes(addminute);	
			}
			int hour = d.getHourOfDay();
			int minute = d.getMinuteOfHour();
			StringBuffer buffer = new StringBuffer();
			if(hour<10){
				buffer.append("0"+hour);
			}else{
				buffer.append(hour);
			}
			buffer.append(":");
			if(minute<10){
				buffer.append("0"+minute);
			}else{
				buffer.append(minute);
			}
			return buffer.toString();
		} catch (ParseException e) {
			return time;
		}
	}

	public static String dealDateFormat(String oldDate) {
		Date date1 = null;
		DateFormat df2 = null;
		try {
			oldDate= oldDate.replace("Z", " UTC");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
			Date date = df.parse(oldDate);
			SimpleDateFormat df1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
			date1 = df1.parse(date.toString());
			df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return df2.format(date1);
	}

	/**
	 *
	 * 　　* 判断时间是否在时间段内 *
	 *
	 * 　　* @param date
	 *
	 * 　　* 当前时间 yyyy-MM-dd HH:mm:ss
	 *
	 * 　　* @param strDateBegin
	 *
	 * 　　* 开始时间 00:00:00
	 *
	 * 　　* @param strDateEnd
	 *
	 * 　　* 结束时间 00:05:00
	 *
	 * 　　* @return
	 *
	 * 　　*/

	/**

	 * 判断时间是否在时间段内 *

	 * @param date

	 * 当前时间 yyyy-MM-dd HH:mm:ss

	 * @param strDateBegin

	 * 开始时间 00:00:00

	 * @param strDateEnd

	 * 结束时间 00:05:00

	 * @return

	 */

	public static boolean isInDates(String strDate,String strDateBegin,String strDateEnd){
		// myDate = sd.parse(strDate);
			//设置日期格式
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date now = null;
			Date beginTime = null;
			Date endTime = null;
			try {
				now = df.parse(strDate);
				//now = df.parse(df.format(new Date()));
				beginTime = df.parse(strDateBegin);
				endTime = df.parse(strDateEnd);
			} catch (Exception e) {
				e.printStackTrace();
			}

			Calendar date = Calendar.getInstance();
			date.setTime(now);

			Calendar begin = Calendar.getInstance();
			begin.setTime(beginTime);

			Calendar end = Calendar.getInstance();
			end.setTime(endTime);

			return date.after(begin) && date.before(end);
	}

	/**
	 * 判断当前时间是否在14:57-15:00之间
	 *
	 * @return boolean
	 */
	public static boolean judgeIsSetBiddingTime() {
		//设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = null;
		Date beginTime = null;
		Date endTime = null;

		try {
			now = df.parse(df.format(new Date()));
			beginTime = df.parse("2020-01-08 20:04:26");
			endTime = df.parse("2020-02-28 20:04:26");
		} catch (Exception e) {
			e.printStackTrace();
		}

		Calendar date = Calendar.getInstance();
		date.setTime(now);

		Calendar begin = Calendar.getInstance();
		begin.setTime(beginTime);

		Calendar end = Calendar.getInstance();
		end.setTime(endTime);

		return date.after(begin) && date.before(end);
	}

	public static String getInterval(Date createAt) {
		// 定义最终返回的结果字符串。
		String interval = null;
		/*TimeZone timeZoneChina = TimeZone.getTimeZone("Asia/Shanghai");//获取时区 这句加上，很关键。
		dateFormatterChina.setTimeZone(timeZoneChina);//设置系统时区*/
		long millisecond = System.currentTimeMillis() - createAt.getTime();

		long second = millisecond / 1000;

		if (second <= 0) {
			second = 0;
		}
		//*--------------微博体（标准）
		if (second == 0) {
			interval = "刚刚";
		} else if (second < 30) {
			interval = second + "秒以前";
		} else if (second >= 30 && second < 60) {
			interval = "半分钟前";
		} else if (second >= 60 && second < 60 * 60) {//大于1分钟 小于1小时
			long minute = second / 60;
			interval = minute + "分钟前";
		} else if (second >= 60 * 60 && second < 60 * 60 * 24) {//大于1小时 小于24小时
			long hour = (second / 60) / 60;
			if (hour <= 3) {
				interval = hour + "小时前";
			} else {
				interval = "今天" + getFormatTime(createAt, "HH:mm");
			}
		} else if (second >= 60 * 60 * 24 && second <= 60 * 60 * 24 * 2) {//大于1D 小于2D
			interval = "昨天" + getFormatTime(createAt, "HH:mm");
		} else if (second >= 60 * 60 * 24 * 2 && second <= 60 * 60 * 24 * 7) {//大于2D小时 小于 7天
			long day = ((second / 60) / 60) / 24;
			interval = day + "天前";
		} else if ( second <= 60 * 60 * 24 * 365 && second >= 60 * 60 * 24 * 7) {//大于7天小于365天
			interval = getFormatTime(createAt, "MM-dd HH:mm");
		} else if (second >= 60 * 60 * 24 * 365) {//大于365天
			interval = getFormatTime(createAt, "yyyy-MM-dd HH:mm");
		} else {
			interval = "0";
		}
		return interval;
	}
	public static String getFormatTime(Date date, String Sdf) {
		return (new SimpleDateFormat(Sdf)).format(date);
	}

	public static void main(String[] args) throws ParseException {
		//System.out.println(getFirstDayOfMonth("2020-02-11"));
		//System.out.println("2020-01-08 20:04:26".substring(3,5));*/
		System.out.println(isInDates(TmDateUtil.getCurrentDatetime(new Date()),"2020-07-30 00:00:00","2023-07-30 00:00:00"));
		//System.out.println(toDisplayDateTime("2020-02-15 12:00:00"));
		//System.out.println(addDay(new Date(), 5));
		//System.out.println(getInterval(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-03-12 12:12:12")));
		//System.out.println(getCurrentDateTime());
		//System.out.println(new Date().getTime());
	}
	
}
