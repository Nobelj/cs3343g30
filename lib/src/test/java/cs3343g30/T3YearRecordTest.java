package cs3343g30;

import org.junit.Test;

import static org.junit.Assert.*;


public class T3YearRecordTest {

    @Test
    public void getYear() {
        T3YearRecord record = new T3YearRecord(2000, 1, 1000, (float) 0.02);
        assertEquals(record.getYear(), "2000");
    }

    @Test
    public void setYear() {
        T3YearRecord record = new T3YearRecord(2000, 1, 1000, (float) 0.02);
        record.setYear("2001");
        assertEquals(record.getYear(), "2001");
    }

    @Test
    public void getRank() {
        T3YearRecord record = new T3YearRecord(2000, 1, 1000, (float) 0.02);
        assertEquals(record.getRank(), "1");
    }

    @Test
    public void setRank() {
        T3YearRecord record = new T3YearRecord(2000, 1, 1000, (float) 0.02);
        record.setRank("2");
        assertEquals(record.getRank(), "2");
    }

    @Test
    public void getOccurrences() {
        T3YearRecord record = new T3YearRecord(2000, 1, 1000, (float) 0.02);
        assertEquals(record.getOccurrences(), "1000");
    }

    @Test
    public void setOccurrences() {
        T3YearRecord record = new T3YearRecord(2000, 1, 1000, (float) 0.02);
        record.setOccurrences("1001");
        assertEquals(record.getOccurrences(), "1001");
    }

    @Test
    public void getPercentage() {
        T3YearRecord record = new T3YearRecord(2000, 1, 1000, (float) 0.02);
        assertEquals(record.getPercentage(), "0.020");
    }

    @Test
    public void setPercentage() {
        T3YearRecord record = new T3YearRecord(2000, 1, 1000, (float) 0.02);
        record.setPercentage("0.040");
        assertEquals(record.getPercentage(), "0.040");
    }
}