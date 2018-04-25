package com.empl.mgr.utils;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {

	/*
	 * 获取当前时间
	 */
	public static Date getCurrentTime() {
		return new Date();
	}

	/*
	 * 转换时间
	 */
	public static String conversionTime(Date date, String format) {
		if (CompareUtil.isEmpty(date) || StringUtils.isEmpty(format))
			return "";
		return new SimpleDateFormat(format).format(date);
	}

	public static int getCurrentMonthLastDay(){
		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);//把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

}
