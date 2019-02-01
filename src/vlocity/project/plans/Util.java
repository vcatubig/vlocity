package vlocity.project.plans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	public static boolean validDate(String strDate) {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		try {
			simple.parse(strDate);
			return true;
		} catch (ParseException e) {
			System.out.println("Invalid Date!");
			return false;
		}
	}

	public static Date getDate(String strDate) {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return simple.parse(strDate);
		} catch (ParseException e) {
			System.out.println("Invalid Date!" + e);
			return null;
		}
	}
	
	public static boolean validDuration(Date startDate, Date endDate) {
		return startDate.before(endDate);
	}
}
