package com.wecodee.employee.application.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {

	public static Date getCurrentDateTime() {
		return new Date(new java.util.Date().getTime());
	}

	public static Integer getNextVersion(Integer currentVersion) {
		return currentVersion == null ? 1 : currentVersion + 1;
	}

	public static Date getCurrentDateAndTime() {
		return new Date();
	}

	public static Date getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(sdf.format(new Date()));
			return date;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date formattingDate(Date fromDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			fromDate = sdf.parse(sdf.format(fromDate));
			return fromDate;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String formatDateToString(Date date) {
		if (date == null) {
			return null;
		} else {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String strDate = formatter.format(date);
			return strDate;
		}
	}

}
