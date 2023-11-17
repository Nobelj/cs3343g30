package cs3343g30;

import org.junit.Test;
import static org.junit.Assert.*;


public class T4RecordTest {
	@Test
	public void getYearTest() {
		T4Record test = new T4Record("1999", "4", "Joshua", "M", "10", "3.24%");
		String year = test.getYear();
		assertEquals(year, "1999");
	}
	
	@Test
	public void getRankTest() {
		T4Record test = new T4Record("1999", "4", "Joshua", "M", "10", "3.24%");
		String rank = test.getRank();
		assertEquals(rank, "4");
	}
	
	@Test
	public void getNameTest() {
		T4Record test = new T4Record("1999", "4", "Joshua", "M", "10", "3.24%");
		String name = test.getName();
		assertEquals(name, "Joshua");
	}
	
	@Test
	public void getGenderTest() {
		T4Record test = new T4Record("1999", "4", "Joshua", "M", "10", "3.24%");
		String gender = test.getGender();
		assertEquals(gender, "M");
	}
	
	@Test
	public void getOccurrTest() {
		T4Record test = new T4Record("1999", "4", "Joshua", "M", "10", "3.24%");
		String occurr = test.getOccurr();
		assertEquals(occurr, "10");
	}
	
	@Test
	public void getPercentTest() {
		T4Record test = new T4Record("1999", "4", "Joshua", "M", "10", "3.24%");
		String percent = test.getPercent();
		assertEquals(percent, "3.24%");
	}
}
