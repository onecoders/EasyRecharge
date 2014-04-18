package my.project.easyrecharge.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dateformat {

	private static final String DATE_FORMAT = "yyyy-MM-dd";

	public static int getWeek(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getDay();
	}

	static String[] dates = new String[] { "00", "01", "02", "03", "04", "05",
			"06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16",
			"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
			"28", "29", "30", "31" };

	public static String getDate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dates[date.getDate()];
	}

	public static int getMonth(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getMonth();
	}

	public static int getYear(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getYear() + 1900;
	}

	public static int DateToInt(String dateStr) {
		String day = getYear(dateStr) + "" + getMonth(dateStr) + ""
				+ getDate(dateStr);
		return Integer.parseInt(day);
	}

	public static String timestamp2DateStr(String timestamp) {
		long stamp = Long.parseLong(timestamp);
		DateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.format(new Date(stamp));
	}
}
