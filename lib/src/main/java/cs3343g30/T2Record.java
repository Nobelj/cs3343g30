package cs3343g30;

import javafx.beans.property.SimpleStringProperty;

/**
 * Class for a single record for task 2 which contains the name, occurrence, frequency and percentage
 */
public class T2Record {
	private final SimpleStringProperty name;
    private final SimpleStringProperty occurr;
    private final SimpleStringProperty freq;
    private final SimpleStringProperty percent;
    
    /**
     * Constructor for the T2Record class
     * @param name The name for the record
     * @param occurr The occurrence for the name in the range of years
     * @param freq The frequency this name has appeared in the range of years
     * @param percent The percentage of occurrence in the range of years
     */
    public T2Record(String name, String occurr, String freq, String percent) {
    	this.name = new SimpleStringProperty(name);
    	this.occurr = new SimpleStringProperty(occurr);
    	this.freq = new SimpleStringProperty(freq);
    	this.percent = new SimpleStringProperty(percent);
    }
    
    /**
     * Trivial name getter
     * @return Name of the record
     */
    public String getName() {
        return name.get();
    }

    /**
     * Trivial occurrence getter
     * @return Occurrence of the record
     */
    public String getOccurr() {
        return occurr.get();
    }
    
    /**
     * Trivial frequency getter
     * @return Frequency of the record
     */
    public String getFreq() {
    	return freq.get();
    }
    
    /**
     * Trivial percentage getter
     * @return Percentage of the record
     */
    public String getPercent() {
    	return percent.get();
    }
}
