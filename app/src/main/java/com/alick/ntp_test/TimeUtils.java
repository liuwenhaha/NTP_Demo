package com.alick.ntp_test;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 */
@SuppressLint("SimpleDateFormat")
public class TimeUtils {
	public static final String format1="yyyy-MM-dd HH:mm:ss";
	public static final String format2="yyyy-MM-dd";
	public static final String format3="yy-MM-dd";
	public static final String format4="yyyy-MM-dd HH:mm";
	public static final String format5="yyyy-MM";
	public static final String format6="yyyy/MM/dd";
	public static final String format7="yyyy-MM-dd HH:mm:ss:ms";
	public static final String format8="hh:mm";
	public static final String format9="yyyy年MM月dd日 HH:mm:ss";
	public static final String format10="HH:mm:ss:ms";
	/**
	 * 根据时间戳转换字符串时间格式
	 * 
	 * @param time
	 * @return
	 */
	public static String parseLongToString(long time, String formater) {
		SimpleDateFormat format = new SimpleDateFormat(formater);
		return format.format(new Date(time));
	}
	/**
	 * 根据时间戳转换字符串时间格式
	 *
	 * @param time
	 * @return
	 */
	public static String parseLongToString(long time) {
		SimpleDateFormat format = new SimpleDateFormat(format1);
		return format.format(new Date(time));
	}

	
	
