package com.agilet.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
	public static Date String2Time(String datetimeString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginDateTime = new Date();
		try {
			beginDateTime = sdf.parse(datetimeString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return beginDateTime;
	}

	public static String timeFormat(Long l) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDateTime = new Date();
		Date date = new Date(l);
		return sdf.format(date);
	}
}
