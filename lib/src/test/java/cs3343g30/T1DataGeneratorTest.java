package cs3343g30;

import org.junit.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static org.junit.Assert.*;


public class T1DataGeneratorTest {
	@Test
	public void getSummaryTest() {
		String summary = T1DataGenerator.getSummary(1880);
		assertEquals(summary, "John is the most popular name with the number of occurrences of 9655, which "
				+ "represents 8.74% of total male births in 1880.\n"
				+ "Mary is the most popular name with the number of occurrences of 7065, which "
				+ "represents 7.76% of total female births in 1880.");
	}
	
	
	@Test
	public void getTableDataTest() {
		ObservableList<T1Record> resultList = T1DataGenerator.getTableData(1, 1880, "F");
		ObservableList<T1Record> realResult = FXCollections.observableArrayList();
		realResult.add(new T1Record("Mary", "7065", "7.76%"));
		for(int i=0;i<resultList.size();++i) {
			assertEquals(realResult.get(i).getName(), resultList.get(i).getName());
			assertEquals(realResult.get(i).getOccurr(), resultList.get(i).getOccurr());
			assertEquals(realResult.get(i).getPercent(), resultList.get(i).getPercent());
		}
		
	}
}
