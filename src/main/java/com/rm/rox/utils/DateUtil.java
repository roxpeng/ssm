package com.rm.rox.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil
{
	public static String getDate()
	{
		return getDate(Calendar.getInstance().getTime());
	}

	public static String getDate(Date now)
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(now);
	}

	public static String getSimpleDate()
	{
		return getSimpleDate(Calendar.getInstance().getTime());
	}

	public static String getSimpleDate(Date now)
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(now);
	}

	public static Date getSimpleDate(String date) throws ParseException
	{
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		return f.parse(date);
	}

	public static String getTimeByZdy(Date now, String fmt)
	{
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(fmt);
		return format.format(now);
	}

	public static String getCurrentMonthFirstDay()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return getSimpleDate(calendar.getTime());
	}

	public static String getCurrentMonthLastDay()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.getActualMaximum((Calendar.DAY_OF_MONTH)));
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return getSimpleDate(calendar.getTime());
	}

	public static String getLastDay()
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
		return df.format(calendar.getTime());
	}

	public static String getLastWeekFirstDay()
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance(Locale.FRANCE);
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 7);
		return df.format(calendar.getTime());
	}

	public static String getAroundDay(String date, int add)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		try
		{
			calendar.setTime(df.parse(date));
		}
		catch (ParseException ex)
		{
			throw new RuntimeException("DateUtil.getAroundDay throw a exception: date=" + date, ex);
		}

		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + add);
		return df.format(calendar.getTime());
	}
	
	
	public static String getPrevDay()
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
		return df.format(calendar.getTime());
	}
}
