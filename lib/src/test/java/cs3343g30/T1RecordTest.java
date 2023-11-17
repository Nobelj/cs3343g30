package cs3343g30;

import org.junit.Test;
import static org.junit.Assert.*;


public class T1RecordTest {
	
	@Test
	public void getNameTest() {
		T1Record test = new T1Record("Joshua", "10", "3.24%");
		String name = test.getName();
		assertEquals(name, "Joshua");
	}
	
	@Test
	public void getOccurrTest() {
		T1Record test = new T1Record("Joshua", "10", "3.24%");
		String occurr = test.getOccurr();
		assertEquals(occurr, "10");
	}

	@Test
	public void getPercentTest() {
		T1Record test = new T1Record("Joshua", "10", "3.24%");
		String percent = test.getPercent();
		assertEquals(percent, "3.24%");
	}
	
	@Test
	public void equalsFalseTest() {
		T1Record test1 = new T1Record("Joshua", "10", "3.24%");
		T1Record test2 = new T1Record("Ken", "10", "3.24%");
		
		assertFalse(test1.equals(test2));
	}


}