	/**
	 * 根据字符串时间格式转换系统时间戳
	 * 
	 * @param strTime
	 * @return
	 */
	public static long parseStringToMillis(String strTime) {
		SimpleDateFormat format = new SimpleDateFormat(format4);
		try {
			Date d = format.parse(strTime);
			return d.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 根据字符串时间格式转换系统时间戳
	 * 
	 * @param strTime
	 * @return
	 * modify by zhang 
	 */
	public static long parseStringToMillis(String strTime, String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		try {
			if (strTime!=null) {
				Date d = format.parse(strTime);
				return d.getTime();
			}else{
				return System.currentTimeMillis();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * String格式的时间转换成另一种String格式的时间
	 * @param strTime
	 * @param befoerFormat
	 * @param afterFormat
	 * @return
	 */
	public static String parseStringToString(String strTime, String befoerFormat, String afterFormat){
		SimpleDateFormat format = new SimpleDateFormat(befoerFormat);
		try {
			return parseLongToString(format.parse(strTime).getTime(),afterFormat);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}



	/**
	 * 获取聊天时间
	 * 
	 * @param timesamp
	 * @return
	 */
	public static String getChatTime(long timesamp) {
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		Date today = new Date(System.currentTimeMillis());
		Date otherDay = new Date(timesamp);
		int temp = Integer.parseInt(sdf.format(today))
				- Integer.parseInt(sdf.format(otherDay));

		switch (temp) {
		case 0:
			result = "今天 " + parseLongToString(timesamp, "HH:mm");
			break;
		case 1:
			result = "昨天 " + parseLongToString(timesamp, "HH:mm");
			break;
		case 2:
			result = "前天 " + parseLongToString(timesamp, "HH:mm");
			break;

		default:
			// result = temp + "天前 ";
			result = parseLongToString(timesamp, "yyyy-MM-dd HH:mm:dd");
			break;
		}

		return result;
	}
	
	
	public static DateModel parseLongToCalendar(long time){
		Calendar calendar= Calendar.getInstance();
		calendar.setTimeInMillis(time);
		DateModel dateModel=new DateModel(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,calendar.get(Calendar.DAY_OF_MONTH));
		return dateModel;
	}
	
	public static class DateModel{
		public int year;
		public int month;
		public int day;
		
		public DateModel() {
		}

		public DateModel(int year, int month, int day) {
			this.year = year;
			this.month = month;
			this.day = day;
		}
	}

	/**
	 * 获取当前时间
	 * @return
	 */
	public static String getCurrentTime(){
		return parseLongToString(System.currentTimeMillis(),format1);
	}

	/**
	 * 获取当前时间
	 * @param format	时间格式
	 * @return
	 */
	public static String getCurrentTime(String format){
		return parseLongToString(System.currentTimeMillis(),format);
	}


	public static boolean isOverRange(long second, String beginTime, String endTime){
		return isOverRange(second,parseStringToMillis(beginTime,format1),parseStringToMillis(endTime,format1));
	}

	public static boolean isOverRange(long second, String beginTime, String endTime, String format){
		return isOverRange(second,parseStringToMillis(beginTime,format),parseStringToMillis(endTime,format1));
	}

	public static boolean isOverRange(long second,long beginTime,long endTime){
		return endTime-beginTime>second;

	}


	public static String parseChatTime(String strTime) {
		return parseChatTime(parseStringToMillis(strTime,format1));
		//2016-08-19 23:03:10
	}

	/**
	 * 解析聊天时间<br/>
	 * 1.不同年,或同年但不同月,或同年同月但日相差大于1天的,就显示具体时间,例如:2015年5月10日 上午 8:7<br/>
	 * 2.同年同月作日,显示:昨天上午 8:7<br/>
	 * 3.同年同月同日,显示:今天下午 6:7
	 * @param time
	 * @return
     */
	public static String parseChatTime(long time) {
		Calendar otherCalendar = Calendar.getInstance();
		otherCalendar.setTimeInMillis(time);

		Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.setTimeInMillis(System.currentTimeMillis());

		if (otherCalendar.get(Calendar.YEAR) != currentCalendar.get(Calendar.YEAR)
				|| (otherCalendar.get(Calendar.MONTH) != currentCalendar.get(Calendar.MONTH))
				|| (currentCalendar.get(Calendar.DAY_OF_MONTH) - otherCalendar.get(Calendar.DAY_OF_MONTH) > 1)) {

			return otherCalendar.get(Calendar.YEAR) + "年" + (otherCalendar.get(Calendar.MONTH) + 1) + "月"
					+ otherCalendar.get(Calendar.DAY_OF_MONTH) + "日 "
					+ (otherCalendar.get(Calendar.AM_PM) == Calendar.AM ? "上午" : "下午") + " "
					+ parseLongToString(time,format8);
		}

		return (currentCalendar.get(Calendar.DAY_OF_MONTH) - otherCalendar.get(Calendar.DAY_OF_MONTH) == 1 ? "昨天": "")
				+ (otherCalendar.get(Calendar.AM_PM) == Calendar.AM ? "上午" : "下午") + " "
				+ parseLongToString(time,format8);
	}



	public static String parseLastMsgTime(long time) {
		Calendar otherCalendar = Calendar.getInstance();
		otherCalendar.setTimeInMillis(time);

		Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.setTimeInMillis(System.currentTimeMillis());

		if (otherCalendar.get(Calendar.YEAR) != currentCalendar.get(Calendar.YEAR)
				|| (otherCalendar.get(Calendar.MONTH) != currentCalendar.get(Calendar.MONTH))
				|| (currentCalendar.get(Calendar.DAY_OF_MONTH) - otherCalendar.get(Calendar.DAY_OF_MONTH) > 1)) {

			return String.valueOf(otherCalendar.get(Calendar.YEAR)).substring(2) + "/" + (otherCalendar.get(Calendar.MONTH) + 1) + "/"
					+ otherCalendar.get(Calendar.DAY_OF_MONTH);
		}

		if(currentCalendar.get(Calendar.DAY_OF_MONTH) - otherCalendar.get(Calendar.DAY_OF_MONTH) == 1){
			return "昨天";
		}

		return (otherCalendar.get(Calendar.AM_PM) == Calendar.AM ? "上午" : "下午") + " "
				+ parseLongToString(time,format8)/*otherCalendar.get(Calendar.HOUR) + ":" + otherCalendar.get(Calendar.MINUTE)*/;
	}
}
