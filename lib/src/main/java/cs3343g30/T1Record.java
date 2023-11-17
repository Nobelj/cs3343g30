package cs3343g30;

import javafx.beans.property.SimpleStringProperty;

/**
 * The class for each row of the tables in task 1
 * @author Nobel
 */
public class T1Record {
	private final SimpleStringProperty name;
    private final SimpleStringProperty occurr;
    private final SimpleStringProperty percent;

    /**
     * Constructor for T1Record
     * @param name      The name
     * @param occurr 	The occurrences of the name in that year
     * @param percent  	The percentage of the name in that year
     */
    public T1Record(String name, String occurr, String percent) {
        this.name = new SimpleStringProperty(name);
        this.occurr = new SimpleStringProperty(occurr);
        this.percent = new SimpleStringProperty(percent);
    }

    /**
     * Retrieve the value of name.
     * @return A String data type
     */
    public String getName() {
        return name.get();
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
