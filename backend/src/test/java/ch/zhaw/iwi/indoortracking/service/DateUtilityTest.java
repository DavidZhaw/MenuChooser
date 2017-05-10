package ch.zhaw.iwi.indoortracking.service;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import ch.zhaw.iwi.alcoholtester.service.DateUtility;


public class DateUtilityTest {

	@Test
	public void testCheckDateOrder1() {
		Date date = new Date();
		assertTrue(DateUtility.checkDateOrder(date, date));
	}
	
	@Test
	public void testCheckDateOrder2() {
		Date date = new Date();
		assertTrue(DateUtility.checkDateOrder(date, null));
	}
	
	@Test
	public void testCheckDateOrder3() {
		Date date = new Date();
		assertTrue(DateUtility.checkDateOrder(null, date));
	}
	
	@Test
	public void testCheckDateOrder4() {
		assertTrue(DateUtility.checkDateOrder(null, null));
	}

}
