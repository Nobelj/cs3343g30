package cs3343g30;

import javafx.beans.property.SimpleStringProperty;

/**
 * The class for each row of the table in task 4
 * @author Nobel
 */
public class T4Record {
	/**
	 * The year
	 */
	private final SimpleStringProperty year;
	/**
	 * The rank
	 */
    private final SimpleStringProperty rank;
    /**
	 * The name
	 */
    private final SimpleStringProperty name;
    /**
	 * The gender
	 */
    private final SimpleStringProperty gender;
    /**
	 * The occurrence
	 */
    private final SimpleStringProperty occurr;
    /**
	 * The percentage
	 */
    private final SimpleStringProperty percent;
    
    /**
     * Constructor for T4Record
     * @param year      The year of the name's data
     * @param rank      The ranking of the name in that year
     * @param name      The name
     * @param gender	The gender of the name in that year
     * @param occurr 	The occurrences of the name in that year
     * @param percent  	The percentage of the name in that year
     */
    public T4Record(String year, String rank, String name, String gender, String occurr, String percent) {
    	this.year = new SimpleStringProperty(year);
        this.rank = new SimpleStringProperty(rank);
        this.name = new SimpleStringProperty(name);
        this.gender = new SimpleStringProperty(gender);
        this.occurr = new SimpleStringProperty(occurr);
        this.percent = new SimpleStringProperty(percent);
    }
    
    /**
    * Retrieve the value of year.
    * @return A String data type
    */
    public String getYear() {
    	return year.get();
    }
    
    /**
     * Retrieve the value of rank.
     * @return A String data type
     */
    public String getRank() {
    	return rank.get();
    }
    
    /**
     * Retrieve the value of name.
     * @return A String data type
     */
    public String getName() {
    	return name.get();
    }
    
    /**
     * Retrieve the value of gender.
     * @return A String data type
     */
    public String getGender() {
    	return gender.get();
    }
    
    /**
     * Retrieve the value of occurrence.
     * @return A String data type
     */
    public String getOccurr() {
    	return occurr.get();
    }
    
    /**
     * Retrieve the value of percentage.
     * @return A String data type
     */
    public String getPercent() {
    	return percent.get();
    }
}
