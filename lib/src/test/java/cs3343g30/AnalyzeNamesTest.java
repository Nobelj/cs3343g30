package cs3343g30;

import org.junit.Test;
import static org.junit.Assert.*;

public class AnalyzeNamesTest {
	
    @Test 
    public void testGetRankNotFound() {
    	AnalyzeNames a = new AnalyzeNames();
    	int i = a.getRank(2019, "XXX", "M");
		assertEquals(i, -1);
    }
    
    @Test 
    public void testGetRankMale() {
    	AnalyzeNames a = new AnalyzeNames();
    	int i = a.getRank(2019, "David", "M");
    	assertEquals(i,27);
    }
    
    @Test 
    public void testGetRankFemale() {
    	AnalyzeNames a = new AnalyzeNames();
    	int i = a.getRank(2019, "Desire", "F");
    	assertEquals(i,2192);
    }
    	
    @Test 
    public void testGetNameMale() {
    	AnalyzeNames a = new AnalyzeNames();
    	String name = a.getName(2019, 27, "M");
    	assertTrue(name.equals("David"));
    }
    
    @Test 
    public void testGetNameFemale() {
    	AnalyzeNames a = new AnalyzeNames();
    	String name = a.getName(2019, 2192, "F");
    	assertTrue(name.equals("Desire"));
    }
    
    @Test
    public void getNameNotFoundTest() {
    	String name = AnalyzeNames.getName(2019, 0, "F");
    	assertTrue(name.equals("information on the name at the specified rank is not available"));
    }

    @Test
    public void getOccurrence() {
        assertEquals(AnalyzeNames.getOccurrence(2000, "Tom", "M"), 99);
    }
    
    @Test
    public void getOccurrenceNotFoundTest() {
        assertEquals(AnalyzeNames.getOccurrence(1880, "Toi", "M"), -1);
    }
    
    @Test
    public void getOccurrenceInYearBefore1880Test() {
        assertEquals(AnalyzeNames.getOccurrence(1771, "Tom", "M"), 0);
    }
    
    @Test
    public void getOccurrenceInYearAfter2019Test() {
        assertEquals(AnalyzeNames.getOccurrence(2020, "Tom", "M"), 0);
    }


    @Test
    public void getNameYearRecord() {
        T3YearRecord generatedRecord = AnalyzeNames.getNameYearRecord(2000, "Tom", "M");
        T3YearRecord expectedRecord = new T3YearRecord(2000, 1311, 99, (float) 0.003);
        assertEquals(generatedRecord.getOccurrences(), expectedRecord.getOccurrences());
        assertEquals(generatedRecord.getPercentage(), expectedRecord.getPercentage());
    }
    

    @Test
    public void getTotalOccurrence() {
        int occurrence = AnalyzeNames.getTotalOccurrence(1990);
        assertEquals(occurrence, 3950252);
    }
    
    @Test
    public void getTotalOccurrenceWithGender() {
    	int occurr1880M = AnalyzeNames.getTotalOccurrence(1880,"M");
    	assertEquals(occurr1880M, 110491);
    }

    @Test
    public void getSummary() {
        String generatedSummary = AnalyzeNames.getSummary(2019);
        String expectedSummary = "Summary (Year of 2019):\n" +
                "Total Births = 3,445,321\n" +
                "***Baby Girls = 1,665,373\n" +
                "***Baby Boys = 1,779,948\n" +
                "Total Number of Unique Names = 31,954\n" +
                "***Unique Names (baby girls) = 17,905\n" +
                "***Unique Names (baby boys) = 14,049\n";
        assertEquals(generatedSummary, expectedSummary);
    }
}
