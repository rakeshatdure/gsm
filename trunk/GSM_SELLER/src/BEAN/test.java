package BEAN;

import java.util.Calendar;

public class test {
	public String getCalendarByID() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE,1);
		return calendar.get(Calendar.YEAR) + "-"
		+ (calendar.get(Calendar.MONTH) + 1) + "-"
		+ calendar.get(Calendar.DATE);
		
	}
	
	public static void main(String[] args) {
		test  m = new test();
		System.out.println("ngay hien tai: " + m.getCalendarByID());
	}
}
