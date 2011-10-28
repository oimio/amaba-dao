package ch.amaba.dao.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	private static Date INFINITE_DATE;

	public static Date getInfiniteDate() {
		if (DateUtils.INFINITE_DATE == null) {
			final Calendar cal = Calendar.getInstance();
			cal.set(9999, Calendar.DECEMBER, 1);
			DateUtils.INFINITE_DATE = cal.getTime();
		}
		return DateUtils.INFINITE_DATE;
	}

}
