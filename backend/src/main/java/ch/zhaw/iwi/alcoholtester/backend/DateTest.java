package ch.zhaw.iwi.alcoholtester.backend;

import java.util.GregorianCalendar;

public class DateTest {

	public static void main(String[] args) {
		//                                                   YY   MM  DD  hh  mm  ss
		GregorianCalendar drinkTime = new GregorianCalendar(2014, 10, 05, 22, 59, 00);
		
		double diffMill = System.currentTimeMillis()-drinkTime.getTimeInMillis();
		double diffHour = diffMill/(3600000);
		
		System.out.println("Differenz: "+diffHour);		
	}
}
